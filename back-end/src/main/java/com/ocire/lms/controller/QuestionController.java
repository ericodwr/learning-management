package com.ocire.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.question.QuestionResDto;
import com.ocire.lms.dto.question.QuestionTypeResDto;
import com.ocire.lms.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("question")
public class QuestionController {
	private final QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@GetMapping("task")
	public ResponseEntity<List<QuestionResDto>> getQuestionByTask(@RequestParam("id") Long id) {
		final List<QuestionResDto> data = questionService.getQuestionByTaskId(id);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("type")
	public ResponseEntity<List<QuestionTypeResDto>> getQuestionType() {
		final List<QuestionTypeResDto> data = questionService.getAllQuestionType();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
