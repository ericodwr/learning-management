package com.ocire.lms.dto.student;

public class AttendanceInsertReqDto {
	private Long learningId;
	private Long studentId;
	private Boolean approvement;
	
	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Boolean getApprovement() {
		return approvement;
	}

	public void setApprovement(Boolean approvement) {
		this.approvement = approvement;
	}

}
