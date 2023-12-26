package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.UserDao;
import com.ocire.lms.model.User;
import com.ocire.lms.repo.UserRepo;

@Repository
@org.springframework.context.annotation.Profile("springdatajpa-query")
public class UserDaoSpringDataJpaImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;
	private UserRepo userRepo;

	public UserDaoSpringDataJpaImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User getUserByUsername(String username) {
		final User user = userRepo.getByUsername(username);
		return user;

	}

	@Override
	public User getById(Long id) {
		final User user = userRepo.findById(id).get();
		return user;
	}
	
	@Override
	public User insert(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final List<User> users = userRepo.getByRoleRoleCode(roleCode);
		return users;
	}

	@Override
	public List<User> getAll() {
		final List<User> users = userRepo.findAll();
		return users;
	}
}
