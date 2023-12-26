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
import com.ocire.lms.dto.forum.CommentInsertReqDto;
import com.ocire.lms.dto.forum.CommentResDto;
import com.ocire.lms.service.ForumService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("forum")
public class ForumController {
	private final ForumService forumService;

	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}

	@GetMapping("comment")
	public ResponseEntity<List<CommentResDto>> getCommentByForumId(@RequestParam("id") Long id) {
		final List<CommentResDto> data = forumService.getCommentByForumId(id);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertComment(@Valid @RequestBody CommentInsertReqDto data) {
		final InsertResDto response = forumService.insertComment(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
