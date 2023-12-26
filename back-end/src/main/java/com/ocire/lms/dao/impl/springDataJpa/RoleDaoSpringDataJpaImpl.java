package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.RoleDao;
import com.ocire.lms.model.Role;
import com.ocire.lms.repo.RoleRepo;

@Repository
@Profile("springdatajpa-query")
public class RoleDaoSpringDataJpaImpl implements RoleDao {
	@PersistenceContext
	private EntityManager em;
	private RoleRepo roleRepo;

	public RoleDaoSpringDataJpaImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	@Override
	public Role getByRoleCode(String roleCode) {
		Role role = roleRepo.getByRoleCode(roleCode);
		return role;
	}

	@Override
	public Role getById(Long id) {
		final Role role = roleRepo.findById(id).get();
		return role;
	}

	@Override
	public List<Role> getAllRole() {
		final List<Role> allRoles = roleRepo.findAll();
		return allRoles;
	}

}
