package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.StudentAttendanceDao;
import com.ocire.lms.model.Learning;
import com.ocire.lms.model.StudentAttendance;
import com.ocire.lms.model.User;

@Repository
@Profile("hql-query")
public class StudentAttendanceDaoHqlImpl implements StudentAttendanceDao {
	@PersistenceContext
	private EntityManager em;

	public StudentAttendanceDaoHqlImpl(SessionFactory factory) {
	}
	
	@Override
	public StudentAttendance getStudentAttendance(Long studentId, Long learningId) {
		final String sql = "SELECT sa.id, sa.student.id, sa.learning.id, sa.approvement " + "FROM StudentAttendance sa "
				+ "WHERE sa.student.id = :studentId " + "AND sa.learning.id = :learningId";

		final Object userObj = this.em.createQuery(sql).setParameter("studentId", studentId)
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

	@Override
	public List<StudentAttendance> getStudentAttendanceByLearningId(Long learningId) {
		final String sql = "SELECT sa FROM StudentAttendance sa " + "WHERE sa.learning.id = :learningId";

		final List<StudentAttendance> studentAttendanceList = this.em.createQuery(sql, StudentAttendance.class)
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
