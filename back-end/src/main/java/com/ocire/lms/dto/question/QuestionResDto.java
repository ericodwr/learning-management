package com.ocire.lms.dto.question;

import java.util.List;

public class QuestionResDto {
	private Long id;
	private String questionName;
	private String questionType;

	List<Long> questionFilesList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<Long> getQuestionFilesList() {
		return questionFilesList;
	}

	public void setQuestionFilesList(List<Long> questionFilesList) {
		this.questionFilesList = questionFilesList;
	}

}
