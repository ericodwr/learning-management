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
import com.ocire.lms.dto.learning.LearningInsertReqDto;
import com.ocire.lms.dto.learning.LearningResDto;
import com.ocire.lms.service.LearningService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("learning")
public class LearningController {
	private final LearningService learningService;

	public LearningController(LearningService learningService) {
		this.learningService = learningService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertLearning(@Valid @RequestBody LearningInsertReqDto data) {
		final InsertResDto response = learningService.insertLearning(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<LearningResDto>> getByRoleCode(@RequestParam("id") Long id) {
		final List<LearningResDto> data = learningService.getLearningByClassId(id);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
