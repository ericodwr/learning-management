package com.ocire.lms.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.ForumDao;
import com.ocire.lms.model.Forum;
import com.ocire.lms.model.Learning;

@Repository
@Profile("hql-query")
public class ForumDaoHqlImpl implements ForumDao {
	@PersistenceContext
	private EntityManager em;

	public ForumDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public Forum getForumByLearningId(Long learningId)  {
		final String sql = "SELECT f.id, f.forumName, f.learning.id FROM Forum f  WHERE f.learning.id = :learningId";

		final Object userObj = this.em.createQuery(sql).setParameter("learningId", learningId).getSingleResult();

		final Object[] userArr = (Object[]) userObj;

		Forum forum = null;

		if (userArr.length > 0) {
			forum = new Forum();
			forum.setId(Long.valueOf(userArr[0].toString()));
			forum.setForumName(userArr[1].toString());

			final Learning learning = new Learning();
			learning.setId(Long.valueOf(userArr[2].toString()));

			forum.setLearning(learning);

		}

		return forum;
	}

	@Override
	public Forum insert(Forum forum)  {
		em.persist(forum);
		return forum;
	}

	@Override
	public Forum getById(Long id) {
		final Forum forum = this.em.find(Forum.class, id);
		return forum;
	}

}
