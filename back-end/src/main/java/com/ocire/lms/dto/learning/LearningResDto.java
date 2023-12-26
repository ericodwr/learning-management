package com.ocire.lms.dto.learning;

public class LearningResDto {
	private Long id;
	private String learningName;
	private String learningCode;
	private String startTime;
	private String endTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
