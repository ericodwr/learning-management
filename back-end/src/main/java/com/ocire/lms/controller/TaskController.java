package com.ocire.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.task.TaskInsertReqDto;
import com.ocire.lms.dto.task.TaskResDto;
import com.ocire.lms.service.TaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("task")
public class TaskController {
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping
	public ResponseEntity<InsertResDto> insertLearning(@Valid @RequestBody List<TaskInsertReqDto> data) {
		final InsertResDto response = taskService.insertTask(data);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<TaskResDto>> getTaskByLearningId(@RequestParam("learningId") Long id) {
		final List<TaskResDto> data = taskService.getTaskByLearningId(id);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("detail")
	public ResponseEntity<TaskResDto> getTaskById(@RequestParam("id") Long id) {
		final TaskResDto data = taskService.getById(id);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	

}
