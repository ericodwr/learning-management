package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Role;

public interface RoleDao {
	Role getByRoleCode(String roleCode);
	
	Role getById(Long id);
	
	List<Role> getAllRole();
}
