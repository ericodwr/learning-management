package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.StudentAttendanceDao;
import com.ocire.lms.model.StudentAttendance;
import com.ocire.lms.repo.StudentAttendanceRepo;

@Repository
@Profile("springdatajpa-query")
public class StudentAttendanceDaoSpringDataJpaImpl implements StudentAttendanceDao {
	@PersistenceContext
	private EntityManager em;
	private StudentAttendanceRepo studentAttendanceRepo;

	public StudentAttendanceDaoSpringDataJpaImpl(StudentAttendanceRepo studentAttendanceRepo) {
		this.studentAttendanceRepo = studentAttendanceRepo;
	}

	@Override
	public StudentAttendance getStudentAttendance(Long studentId, Long learningId) {

		StudentAttendance studentAttendance = studentAttendanceRepo.getByStudentIdAndLearningId(studentId, learningId);

		return studentAttendance;

	}

	@Override
	public List<StudentAttendance> getStudentAttendanceByLearningId(Long learningId) {

		final List<StudentAttendance> studentAttendanceList = studentAttendanceRepo.getByLearningId(learningId);

		return studentAttendanceList;
	}

	@Override
	public StudentAttendance getById(Long id) {
		final StudentAttendance studentAttendance = studentAttendanceRepo.findById(id).get();
		return studentAttendance;
	}

	@Override
	public StudentAttendance insert(StudentAttendance studentAttendance) {
		studentAttendanceRepo.save(studentAttendance);
		return studentAttendance;
	}

}
