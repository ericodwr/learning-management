package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.TaskDao;
import com.ocire.lms.model.Task;
import com.ocire.lms.repo.TaskRepo;

@Repository
@Profile("springdatajpa-query")
public class TaskDaoSpringDataJpaImpl implements TaskDao {
	@PersistenceContext
	private EntityManager em;
	private TaskRepo taskRepo;

	public TaskDaoSpringDataJpaImpl(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}

	@Override
	public List<Task> getTaskByLearningId(Long learningId) {

		final List<Task> taskList = taskRepo.getByLearningId(learningId);

		return taskList;
	}

	@Override
	public Task getById(Long id) {
		final Task task = taskRepo.findById(id).get();
		return task;
	}
	
	@Override
	public Task insert(Task task) {
		taskRepo.save(task);
		return task;
	}

}
