package com.ocire.lms.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocire.lms.dto.login.LoginReqDto;
import com.ocire.lms.dto.login.LoginResDto;
import com.ocire.lms.service.JwtService;
import com.ocire.lms.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("login")
public class LoginController {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	public LoginController(UserService userService, AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@PostMapping
	public ResponseEntity<?> login(@RequestBody final LoginReqDto user) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

		authenticationManager.authenticate(auth);
		final LoginResDto userLogin = userService.login(user);

		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);

		final Map<String, Object> claims = new HashMap<>();
		claims.put("exp", cal.getTime());
		claims.put("id", userLogin.getId());
		
		final LoginResDto loginRes = new LoginResDto();
		loginRes.setToken(jwtService.generateJwt(claims));
		loginRes.setFullName(userLogin.getFullName());
		loginRes.setId(userLogin.getId());
		loginRes.setRoleName(userLogin.getRoleName());
		loginRes.setRoleCode(userLogin.getRoleCode());
		loginRes.setPhotoId(userLogin.getPhotoId());

		return new ResponseEntity<>(loginRes, HttpStatus.OK);
	}
}