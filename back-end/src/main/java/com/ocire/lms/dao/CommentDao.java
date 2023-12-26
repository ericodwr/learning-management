package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Comment;

public interface CommentDao {
	List<Comment> getCommentByForumId(Long forumId);

	Comment insert(Comment comment);
}
