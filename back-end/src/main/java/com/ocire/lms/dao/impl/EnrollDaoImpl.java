package com.ocire.lms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.EnrollDao;
import com.ocire.lms.model.Enroll;

@Repository
@Profile("native-query")
public class EnrollDaoImpl implements EnrollDao {
	@PersistenceContext
	private EntityManager em;

	public EnrollDaoImpl() {
	}

	@Override
	public Enroll insert(Enroll enroll)  {
		em.persist(enroll);

		return enroll;
	}

}
