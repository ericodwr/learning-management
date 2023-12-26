package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.model.Question;

@Repository
@Profile("native-query")
public class QuestionDaoImpl implements QuestionDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionByTaskId(Long taskId) {
		final String sql = "SELECT * FROM t_question q INNER JOIN t_question_type t ON q.type_id = t.id  WHERE q.task_id = :taskId";
		
		final List<Question> questionlist = this.em.createNativeQuery(sql, Question.class)
				.setParameter("taskId", taskId).getResultList();

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
