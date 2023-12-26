package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.QuestionTypeDao;
import com.ocire.lms.model.QuestionType;

@Repository
@Profile("hql-query")
public class QuestionTypeDaoHqlImpl implements QuestionTypeDao {
	@PersistenceContext
	private EntityManager em;

	public QuestionTypeDaoHqlImpl() {
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
