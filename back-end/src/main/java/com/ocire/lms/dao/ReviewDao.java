package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Review;

public interface ReviewDao {
	Review insert(Review review);
	
	List<Review> getReviewByTask(Long id);
}
