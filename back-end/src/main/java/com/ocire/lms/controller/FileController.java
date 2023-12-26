package com.ocire.lms.controller;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.files.FilesInsertReqDto;
import com.ocire.lms.model.File;
import com.ocire.lms.service.FileService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("files")
public class FileController {
	private com.ocire.lms.service.FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getFileById(@PathVariable("id") Long id) {
		final File file = fileService.getById(id);
		final String fileName = "attachment";
		final byte[] fileBytes = Base64.getDecoder().decode(file.getFileName());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName+"." + file.getFileExtens())
				.body(fileBytes);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertFile(@RequestBody FilesInsertReqDto data) {
		final InsertResDto result = fileService.insertFile(data);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
