package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.dao.QuestionFilesDao;
import com.ocire.lms.dao.QuestionTypeDao;
import com.ocire.lms.dao.TaskDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.task.TaskInsertReqDto;
import com.ocire.lms.dto.task.TaskResDto;
import com.ocire.lms.model.File;
import com.ocire.lms.model.Learning;
import com.ocire.lms.model.Question;
import com.ocire.lms.model.QuestionFiles;
import com.ocire.lms.model.QuestionType;
import com.ocire.lms.model.Task;
import com.ocire.lms.service.PrincipalService;
import com.ocire.lms.service.TaskService;
import com.ocire.lms.utl.DateUtil;

@Service
public class TaskServiceImpl implements TaskService {
	private TaskDao taskDao;
	private FileDao fileDao;
	private QuestionDao questionDao;
	private QuestionFilesDao questionFilesDao;
	private QuestionTypeDao questionTypeDao;
	private LearningDao learningDao;
	private PrincipalService principalService;

	public TaskServiceImpl(TaskDao taskDao, FileDao fileDao, QuestionDao questionDao, QuestionFilesDao questionFilesDao,
			LearningDao learningDao, PrincipalService principalService, QuestionTypeDao questionTypeDao) {
		this.taskDao = taskDao;
		this.fileDao = fileDao;
		this.questionDao = questionDao;
		this.questionFilesDao = questionFilesDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
		this.questionTypeDao = questionTypeDao;
	}

	@Override
	public List<TaskResDto> getTaskByLearningId(Long learningId) {
		final List<TaskResDto> taskList = new ArrayList<>();
		taskDao.getTaskByLearningId(learningId).forEach(t -> {
			final TaskResDto task = new TaskResDto();
			task.setId(t.getId());
			task.setTaskName(t.getTaskName());
			task.setTaskCode(t.getTaskCode());
			task.setStartTime(t.getStartTime().toString());
			task.setEndTime(t.getEndTime().toString());
			task.setLearningId(t.getLearning().getId());
			taskList.add(task);
		});
		return taskList;
	}

	@Override
	public TaskResDto getById(Long id) {
		final Task task = taskDao.getById(id);
		final TaskResDto taskRes = new TaskResDto();
		taskRes.setId(task.getId());
		taskRes.setTaskName(task.getTaskName());
		taskRes.setTaskCode(task.getTaskCode());
		taskRes.setStartTime(task.getStartTime().toString());
		taskRes.setEndTime(task.getEndTime().toString());
		taskRes.setLearningId(task.getLearning().getId());
		return taskRes;
	}

	@Transactional
	@Override
	public InsertResDto insertTask(List<TaskInsertReqDto> dataList) {
		final InsertResDto result = new InsertResDto();

		dataList.forEach(data -> {
			final Learning learning = learningDao.getById(data.getLearningId());

			final Task task = new Task();
			task.setLearning(learning);
			task.setCreatedBy(principalService.getPrincipal());
			task.setTaskName(data.getTaskName());
			task.setTaskCode(data.getTaskCode());
			task.setStartTime(DateUtil.parseStringToDate(data.getStartTime()));
			task.setEndTime(DateUtil.parseStringToDate(data.getEndTime()));
			final Task newTask = taskDao.insert(task);

			if (data.getQuestionListReq() != null) {
				data.getQuestionListReq().forEach(questionData -> {
					final QuestionType type = questionTypeDao.getById(questionData.getTypeId());

					final Question question = new Question();
					question.setTask(newTask);
					question.setCreatedBy(principalService.getPrincipal());
					question.setQuestionName(questionData.getQuestionName());
					question.setType(type);

					final Question newQuestion = questionDao.insert(question);

					if (questionData.getQuestionFilesReq() != null) {
						questionData.getQuestionFilesReq().forEach(questionFiles -> {
							final File file = new File();
							file.setFileName(questionFiles.getFileName());
							file.setFileExtens(questionFiles.getFileExtens());
							file.setCreatedBy(principalService.getPrincipal());

							final File newFile = fileDao.insert(file);
							final QuestionFiles questionFile = new QuestionFiles();
							questionFile.setFile(newFile);
							questionFile.setQuestion(newQuestion);
							questionFile.setCreatedBy(principalService.getPrincipal());
							questionFilesDao.insert(questionFile);
						});
					}
				});
			}
		});
		result.setId(null);
		result.setMessage("Create Task Successfully!");
		return result;
	}

}
