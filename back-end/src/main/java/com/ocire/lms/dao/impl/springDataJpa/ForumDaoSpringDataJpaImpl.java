package com.ocire.lms.dao.impl.springDataJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ForumDao;
import com.ocire.lms.model.Forum;
import com.ocire.lms.repo.ForumRepo;

@Repository
@Profile("springdatajpa-query")
public class ForumDaoSpringDataJpaImpl implements ForumDao {
	@PersistenceContext
	private EntityManager em;
	private ForumRepo forumRepo;

	public ForumDaoSpringDataJpaImpl(ForumRepo forumRepo) {
		this.forumRepo = forumRepo;
	}

	@Override
	public Forum getForumByLearningId(Long learningId) {

		Forum forum = forumRepo.getByLearningId(learningId);

		return forum;
	}

	@Override
	public Forum insert(Forum forum) {
		forumRepo.save(forum);
		return forum;
	}

	@Override
	public Forum getById(Long id) {
		final Forum forum = forumRepo.findById(id).get();
		return forum;
	}

}
