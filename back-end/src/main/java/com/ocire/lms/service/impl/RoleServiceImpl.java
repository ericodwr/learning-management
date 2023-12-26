package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.RoleDao;
import com.ocire.lms.dto.role.RoleResDto;
import com.ocire.lms.model.Role;
import com.ocire.lms.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleDao roleDao;

	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role getByRoleCode(String roleCode) {
		return roleDao.getByRoleCode(roleCode);
	}

	@Override
	public List<RoleResDto> getAllRole() {
		final List<RoleResDto> allRoles = new ArrayList<>();
		roleDao.getAllRole().forEach(r -> {
			final RoleResDto role = new RoleResDto();
			role.setId(r.getId());
			role.setRoleName(r.getRoleName());
			role.setRoleCode(r.getRoleCode());
			allRoles.add(role);
		});
		return allRoles;
	}

}
