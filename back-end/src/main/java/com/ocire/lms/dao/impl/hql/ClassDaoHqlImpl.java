package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.model.Class;
import com.ocire.lms.model.Enroll;

@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class ClassDaoHqlImpl implements ClassDao {
	@PersistenceContext
	private EntityManager em;

	public ClassDaoHqlImpl(SessionFactory factory) {
	}

//	public List<Class> getClassByStudentId(Long studentId) {
//
//		final String sql = "SELECT e.studentClass.id, e.studentClass.className, e.studentClass.classCode, e.studentClass.teacher.id, e.studentClass.teacher.profile.fullName "
//				+ "FROM Enroll e " + "WHERE e.student.id = :studentId";
//
//		final List<Class> studentClasses = new ArrayList<>();
//
//		final List<?> userObjs = this.em.createQuery(sql).setParameter("studentId", studentId).getResultList();
//
//		if (userObjs.size() > 0) {
//			for (Object userObj : userObjs) {
//				final Object[] userArr = (Object[]) userObj;
//				final Class studentClass = new Class();
//				studentClass.setId(Long.valueOf(userArr[0].toString()));
//				studentClass.setClassName(userArr[1].toString());
//				studentClass.setClassCode(userArr[2].toString());
//
//				final User teacher = new User();
//				teacher.setId(Long.valueOf(userArr[3].toString()));
//
//				final Profile profile = new Profile();
//				profile.setFullName(userArr[4].toString());
//
//				teacher.setProfile(profile);
//				studentClass.setTeacher(teacher);
//				studentClasses.add(studentClass);
//			}
//		}
//
//		return studentClasses;
//
//	}

	public List<Class> getAllClass() {
		final String sql = "SELECT c FROM Class c";
		List<Class> classList = this.em.createQuery(sql, Class.class).getResultList();
		return classList;
	}

	public List<Class> getClassByTeacherId(Long teacherId) {
		final String sql = "SELECT c FROM Class c WHERE c.teacher.id = :teacherId";

		final List<Class> teacherClassList = this.em.createQuery(sql, Class.class).setParameter("teacherId", teacherId)
				.getResultList();

		return teacherClassList;
	}

	@Override
	public Class insert(Class newClass) {
		em.persist(newClass);
		return newClass;
	}
	
	@Override
	public Class getById(Long id) {
		final Class classLms = em.find(Class.class, id);
		return classLms;
	}

	@Override
	public List<Enroll> getClassByStudentId(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class> getClassByUnenrollStudent(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
