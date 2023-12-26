package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.ClassDao;
import com.ocire.lms.dao.ForumDao;
import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.learning.LearningInsertReqDto;
import com.ocire.lms.dto.learning.LearningResDto;
import com.ocire.lms.model.Forum;
import com.ocire.lms.model.Learning;
import com.ocire.lms.service.LearningService;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.utl.DateUtil;

@Service
public class LearningServiceImpl implements LearningService {
	private final LearningDao learningDao;
	private final ForumDao forumDao;
	private final ClassDao classDao;
	private final PrincipalService principalService;

	public LearningServiceImpl(LearningDao learningDao, ForumDao forumDao, ClassDao classDao,
			PrincipalService principalService) {
		this.learningDao = learningDao;
		this.forumDao = forumDao;
		this.classDao = classDao;
		this.principalService = principalService;

	}
	
	@Override
	public List<LearningResDto> getLearningByClassId(Long classId) {
		final List<LearningResDto> learningList = new ArrayList<>();
		learningDao.getLearningByClassId(classId).forEach(data -> {
			final LearningResDto learning = new LearningResDto();
			learning.setId(data.getId());
			learning.setLearningName(data.getLearningName());
			learning.setLearningCode(data.getLearningCode());
			learning.setStartTime(data.getStartTime().toLocalDate().toString());
			learning.setEndTime(data.getEndTime().toLocalDate().toString());
			learningList.add(learning);
		});
		return learningList;
	}

	@Transactional
	@Override
	public InsertResDto insertLearning(LearningInsertReqDto data) {
		final InsertResDto result = new InsertResDto();

		final Learning newLearning = new Learning();
		newLearning.setLearningName(data.getLearningName());
		newLearning.setLearningCode(data.getLearningCode());
		newLearning.setStartTime(DateUtil.parseStringToDate(data.getStartTime()));
		newLearning.setEndTime(DateUtil.parseStringToDate(data.getEndTime()));
		newLearning.setCreatedBy(principalService.getPrincipal());

		final com.ocire.lms.model.Class classLms = classDao.getById(data.getClassId());
		newLearning.setLearningClass(classLms);
		final Learning addedNewLearning = learningDao.insert(newLearning);

		final Forum newForum = new Forum();
		newForum.setForumName(data.getForumName());
		newForum.setLearning(addedNewLearning);
		newForum.setCreatedBy(principalService.getPrincipal());
		forumDao.insert(newForum);

		result.setId(addedNewLearning.getId());
		result.setMessage("Successfully create new Learning!");

		return result;
	}

}
