package com.ocire.lms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionAnswerDao;
import com.ocire.lms.model.Question;
import com.ocire.lms.model.QuestionAnswer;

@Repository
@Profile("native-query")
public class QuestionAnswerDaoImpl implements QuestionAnswerDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionAnswerDaoImpl() {
	}

	@Override
	public QuestionAnswer insert(QuestionAnswer questionAnswer)  {
		em.persist(questionAnswer);
		return questionAnswer;
	}

	@Override
	public List<QuestionAnswer> getByCreatedBy(Long id)  {
		final List<QuestionAnswer> questionAnswers = new ArrayList<>();
		final String sql = "SELECT ta.id as answer_id, ta.essay_answer, tq.id, tq.question_name as question_id FROM t_question_answer ta INNER JOIN t_question tq ON ta.id_question = tq.id WHERE ta.created_by = :id";
		final List<?> userObjs = this.em.createNativeQuery(sql).setParameter("id", id).getResultList();

		if (userObjs.size() > 0) {
			for (Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				final QuestionAnswer questionAnswer = new QuestionAnswer();
				questionAnswer.setId(Long.valueOf(userArr[0].toString()));
				questionAnswer.setEssayAnswer(userArr[1].toString());

				final Question question = new Question();

				question.setId(Long.valueOf(userArr[2].toString()));
				question.setQuestionName(userArr[3].toString());
				questionAnswer.setQuestion(question);
				questionAnswers.add(questionAnswer);
			}
		}

		return questionAnswers;
	}

}
