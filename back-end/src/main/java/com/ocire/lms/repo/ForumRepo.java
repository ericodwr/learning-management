package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Forum;

public interface ForumRepo extends JpaRepository<Forum, Long> {
	Forum getByLearningId(Long id);
}
