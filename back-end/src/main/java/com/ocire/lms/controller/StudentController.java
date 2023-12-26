package com.ocire.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.UpdateResDto;
import com.ocire.lms.dto.student.AnswerInsertReqDto;
import com.ocire.lms.dto.student.AttendanceInsertReqDto;
import com.ocire.lms.dto.student.AttendanceResDto;
import com.ocire.lms.dto.student.EnrollInsertReqDto;
import com.ocire.lms.dto.student.ReviewInsertReqDto;
import com.ocire.lms.dto.user.UserInsertReqDto;
import com.ocire.lms.service.EnrollService;
import com.ocire.lms.service.QuestionAnswerService;
import com.ocire.lms.service.ReviewService;
import com.ocire.lms.service.StudentAttendanceService;
import com.ocire.lms.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("student")
public class StudentController {
	private final UserService userService;
	private final EnrollService enrollService;
	private final QuestionAnswerService questionAnswerService;
	private final StudentAttendanceService studentAttendanceService;
	private final ReviewService reviewService;

	public StudentController(UserService userService, EnrollService enrollService,
			QuestionAnswerService questionAnswerService, StudentAttendanceService studentAttendanceService,
			ReviewService reviewService) {
		this.userService = userService;
		this.enrollService = enrollService;
		this.questionAnswerService = questionAnswerService;
		this.studentAttendanceService = studentAttendanceService;
		this.reviewService = reviewService;
	}

	@PostMapping("create")
	public ResponseEntity<InsertResDto> insertUser(@Valid @RequestBody UserInsertReqDto data) {
		final InsertResDto response = userService.insert(data, "Student");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("enroll")
	public ResponseEntity<InsertResDto> enrollUser(@Valid @RequestBody EnrollInsertReqDto data) {
		final InsertResDto response = enrollService.insertEnroll(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("answer")
	public ResponseEntity<InsertResDto> answerQuestion(@Valid @RequestBody List<AnswerInsertReqDto> data) {
		final InsertResDto response = questionAnswerService.insertAnswer(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping("attendance")
	public ResponseEntity<UpdateResDto> updateAttendance(@Valid @RequestBody AttendanceInsertReqDto data) {
		final UpdateResDto response = studentAttendanceService.updateStudentAttendanceStatus(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("attendance")
	public ResponseEntity<InsertResDto> insertAttendance(@Valid @RequestBody AttendanceInsertReqDto data) {
		final InsertResDto response = studentAttendanceService.insertStudentAttendance(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("attendance")
	public ResponseEntity<List<AttendanceResDto>> getAttendance(@Valid @RequestParam("learningId") Long id) {
		final List<AttendanceResDto> response = studentAttendanceService.getStudentAttendanceByLearningId(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("review")
	public ResponseEntity<InsertResDto> reviewStudent(@Valid @RequestBody ReviewInsertReqDto data) {
		final InsertResDto response = reviewService.insertReview(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
