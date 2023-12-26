package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.model.Class;
import com.ocire.lms.model.Enroll;
import com.ocire.lms.repo.ClassRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ClassDaoSpringDataJpaImpl implements ClassDao {
	@PersistenceContext
	private EntityManager em;
	private ClassRepo classRepo;

	public ClassDaoSpringDataJpaImpl(ClassRepo classRepo) {
		this.classRepo = classRepo;
	}

	public List<Enroll> getClassByStudentId(Long studentId) {

		final String sql = "SELECT e " + "FROM Enroll e " + " WHERE student.id = :studentId";
		final List<Enroll> enrollList = this.em.createQuery(sql,Enroll.class).setParameter("studentId", studentId).getResultList();

		return enrollList;

	}

	public List<Class> getAllClass() {
		List<Class> classList = classRepo.findAll();
		return classList;
	}

	public List<Class> getClassByTeacherId(Long teacherId) {

		final List<Class> teacherClassList = classRepo.getByTeacherId(teacherId);

		return teacherClassList;
	}

	@Override
	public Class insert(Class newClass) {
		classRepo.save(newClass);
		return newClass;
	}

	@Override
	public Class getById(Long id) {
		final Class classLms = classRepo.findById(id).get();
		return classLms;
	}

	@Override
	public List<Class> getClassByUnenrollStudent(Long studentId) {
		List<Class> unenrollClass = classRepo.getByEnrolled(studentId);
		return unenrollClass;
	}

}
