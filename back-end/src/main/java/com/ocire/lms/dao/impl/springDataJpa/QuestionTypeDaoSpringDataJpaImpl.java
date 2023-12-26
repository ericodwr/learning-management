package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionTypeDao;
import com.ocire.lms.model.QuestionType;
import com.ocire.lms.repo.QuestionTypeRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionTypeDaoSpringDataJpaImpl implements QuestionTypeDao {
	@PersistenceContext
	private EntityManager em;
	private QuestionTypeRepo questionTypeRepo;

	public QuestionTypeDaoSpringDataJpaImpl(QuestionTypeRepo questionTypeRepo) {
		this.questionTypeRepo = questionTypeRepo;
	}

	@Override
	public QuestionType getById(Long id) {
		final QuestionType questionType = questionTypeRepo.findById(id).get();
		return questionType;
	}

	@Override
	public List<QuestionType> getAllType() {
		final List<QuestionType> questionTypes = questionTypeRepo.findAll();
		return questionTypes;
	}

}
