package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.model.Question;

@Repository
@Profile("hql-query")
public class QuestionDaoHqlImpl implements QuestionDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		final String sql = "SELECT q FROM Question q WHERE q.task.id = :taskId";

		final List<Question> questionlist = this.em.createQuery(sql, Question.class).setParameter("taskId", taskId)
				.getResultList();

		return questionlist;
	}

	@Override
	public Question insert(Question question) {
		em.persist(question);
		return question;
	}

	@Override
	public Question getById(Long id) {
		final Question question = this.em.find(Question.class, id);
		return question;
	}

}
