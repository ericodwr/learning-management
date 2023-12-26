package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.model.Class;
import com.ocire.lms.model.Enroll;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ClassDaoImpl implements ClassDao {
	@PersistenceContext
	private EntityManager em;
	
	public ClassDaoImpl() {
	}

//	public List<Class> getClassByStudentId(Long studentId) {
//
//		final String sql = "SELECT c.id, c.class_name, c.class_code, c.teacher_id, p.full_name FROM t_enroll e INNER JOIN t_class c ON e.class_id = c.id  "
//				+ "INNER JOIN t_user u ON c.teacher_id = u.id INNER JOIN t_profile p ON u.profile_id = p.id "
//				+ "WHERE e.student_id = :studentId";
//
//		final List<Class> studentClasses = new ArrayList<>();
//
//		final List<?> userObjs = this.em.createNativeQuery(sql).setParameter("studentId", studentId).getResultList();
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

	@SuppressWarnings("unchecked")
	public List<Class> getAllClass() {
		final String sql = "SELECT * FROM t_class";
		List<Class> classList = this.em.createNativeQuery(sql, Class.class).getResultList();
		return classList;
	}

	@SuppressWarnings("unchecked")
	public List<Class> getClassByTeacherId(Long teacherId) {
		final String sql = "SELECT * FROM t_class WHERE teacher_id = :teacherId";

		final List<Class> teacherClassList = this.em.createNativeQuery(sql, Class.class)
				.setParameter("teacherId", teacherId).getResultList();

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
