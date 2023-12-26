package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionFilesDao;
import com.ocire.lms.model.QuestionFiles;
import com.ocire.lms.repo.QuestionFilesRepo;

@Repository
@Profile("springdatajpa-query")
public class QuestionFilesDaoSpringDataJpaImpl implements QuestionFilesDao {
	@PersistenceContext
	private EntityManager em;
	private QuestionFilesRepo questionFilesRepo;

	public QuestionFilesDaoSpringDataJpaImpl(QuestionFilesRepo questionFilesRepo) {
		this.questionFilesRepo = questionFilesRepo;
	}

	@Override
	public List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId) {
		final List<QuestionFiles> questionFilesList = questionFilesRepo.getByQuestionId(questionId);

		return questionFilesList;
	}

	@Override
	public QuestionFiles insert(QuestionFiles questionFiles) {
		questionFilesRepo.save(questionFiles);
		return questionFiles;
	}

}
