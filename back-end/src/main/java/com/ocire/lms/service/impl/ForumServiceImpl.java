package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.CommentDao;
import com.ocire.lms.dao.ForumDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.forum.CommentInsertReqDto;
import com.ocire.lms.dto.forum.CommentResDto;
import com.ocire.lms.model.Comment;
import com.ocire.lms.model.Forum;
import com.ocire.lms.model.User;
import com.ocire.lms.service.ForumService;
import com.ocire.lms.service.PrincipalService;

@Service
public class ForumServiceImpl implements ForumService {
	@PersistenceContext
	private EntityManager em;
	private final ForumDao forumDao;
	private final CommentDao commentDao;
	private final UserDao userDao;
	private final PrincipalService principalService;

	public ForumServiceImpl(ForumDao forumDao, CommentDao commentDao, UserDao userDao,
			PrincipalService principalService) {
		this.forumDao = forumDao;
		this.commentDao = commentDao;
		this.userDao = userDao;
		this.principalService = principalService;
	}
	
	@Override
	public Forum getForumByLearningId(Long learningId) {
		return forumDao.getForumByLearningId(learningId);
	}

	@Override
	public List<CommentResDto> getCommentByForumId(Long forumId) {
		final List<CommentResDto> commentList = new ArrayList<>();
		commentDao.getCommentByForumId(forumId).forEach(comment -> {
			final CommentResDto commentRes = new CommentResDto();
			commentRes.setForumChat(comment.getForumChat());
			commentRes.setFullName(comment.getUser().getProfile().getFullName());
			commentRes.setUserId(comment.getUser().getId());
			commentRes.setCreatedAt(comment.getCreatedAt().toString());
			commentList.add(commentRes);
		});
		return commentList;
	}
	
	@Transactional
	@Override
	public InsertResDto insertComment(CommentInsertReqDto data) {
		final InsertResDto result = new InsertResDto();
		final User user = userDao.getById(principalService.getPrincipal());
		final Forum forum = forumDao.getById(data.getForumId());
		final Comment comment = new Comment();
		comment.setForumChat(data.getForumChat());
		comment.setForum(forum);
		comment.setUser(user);
		comment.setCreatedBy(principalService.getPrincipal());

		final Comment newComment = commentDao.insert(comment);
		result.setId(newComment.getId());
		result.setMessage("Successfully insert comment!");
		return result;
	}
	
	@Override
	public Forum insertForum(Forum forum) {
		try {
			this.em.getTransaction().begin();
			final Forum newForum = forumDao.insert(forum);
			this.em.getTransaction().commit();
			return newForum;
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			return null;
		}
	}

}
