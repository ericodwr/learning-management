package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.CommentDao;
import com.ocire.lms.model.Comment;

@Repository
@Profile("native-query")
public class CommentDaoImpl implements CommentDao {
	@PersistenceContext
	private EntityManager em;
	
	public CommentDaoImpl() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByForumId(Long forumId)  {
		final String sql = "SELECT * FROM t_comment tc INNER JOIN t_user tu ON tc.user_id = tu.id INNER JOIN t_profile tp ON tu.profile_id = tp.id WHERE tc.forum_id = :forumId";
		
		final List<Comment> commentList = this.em.createNativeQuery(sql, Comment.class).setParameter("forumId", forumId)
				.getResultList();
		return commentList;
	}
	
	@Override
	public Comment insert(Comment comment)  {
		em.persist(comment);
		return comment;
	}

}
