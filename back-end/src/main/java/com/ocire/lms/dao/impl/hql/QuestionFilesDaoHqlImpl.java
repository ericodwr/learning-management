package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionFilesDao;
import com.ocire.lms.model.QuestionFiles;

@Repository
@Profile("hql-query")
public class QuestionFilesDaoHqlImpl implements QuestionFilesDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionFilesDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId) {
		final String sql = "SELECT qf FROM QuestionFiles qf " + "WHERE qf.question.id = :questionId";

		final List<QuestionFiles> questionFilesList = this.em.createQuery(sql, QuestionFiles.class)
				.setParameter("questionId", questionId).getResultList();

		return questionFilesList;
	}

	@Override
	public QuestionFiles insert(QuestionFiles questionFiles) {
		em.persist(questionFiles);
		return questionFiles;
	}

}
