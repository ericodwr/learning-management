package com.ocire.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.classlms.ClassInsertReqDto;
import com.ocire.lms.dto.classlms.ClassResDto;
import com.ocire.lms.service.ClassService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("class")
public class ClassController {
	private final ClassService classService;

	public ClassController(ClassService classService) {
		this.classService = classService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertClass(@Valid @RequestBody ClassInsertReqDto data) {
		final InsertResDto response = classService.insertClass(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ClassResDto>> getAll() {
		final List<ClassResDto> data = classService.getAllClasses();

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("student")
	public ResponseEntity<List<ClassResDto>> getStudent() {
		final List<ClassResDto> data = classService.getClassByStudent();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("student/unenroll")
	public ResponseEntity<List<ClassResDto>> getStudentEnroll() {
		final List<ClassResDto> data = classService.getClassByStudentId();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("teacher")
	public ResponseEntity<List<ClassResDto>> getTeacher() {
		final List<ClassResDto> data = classService.getClassByTeacherId();

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

}
