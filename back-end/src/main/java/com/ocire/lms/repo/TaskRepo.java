package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
	List<Task> getByLearningId(Long id);
}
