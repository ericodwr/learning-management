package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.model.Learning;

@Repository
@Profile("hql-query")
public class LearningDaoHqlImpl implements LearningDao {
	@PersistenceContext
	private EntityManager em;

	public LearningDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<Learning> getLearningByClassId(Long classId) {
		final String sql = "SELECT l FROM Learning l " + "WHERE l.learningClass.id = :classId";

		final List<Learning> learningList = this.em.createQuery(sql, Learning.class).setParameter("classId", classId)
				.getResultList();
		return learningList;
	}

	@Override
	public Learning insert(Learning learning) {
		em.persist(learning);
		return learning;
	}
	
	@Override
	public Learning getById(Long id) {
		final Learning learning = this.em.find(Learning.class, id);
		return learning;
	}

}
