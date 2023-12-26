package com.ocire.lms.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.ReviewDao;
import com.ocire.lms.dao.TaskDao;
import com.ocire.lms.dao.UserDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.ReviewInsertReqDto;
import com.ocire.lms.model.Review;
import com.ocire.lms.model.Task;
import com.ocire.lms.model.User;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	private ReviewDao reviewDao;
	private PrincipalService principalService;
	private UserDao userDao;
	private TaskDao taskDao;

	@PersistenceContext
	private EntityManager em;

	public ReviewServiceImpl(ReviewDao reviewDao, UserDao userDao, TaskDao taskDao, PrincipalService principalService) {
		this.reviewDao = reviewDao;
		this.userDao = userDao;
		this.taskDao = taskDao;
		this.principalService = principalService;

	}
	
	@Transactional
	@Override
	public InsertResDto insertReview(ReviewInsertReqDto data) {
		final InsertResDto result = new InsertResDto();

		Review newReview = new Review();
		newReview.setScore(data.getScore());
		newReview.setNotes(data.getNotes());
		newReview.setCreatedBy(principalService.getPrincipal());

		final User teacher = userDao.getById(principalService.getPrincipal());
		final User student = userDao.getById(data.getStudentId());
		final Task task = taskDao.getById(data.getTaskId());

		newReview.setStudent(student);
		newReview.setTeacher(teacher);
		newReview.setTask(task);

		final Review addedReview = reviewDao.insert(newReview);

		result.setId(addedReview.getId());
		result.setMessage("Successfully review candidate!");

		return result;
	}

}
