package com.ocire.lms.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.dao.EnrollDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.EnrollInsertReqDto;
import com.ocire.lms.model.Enroll;
import com.ocire.lms.model.User;
import com.ocire.lms.service.EnrollService;
import com.ocire.lms.service.PrincipalService;

@Service
public class EnrollServiceImpl implements EnrollService {
	@PersistenceContext
	private EntityManager em;
	private final UserDao userDao;
	private final PrincipalService principalService;
	private final EnrollDao enrollDao;
	private final ClassDao classDao;

	public EnrollServiceImpl(EntityManager em, UserDao userDao, PrincipalService principalService, EnrollDao enrollDao,
			ClassDao classDao) {
		this.em = em;
		this.userDao = userDao;
		this.principalService = principalService;
		this.enrollDao = enrollDao;
		this.classDao = classDao;
	}

	@Transactional
	@Override
	public InsertResDto insertEnroll(EnrollInsertReqDto data) {
		final InsertResDto result = new InsertResDto();
		final Enroll enroll = new Enroll();

		final User student = userDao.getById(principalService.getPrincipal());
		final com.ocire.lms.model.Class studentClass = classDao.getById(data.getClassId());
		
		enroll.setStudent(student);
		
		enroll.setStudentClass(studentClass);
		enroll.setCreatedBy(principalService.getPrincipal());
		
		final Enroll newEnroll = enrollDao.insert(enroll);
		
		result.setId(newEnroll.getId());
		result.setMessage("Successfully enroll class!");

		return result;
	}

}
