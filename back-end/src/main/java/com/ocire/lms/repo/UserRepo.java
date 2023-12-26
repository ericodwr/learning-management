package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User getByUsername(String username);
	
	List<User> getByRoleRoleCode(String roleCode);
}
