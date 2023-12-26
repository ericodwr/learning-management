package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.task.TaskInsertReqDto;
import com.ocire.lms.dto.task.TaskResDto;
import com.ocire.lms.model.Task;

public interface TaskService {
	List<TaskResDto> getTaskByLearningId(Long learningId);
	
	TaskResDto getById(Long id);
	
	InsertResDto insertTask(List<TaskInsertReqDto> data);
}
