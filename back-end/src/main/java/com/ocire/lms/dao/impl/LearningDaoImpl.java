package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.model.Learning;

@Repository
@Profile("native-query")
public class LearningDaoImpl implements LearningDao {
	@PersistenceContext
	private EntityManager em;

	public LearningDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Learning> getLearningByClassId(Long classId) {
		final String sql = "SELECT * FROM t_learning l " + "WHERE l.class_id = :classId";

		final List<Learning> learningList = this.em.createNativeQuery(sql, Learning.class)
				.setParameter("classId", classId).getResultList();
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
