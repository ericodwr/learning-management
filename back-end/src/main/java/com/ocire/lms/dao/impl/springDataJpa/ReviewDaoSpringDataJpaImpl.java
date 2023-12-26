package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ReviewDao;
import com.ocire.lms.model.Review;
import com.ocire.lms.repo.ReviewRepo;

@Repository
@Profile("springdatajpa-query")
public class ReviewDaoSpringDataJpaImpl implements ReviewDao {
	@PersistenceContext
	private EntityManager em;
	private ReviewRepo reviewRepo;
	
	public ReviewDaoSpringDataJpaImpl(ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;
	}
	
	@Override
	public Review insert(Review review) {
		reviewRepo.save(review);
		return review;
	}

	@Override
	public List<Review> getReviewByTask(Long id) {
		final List<Review> reviews = reviewRepo.getByTaskId(id);
		return reviews;
	}

}
