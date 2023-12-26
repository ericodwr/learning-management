package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.model.Question;
import com.ocire.lms.repo.QuestionRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionDaoSpringDataJpaImpl implements QuestionDao {
	@PersistenceContext
	private EntityManager em;
	private QuestionRepo questionRepo;

	public QuestionDaoSpringDataJpaImpl(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		final List<Question> questionlist = questionRepo.getByTaskId(taskId);

		return questionlist;
	}

	@Override
	public Question insert(Question question) {
		questionRepo.save(question);
		return question;
	}

	@Override
	public Question getById(Long id) {
		final Question question = questionRepo.findById(id).get();
		return question;
	}

}
