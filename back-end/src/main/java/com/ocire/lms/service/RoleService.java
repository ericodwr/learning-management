package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.role.RoleResDto;
import com.ocire.lms.model.Role;

public interface RoleService {
	Role getByRoleCode(String roleCode);
	
	List<RoleResDto> getAllRole();
}
