package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Learning;

public interface LearningRepo extends JpaRepository<Learning, Long> {
	List<Learning> getBylearningClassId(Long id);
}
