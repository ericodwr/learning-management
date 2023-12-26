package com.ocire.lms.dao.impl.springDataJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.EnrollDao;
import com.ocire.lms.model.Enroll;
import com.ocire.lms.repo.EnrollRepo;

@Repository
@Profile("springdatajpa-query")
public class EnrollDaoSpringDataJpaImpl implements EnrollDao {
	@PersistenceContext
	private EntityManager em;
	private EnrollRepo enrollRepo;

	public EnrollDaoSpringDataJpaImpl(EnrollRepo enrollRepo) {
		this.enrollRepo = enrollRepo;
	}

	@Override
	public Enroll insert(Enroll enroll) {
		enrollRepo.save(enroll);

		return enroll;
	}


}
