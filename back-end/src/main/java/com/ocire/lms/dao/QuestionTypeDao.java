package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.QuestionType;

public interface QuestionTypeDao {
	QuestionType getById(Long id);
	
	List<QuestionType> getAllType();
}
