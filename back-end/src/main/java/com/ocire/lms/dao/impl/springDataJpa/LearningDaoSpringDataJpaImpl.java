package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.model.Learning;
import com.ocire.lms.repo.LearningRepo;

@Repository
@Profile("springdatajpa-query")
public class LearningDaoSpringDataJpaImpl implements LearningDao {
	@PersistenceContext
	private EntityManager em;
	private LearningRepo learningRepo;

	public LearningDaoSpringDataJpaImpl(LearningRepo learningRepo) {
		this.learningRepo = learningRepo;
	}

	@Override
	public List<Learning> getLearningByClassId(Long classId) {

		final List<Learning> learningList = learningRepo.getBylearningClassId(classId);
		return learningList;
	}

	@Override
	public Learning insert(Learning learning) {
		learningRepo.save(learning);
		return learning;
	}

	@Override
	public Learning getById(Long id) {
		final Learning learning = learningRepo.findById(id).get();
		return learning;
	}

}
