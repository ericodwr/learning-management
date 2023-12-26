package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ReviewDao;
import com.ocire.lms.model.Review;

@Repository
@Profile("native-query")
public class ReviewDaoImpl implements ReviewDao {
	@PersistenceContext
	private EntityManager em;

	public ReviewDaoImpl() {
	}

	@Override
	public Review insert(Review review) {
		em.persist(review);
		return review;
	}

	@Override
	public List<Review> getReviewByTask(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
