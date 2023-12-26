package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.dao.StudentAttendanceDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.UpdateResDto;
import com.ocire.lms.dto.student.AttendanceInsertReqDto;
import com.ocire.lms.dto.student.AttendanceResDto;
import com.ocire.lms.model.Learning;
import com.ocire.lms.model.StudentAttendance;
import com.ocire.lms.model.User;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.service.StudentAttendanceService;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
	@PersistenceContext
	private EntityManager em;
	private final StudentAttendanceDao studentAttendanceDao;
	private final UserDao userDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;

	public StudentAttendanceServiceImpl(StudentAttendanceDao studentAttendanceDao, UserDao userDao,
			LearningDao learningDao, PrincipalService principalService) {
		this.studentAttendanceDao = studentAttendanceDao;
		this.principalService = principalService;
		this.userDao = userDao;
		this.learningDao = learningDao;
	}

	@Override
	public StudentAttendance getStudentAttendance(Long studentId, Long learningId) {
		return studentAttendanceDao.getStudentAttendance(studentId, learningId);
	}

	@Override
	public List<AttendanceResDto> getStudentAttendanceByLearningId(Long learningId) {
		final List<AttendanceResDto> attendances = new ArrayList<>();
		studentAttendanceDao.getStudentAttendanceByLearningId(learningId).forEach(a -> {
			final AttendanceResDto student = new AttendanceResDto();
			student.setStudentName(a.getStudent().getProfile().getFullName());
			student.setStudentId(a.getStudent().getId());
			student.setLearningId(a.getLearning().getId());
			student.setApprovement(a.getApprovement());
			student.setCreatedAt(a.getCreatedAt().toString());
			attendances.add(student);
		});
		return attendances;
	}

	@Transactional
	@Override
	public UpdateResDto updateStudentAttendanceStatus(AttendanceInsertReqDto data) {
		final UpdateResDto result = new UpdateResDto();

		final StudentAttendance studentAttendace = studentAttendanceDao.getStudentAttendance(data.getStudentId(),
				data.getLearningId());

		final StudentAttendance updatedStudentAttendace = studentAttendanceDao.getById(studentAttendace.getId());

		updatedStudentAttendace.setApprovement(data.getApprovement());
		updatedStudentAttendace.setUpdatedBy(principalService.getPrincipal());

		em.flush();

		result.setVer(updatedStudentAttendace.getVer());
		result.setMessage("Updated Successfully");
		return result;

	}

	@Override
	public InsertResDto insertStudentAttendance(AttendanceInsertReqDto data) {
		final InsertResDto result = new InsertResDto();
		
		final StudentAttendance attendance = new StudentAttendance();
		final User student = userDao.getById(principalService.getPrincipal());
		final Learning learning = learningDao.getById(data.getLearningId());
		attendance.setCreatedBy(principalService.getPrincipal());
		attendance.setApprovement(data.getApprovement());
		attendance.setLearning(learning);
		attendance.setStudent(student);
		final StudentAttendance newAttendance = studentAttendanceDao.insert(attendance);
		
		result.setId(newAttendance.getId());
		result.setMessage("Successfully input attendance, please wait the teacher to approve!");
		
		return result;
	}

}
