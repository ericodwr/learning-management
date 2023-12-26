package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionTypeDao;
import com.ocire.lms.model.QuestionType;

@Repository
@Profile("native-query")
public class QuestionTypeDaoImpl implements QuestionTypeDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionTypeDaoImpl() {
	}

	@Override
	public QuestionType getById(Long id) {
		final QuestionType questionType = this.em.find(QuestionType.class, id);
		return questionType;
	}

	@Override
	public List<QuestionType> getAllType() {
		// TODO Auto-generated method stub
		return null;
	}

}
