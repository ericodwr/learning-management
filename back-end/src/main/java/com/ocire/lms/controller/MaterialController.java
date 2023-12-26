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
import com.ocire.lms.dto.material.MaterialInsertReqDto;
import com.ocire.lms.dto.material.MaterialResDto;
import com.ocire.lms.service.MaterialService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("material")
public class MaterialController {
	private final MaterialService materialService;

	public MaterialController(MaterialService materialService) {
		this.materialService = materialService;
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertMaterial(@Valid @RequestBody List<MaterialInsertReqDto> data) {
		final InsertResDto response = materialService.insertMaterial(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<MaterialResDto>> getMaterialByLearningId(@RequestParam("learningId") Long id) {
		final List<MaterialResDto> data = materialService.getMaterialsByLearningId(id);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@GetMapping("detail")
	public ResponseEntity<MaterialResDto> getMaterialById(@RequestParam("id") Long id) {
		final MaterialResDto data = materialService.getById(id);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	

}
