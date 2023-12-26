package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Task;

public interface TaskDao {
	List<Task> getTaskByLearningId(Long learningId);

	Task getById(Long id);

	Task insert(Task task);
}
