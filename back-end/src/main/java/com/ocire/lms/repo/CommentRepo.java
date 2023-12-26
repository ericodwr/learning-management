package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {
	List<Comment> getByForumId(Long id);
}
