package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.StudentAttendanceDao;
import com.ocire.lms.model.Learning;
import com.ocire.lms.model.StudentAttendance;
import com.ocire.lms.model.User;

@Repository
@Profile("native-query")
public class StudentAttendanceDaoImpl implements StudentAttendanceDao {
	@PersistenceContext
	private EntityManager em;

	public StudentAttendanceDaoImpl() {
	}

	@Override
	public StudentAttendance getStudentAttendance(Long studentId, Long learningId) {
		final String sql = "SELECT sa.id, sa.student_id, sa.learning_id, sa.approvement "
				+ "FROM t_student_attendance sa " + "INNER JOIN t_user u " + "ON sa.student_id = u.id "
				+ "INNER JOIN t_learning l " + "ON sa.learning_id = l.id " + "WHERE sa.student_id = :studentId "
				+ "AND sa.learning_id = :learningId";

		final Object userObj = this.em.createNativeQuery(sql).setParameter("studentId", studentId)
				.setParameter("learningId", learningId).getSingleResult();

		final Object[] userArr = (Object[]) userObj;

		StudentAttendance studentAttendance = null;

		if (userArr.length > 0) {
			studentAttendance = new StudentAttendance();
			studentAttendance.setId(Long.valueOf(userArr[0].toString()));
			studentAttendance.setApprovement(Boolean.valueOf(userArr[3].toString()));

			final User student = new User();
			student.setId(Long.valueOf(userArr[1].toString()));

			final Learning learning = new Learning();
			learning.setId(Long.valueOf(userArr[2].toString()));

		}

		return studentAttendance;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentAttendance> getStudentAttendanceByLearningId(Long learningId) {
		final String sql = "SELECT * FROM t_student_attendance sa " + "INNER JOIN t_user u "
				+ "ON sa.student_id = u.id " + "INNER JOIN t_profile tp " + "ON u.profile_id = tp.id "
				+ "INNER JOIN t_learning l " + "ON sa.learning_id = l.id " + "WHERE sa.learning_id = :learningId";

		final List<StudentAttendance> studentAttendanceList = this.em.createNativeQuery(sql, StudentAttendance.class)
				.setParameter("learningId", learningId).getResultList();

		return studentAttendanceList;
	}

	@Override
	public StudentAttendance getById(Long id) {
		final StudentAttendance studentAttendance = this.em.find(StudentAttendance.class, id);
		return studentAttendance;
	}

	@Override
	public StudentAttendance insert(StudentAttendance studentAttendance) {
		em.persist(studentAttendance);
		return studentAttendance;
	}

}
