package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.question.QuestionResDto;
import com.ocire.lms.dto.question.QuestionTypeResDto;
import com.ocire.lms.model.QuestionFiles;

public interface QuestionService {
	List<QuestionResDto> getQuestionByTaskId(Long taskId);
	
	List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId);
	
	List<QuestionTypeResDto> getAllQuestionType();
}
