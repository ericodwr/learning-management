package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	 Role getByRoleCode(String roleCode);
}
