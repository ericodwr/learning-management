package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.AnswerInsertReqDto;
import com.ocire.lms.model.QuestionAnswer;
import com.ocire.lms.model.QuestionAnswerFiles;

public interface QuestionAnswerService {
	QuestionAnswer insertEssayAnswer(QuestionAnswer questionAnswer);

	QuestionAnswerFiles insertAnswerFiles(QuestionAnswerFiles questionAnswerFiles);
	
	InsertResDto insertAnswer(List<AnswerInsertReqDto> data);

	List<QuestionAnswer> getQuestionAnswerByStudent(Long studentId);

	List<QuestionAnswerFiles> getQuestionAnswerFiles(Long questionAnswerId);
}
