package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.CommentDao;
import com.ocire.lms.model.Comment;

@Repository
@Profile("hql-query")
public class CommentDaoHqlImpl implements CommentDao {
	@PersistenceContext
	private EntityManager em;

	public CommentDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<Comment> getCommentByForumId(Long forumId) {
		final String sql = "SELECT tc FROM Comment tc WHERE tc.forum.id = :forumId";

		final List<Comment> commentList = this.em.createQuery(sql, Comment.class).setParameter("forumId", forumId)
				.getResultList();
		return commentList;
	}

	@Override
	public Comment insert(Comment comment) {
		em.persist(comment);
		return comment;
	}

}
