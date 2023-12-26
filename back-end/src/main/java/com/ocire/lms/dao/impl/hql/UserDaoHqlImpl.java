package com.ocire.lms.dao.impl.hql;

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
@org.springframework.context.annotation.Profile("hql-query")
public class UserDaoHqlImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	public UserDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public User getUserByUsername(String username) {
		final String sql = "SELECT u.id, u.userPassword, u.role.roleCode, u.role.roleName, u.profile.fullName  "
				+ "FROM User u " + "WHERE u.username = :username ";

		final Object userObj = this.em.createQuery(sql).setParameter("username", username).getSingleResult();

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

		final String sql = "SELECT u.id, u.profile.fullName FROM User u " + "WHERE u.role.roleCode = :roleCode";

		final List<?> userObjs = this.em.createQuery(sql).setParameter("roleCode", roleCode).getResultList();
		
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
		final String sql = "SELECT u FROM User AS u";

		final List<User> users = this.em.createQuery(sql, User.class).getResultList();

		return users;
	}
}
