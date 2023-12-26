package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.TaskDao;
import com.ocire.lms.model.Task;

@Repository
@Profile("hql-query")
public class TaskDaoHqlImpl implements TaskDao {
	@PersistenceContext
	private EntityManager em;

	public TaskDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<Task> getTaskByLearningId(Long learningId) {
		final String sql = "SELECT t FROM Task t WHERE t.learning.id = :learningId";

		final List<Task> taskList = this.em.createQuery(sql, Task.class).setParameter("learningId", learningId)
				.getResultList();

		return taskList;
	}

	@Override
	public Task getById(Long id) {
		final Task task = this.em.find(Task.class, id);
		return task;
	}
	
	@Override
	public Task insert(Task task) {
		em.persist(task);
		return task;
	}

}
