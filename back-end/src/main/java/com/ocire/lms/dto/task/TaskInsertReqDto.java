package com.ocire.lms.dto.task;

import java.util.List;

public class TaskInsertReqDto {
	private String taskName;
	private String taskCode;
	private Long learningId;
	private String startTime;
	private String endTime;

	private List<QuestionInsertReqDto> questionListReq;

	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<QuestionInsertReqDto> getQuestionListReq() {
		return questionListReq;
	}

	public void setQuestionListReq(List<QuestionInsertReqDto> questionListReq) {
		this.questionListReq = questionListReq;
	}

}
