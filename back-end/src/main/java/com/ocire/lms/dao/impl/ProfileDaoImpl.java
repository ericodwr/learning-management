package com.ocire.lms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ProfileDao;
import com.ocire.lms.model.Profile;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class ProfileDaoImpl implements ProfileDao {
	@PersistenceContext
	private EntityManager em;

	public ProfileDaoImpl() {
	}

	@Override
	public Profile insert(Profile profile)  {
		em.persist(profile);
		return profile;
	}

}
