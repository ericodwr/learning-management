package com.ocire.lms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.UserDao;
import com.ocire.lms.model.Profile;
import com.ocire.lms.model.Role;
import com.ocire.lms.model.User;

@Repository
@org.springframework.context.annotation.Profile("native-query")
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	public UserDaoImpl(SessionFactory factory) {
	}

	@Override
	public User getUserByUsername(String username) {
		final String sql = "SELECT u.id, u.user_password, r.role_code, r.role_name, p.full_name  " + "FROM t_user u "
				+ "INNER JOIN t_role r " + "ON u.role_id = r.id " + "INNER JOIN t_profile p "
				+ "ON p.id = u.profile_id " + "WHERE u.username = :username ";

		final Object userObj = this.em.createNativeQuery(sql).setParameter("username", username).getSingleResult();

		final Object[] userArr = (Object[]) userObj;

		User user = null;

		if (userArr.length > 0) {

			user = new User();
			user.setId(Long.valueOf(userArr[0].toString()));
			user.setUserPassword(userArr[1].toString());

			Role role = new Role();
			role.setRoleCode(userArr[2].toString());
			role.setRoleName(userArr[3].toString());
			user.setRole(role);

			Profile profile = new Profile();
			profile.setFullName(userArr[4].toString());

			user.setProfile(profile);
		}

		return user;

	}
	
	@Override
	public User getById(Long id) {
		final User user = this.em.find(User.class, id);
		return user;
	}

	@Override
	public User insert(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public List<User> getUserByRoleCode(String roleCode) {
		final List<User> users = new ArrayList<>();

		final String sql = "SELECT u.id, p.full_name FROM t_user u " + "INNER JOIN t_role "
				+ "ON t_role.id = u.role_id " + "INNER JOIN t_profile p " + "ON p.id = u.profile_id "
				+ "WHERE t_role.role_code = :roleCode";

		final List<?> userObjs = this.em.createNativeQuery(sql).setParameter("roleCode", roleCode).getResultList();

		if (userObjs.size() > 0) {
			for (Object userObj : userObjs) {
				final Object[] userArr = (Object[]) userObj;
				final User user = new User();
				user.setId(Long.valueOf(userArr[0].toString()));

				final Role role = new Role();
				role.setRoleCode(userArr[1].toString());

				final Profile profile = new Profile();
				profile.setFullName(userArr[1].toString());

				user.setProfile(profile);
				user.setRole(role);
				users.add(user);
			}
		}

		return users;
	}
	
	@Override
	public List<User> getAll() {
		final String sql = "SELECT * FROM t_user";

		@SuppressWarnings("unchecked")
		final List<User> users = this.em.createNativeQuery(sql, User.class).getResultList();

		return users;
	}
}
