package com.ocire.lms.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.EnrollDao;
import com.ocire.lms.model.Enroll;

@Repository
@Profile("hql-query")
public class EnrollDaoHqlImpl implements EnrollDao {
	@PersistenceContext
	private EntityManager em;

	public EnrollDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public Enroll insert(Enroll enroll) {
		em.persist(enroll);

		return enroll;
	}

}
