package com.ocire.lms.dto.learning;

public class LearningInsertReqDto {
	private String learningName;
	private String learningCode;
	private String forumName;
	private Long classId;
	private String startTime;
	private String endTime;

	public String getLearningName() {
		return learningName;
	}

	public void setLearningName(String learningName) {
		this.learningName = learningName;
	}

	public String getLearningCode() {
		return learningCode;
	}

	public void setLearningCode(String learningCode) {
		this.learningCode = learningCode;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
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

}
