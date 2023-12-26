package com.ocire.lms.dto.student;

import java.util.List;

public class AnswerInsertReqDto {
	private String essayAnswer;
	private Long questionId;

	List<AnswerFilesInsertReqDto> answerFiles;

	public String getEssayAnswer() {
		return essayAnswer;
	}
	
	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public List<AnswerFilesInsertReqDto> getAnswerFiles() {
		return answerFiles;
	}

	public void setAnswerFiles(List<AnswerFilesInsertReqDto> answerFiles) {
		this.answerFiles = answerFiles;
	}

}
