package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ocire.lms.constant.RoleType;
import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dao.ProfileDao;
import com.ocire.lms.dao.RoleDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.UpdateResDto;
import com.ocire.lms.dto.login.LoginReqDto;
import com.ocire.lms.dto.login.LoginResDto;
import com.ocire.lms.dto.user.UserInsertReqDto;
import com.ocire.lms.dto.user.UserUpdateReqDto;
import com.ocire.lms.dto.user.UsersResDto;
import com.ocire.lms.model.Profile;
import com.ocire.lms.model.Role;
import com.ocire.lms.model.User;
import com.ocire.lms.service.MailService;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.service.UserService;
import com.ocire.lms.utl.RandomGenerateStrUtil;

@Service
public class UserServiceImpl implements UserService {
	@PersistenceContext
	private EntityManager em;
	private final UserDao userDao;
	private final FileDao fileDao;
	private final ProfileDao profileDao;
	private final RoleDao roleDao;
	private final PasswordEncoder encoder;
	private final PrincipalService principalService;
	private final MailService mailService;

	public UserServiceImpl(UserDao userDao, FileDao fileDao, ProfileDao profileDao, PrincipalService principalService,
			RoleDao roleDao, MailService mailService, PasswordEncoder encoder) {
		this.userDao = userDao;
		this.fileDao = fileDao;
		this.profileDao = profileDao;
		this.principalService = principalService;
		this.roleDao = roleDao;
		this.mailService = mailService;
		this.encoder = encoder;
	}

	@Override
	public LoginResDto login(LoginReqDto data) {

		final User user = userDao.getUserByUsername(data.getUsername());

		final LoginResDto userRes = new LoginResDto();

		userRes.setId(user.getId());
		userRes.setFullName(user.getProfile().getFullName());
		userRes.setRoleName(user.getRole().getRoleName());
		userRes.setRoleCode(user.getRole().getRoleCode());
		return userRes;
	}

	@Override
	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto userInsertReqDto) {
		final InsertResDto result = new InsertResDto();

		final Profile profile = new Profile();
		profile.setFullName(userInsertReqDto.getFullName());
		profile.setPhoneNumb(userInsertReqDto.getPhoneNumb());
		profile.setCreatedBy(0L);

		final Profile profileResult = profileDao.insert(profile);

		final User user = new User();
		user.setUsername(userInsertReqDto.getUsername());

		final String password = RandomGenerateStrUtil.getAlphaNumericString(5);

		// generate encoded passworded
		final String encodedPassword = encoder.encode(password);
		user.setUserPassword(encodedPassword);
		user.setProfile(profileResult);
		user.setCreatedBy(principalService.getPrincipal());

		final Role role = roleDao.getById(userInsertReqDto.getRoleId());

		user.setRole(role);

		mailService.sendEmail(userInsertReqDto.getUsername(), "Created Account For LMS as " + role.getRoleName(),
				"Please login with this password: " + password);

		final User userResult = userDao.insert(user);
		result.setId(userResult.getId());
		result.setMessage("Successfully create user!");

		return result;
	}

	@Override
	public List<UsersResDto> getUserByRoleCode(String roleCode) {
		final List<UsersResDto> userList = new ArrayList<>();

		userDao.getUserByRoleCode(roleCode).forEach(u -> {
			final UsersResDto data = new UsersResDto();
			data.setId(u.getId());
			data.setFullName(u.getProfile().getFullName());
			data.setRoleCode(u.getRole().getRoleCode());
			data.setRoleName(u.getRole().getRoleName());
			userList.add(data);
		});
		return userList;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getUserByUsername(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException("failed to login");
	}

	@Override
	public List<UsersResDto> getAll() {
		final List<UsersResDto> userList = new ArrayList<>();

		userDao.getAll().forEach(u -> {
			final UsersResDto data = new UsersResDto();
			data.setUsername(u.getUsername());
			data.setIsActive(u.getIsActive());
			data.setId(u.getId());
			data.setFullName(u.getProfile().getFullName());
			data.setRoleCode(u.getRole().getRoleCode());
			data.setRoleName(u.getRole().getRoleName());
			userList.add(data);
		});
		return userList;
	}

	@Override
	public UpdateResDto update(UserUpdateReqDto data) {
		UpdateResDto userResult = new UpdateResDto();

		User updatedUser = userDao.getById(data.getId());
		updatedUser.setIsActive(data.getIsActive());

		userResult.setVer(updatedUser.getVer());
		userResult.setMessage("Updated Successfully!");

		return userResult;
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto userInsertReqDto, String student) {
		final InsertResDto result = new InsertResDto();

		final Profile profile = new Profile();
		profile.setFullName(userInsertReqDto.getFullName());
		profile.setPhoneNumb(userInsertReqDto.getPhoneNumb());

		profile.setCreatedBy(0L);

		final Profile profileResult = profileDao.insert(profile);

		final User user = new User();
		user.setUsername(userInsertReqDto.getUsername());

		final String password = RandomGenerateStrUtil.getAlphaNumericString(5);

		// generate encoded passworded
		final String encodedPassword = encoder.encode(password);
		user.setUserPassword(encodedPassword);
		user.setProfile(profileResult);
		user.setCreatedBy(0L);

		final Role role = roleDao.getByRoleCode(RoleType.STUDENT.roleCode);

		user.setRole(role);

		mailService.sendEmail(userInsertReqDto.getUsername(), "Created Account For LMS as " + role.getRoleName(),
				"Please login with this password: " + password);

		final User userResult = userDao.insert(user);
		result.setId(userResult.getId());
		result.setMessage("Successfully create user!");

		return result;
	}

}
