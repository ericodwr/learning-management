package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.CommentDao;
import com.ocire.lms.model.Comment;
import com.ocire.lms.repo.CommentRepo;

@Repository
@Profile("springdatajpa-query")
public class CommentDaoSpringDataJpaImpl implements CommentDao {
	@PersistenceContext
	private EntityManager em;
	private CommentRepo commentRepo;

	public CommentDaoSpringDataJpaImpl(CommentRepo commentRepo) {
		this.commentRepo = commentRepo;
	}

	@Override
	public List<Comment> getCommentByForumId(Long forumId) {

		final List<Comment> commentList = commentRepo.getByForumId(forumId);
		return commentList;
	}

	@Override
	public Comment insert(Comment comment) {
		commentRepo.save(comment);
		return comment;
	}

}
