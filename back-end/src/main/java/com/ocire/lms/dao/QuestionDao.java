package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Question;

public interface QuestionDao {
	List<Question> getQuestionByTaskId(Long taskId);

	Question insert(Question question);
	
	Question getById(Long id);
}
