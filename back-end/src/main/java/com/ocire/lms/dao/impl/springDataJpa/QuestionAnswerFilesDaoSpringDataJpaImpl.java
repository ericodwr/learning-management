package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionAnswerFilesDao;
import com.ocire.lms.model.QuestionAnswerFiles;
import com.ocire.lms.repo.QuestionAnswerFileRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionAnswerFilesDaoSpringDataJpaImpl implements QuestionAnswerFilesDao {
	@PersistenceContext
	private EntityManager em;
	private QuestionAnswerFileRepo questionAnswerFileRepo;
	
	public QuestionAnswerFilesDaoSpringDataJpaImpl(QuestionAnswerFileRepo questionAnswerFileRepo) {
		this.questionAnswerFileRepo = questionAnswerFileRepo;
	}

	@Override
	public QuestionAnswerFiles insert(QuestionAnswerFiles questionAnswerFiles) {
		questionAnswerFileRepo.save(questionAnswerFiles);
		return questionAnswerFiles;
	}

	@Override
	public List<QuestionAnswerFiles> getByAnswerId(Long id) {
		final List<QuestionAnswerFiles> answerFiles = questionAnswerFileRepo.getByQuestionAnswerId(id);
		return answerFiles;
	}

}
