package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.QuestionAnswer;

public interface QuestionAnswerDao {
	QuestionAnswer insert(QuestionAnswer questionAnswer) ;
	
	List<QuestionAnswer> getByCreatedBy(Long id) ;
}
