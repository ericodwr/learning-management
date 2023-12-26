package com.ocire.lms.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ProfileDao;
import com.ocire.lms.model.Profile;

@Repository
@org.springframework.context.annotation.Profile("hql-query")
public class ProfileDaoHqlImpl implements ProfileDao {
	@PersistenceContext
	private EntityManager em;

	public ProfileDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public Profile insert(Profile profile) {
		em.persist(profile);
		return profile;
	}

}
