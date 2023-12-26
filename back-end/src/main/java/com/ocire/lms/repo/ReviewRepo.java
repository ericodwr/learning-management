package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {
	List<Review> getByTaskId(Long id);
}
