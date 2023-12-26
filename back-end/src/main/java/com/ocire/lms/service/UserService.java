package com.ocire.lms.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.UpdateResDto;
import com.ocire.lms.dto.login.LoginReqDto;
import com.ocire.lms.dto.login.LoginResDto;
import com.ocire.lms.dto.user.UserInsertReqDto;
import com.ocire.lms.dto.user.UserUpdateReqDto;
import com.ocire.lms.dto.user.UsersResDto;
import com.ocire.lms.model.User;

public interface UserService extends UserDetailsService {
	List<UsersResDto> getAll();

	LoginResDto login(LoginReqDto data);

	UpdateResDto update(UserUpdateReqDto data);

	User getById(Long id);

	InsertResDto insert(UserInsertReqDto data);

	InsertResDto insert(UserInsertReqDto data, String student);

	List<UsersResDto> getUserByRoleCode(String roleCode);
}
