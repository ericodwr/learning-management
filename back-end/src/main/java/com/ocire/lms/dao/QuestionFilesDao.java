package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.QuestionFiles;

public interface QuestionFilesDao {
	List<QuestionFiles> getQuestionFilesByQuestionId(Long questionId);

	QuestionFiles insert(QuestionFiles questionFiles);
}
