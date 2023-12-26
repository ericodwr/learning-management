package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.QuestionAnswerFiles;

public interface QuestionAnswerFilesDao {
	QuestionAnswerFiles insert(QuestionAnswerFiles questionAnswerFiles);

	List<QuestionAnswerFiles> getByAnswerId(Long id);
}
