package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionAnswerDao;
import com.ocire.lms.model.QuestionAnswer;
import com.ocire.lms.repo.QuestionAnswerRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerDaoSpringDataJpaImpl implements QuestionAnswerDao {
	@PersistenceContext
	private EntityManager em;
	private QuestionAnswerRepo questionAnswerRepo;

	public QuestionAnswerDaoSpringDataJpaImpl(QuestionAnswerRepo questionAnswerRepo) {
		this.questionAnswerRepo = questionAnswerRepo;
	}

	@Override
	public QuestionAnswer insert(QuestionAnswer questionAnswer) {
		questionAnswerRepo.save(questionAnswer);
		return questionAnswer;
	}

	@Override
	public List<QuestionAnswer> getByCreatedBy(Long id) {
		final List<QuestionAnswer> questionAnswers = questionAnswerRepo.getByCreatedBy(id);

		return questionAnswers;
	}

}
