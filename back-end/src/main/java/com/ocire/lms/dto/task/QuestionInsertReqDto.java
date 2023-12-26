package com.ocire.lms.dto.task;

import java.util.List;

public class QuestionInsertReqDto {
	private String questionName;
	private Long typeId;

	private List<QuestionFilesInsertReqDto> questionFilesReq;

	public String getQuestionName() {
		return questionName;
	}
	
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public List<QuestionFilesInsertReqDto> getQuestionFilesReq() {
		return questionFilesReq;
	}

	public void setQuestionFilesReq(List<QuestionFilesInsertReqDto> questionFilesReq) {
		this.questionFilesReq = questionFilesReq;
	}

}
