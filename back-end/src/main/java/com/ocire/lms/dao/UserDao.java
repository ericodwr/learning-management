package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.User;

public interface UserDao {
	List<User> getAll();

	User getUserByUsername(String username);

	User getById(Long id);

	User insert(User user);
	
	List<User> getUserByRoleCode(String roleCode);
}
