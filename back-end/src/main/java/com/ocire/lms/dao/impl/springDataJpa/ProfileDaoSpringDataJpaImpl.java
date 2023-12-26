package com.ocire.lms.dao.impl.springDataJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ProfileDao;
import com.ocire.lms.model.Profile;
import com.ocire.lms.repo.ProfileRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class ProfileDaoSpringDataJpaImpl implements ProfileDao {
	@PersistenceContext
	private EntityManager em;
	private ProfileRepo profileRepo;

	public ProfileDaoSpringDataJpaImpl(ProfileRepo profileRepo) {
		this.profileRepo = profileRepo;
	}

	@Override
	public Profile insert(Profile profile) {
		this.profileRepo.save(profile);
		return profile;
	}

}
