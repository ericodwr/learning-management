package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.QuestionDao;
import com.ocire.lms.dao.QuestionFilesDao;
import com.ocire.lms.dao.QuestionTypeDao;
import com.ocire.lms.dto.question.QuestionResDto;
import com.ocire.lms.dto.question.QuestionTypeResDto;
import com.ocire.lms.model.QuestionFiles;
import com.ocire.lms.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	private QuestionDao questionDao;
	private QuestionFilesDao questionFilesDao;
	private QuestionTypeDao questionTypeDao;

	public QuestionServiceImpl(QuestionDao questionDao, QuestionFilesDao questionFilesDao,QuestionTypeDao questionTypeDao) {
		this.questionDao = questionDao;
		this.questionFilesDao = questionFilesDao;
		this.questionTypeDao = questionTypeDao;
	}

	@Override
	public List<QuestionResDto> getQuestionByTaskId(Long taskId) {
		final List<QuestionResDto> questionList = new ArrayList<>();
		
		questionDao.getQuestionByTaskId(taskId).forEach(questions -> {
			final List<Long> questionFiles = new ArrayList<>();
			final QuestionResDto question = new QuestionResDto();
			question.setId(questions.getId());
			question.setQuestionName(questions.getQuestionName());
			question.setQuestionType(questions.getType().getTypeName());
			question.setQuestionFilesList(questionFiles);
			System.out.println(question.getId());
			final List<QuestionFiles> files = questionFilesDao.getQuestionFilesByQuestionId(questions.getId());
			System.out.println(files);
			if (files != null) {
				files.forEach(questfile -> {
					questionFiles.add(questfile.getId());
				});
			}
			question.setQuestionFilesList(questionFiles);
			questionList.add(question);
		});
		return questionList;
	}

	@Override
	public List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId) {
		return questionFilesDao.getQuestionFilesByQuestionId(questionId);
	}

	@Override
	public List<QuestionTypeResDto> getAllQuestionType() {
		List<QuestionTypeResDto> questionTypeList = new ArrayList<>();
		
		questionTypeDao.getAllType().forEach(t -> {
			final QuestionTypeResDto questionType = new QuestionTypeResDto();
			questionType.setTypeName(t.getTypeName());
			questionType.setId(t.getId());
			questionTypeList.add(questionType);
		});
		return questionTypeList;
	}
	
	

}
