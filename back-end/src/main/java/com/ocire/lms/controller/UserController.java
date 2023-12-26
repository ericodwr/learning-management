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
import com.ocire.lms.dto.role.RoleResDto;
import com.ocire.lms.dto.user.UserInsertReqDto;
import com.ocire.lms.dto.user.UserUpdateReqDto;
import com.ocire.lms.dto.user.UsersResDto;
import com.ocire.lms.service.RoleService;
import com.ocire.lms.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UserController {
	private final UserService userService;
	private final RoleService roleService;

	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping
	public ResponseEntity<List<UsersResDto>> getAll() {
		final List<UsersResDto> data = userService.getAll();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/role")
	public ResponseEntity<List<UsersResDto>> getByRoleCode(@RequestParam("code") String roleCode) {
		final List<UsersResDto> data = userService.getUserByRoleCode(roleCode);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertResDto> insertUser(@Valid @RequestBody UserInsertReqDto data) {
		final InsertResDto response = userService.insert(data);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<UpdateResDto> updateUser(@Valid @RequestBody UserUpdateReqDto data) {
		final UpdateResDto response = userService.update(data);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/roles")
	public ResponseEntity<List<RoleResDto>> getAllRoles() {
		final List<RoleResDto> response = roleService.getAllRole();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
