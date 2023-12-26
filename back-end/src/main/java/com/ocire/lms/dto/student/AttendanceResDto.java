package com.ocire.lms.dto.student;

public class AttendanceResDto {
	private Long studentId;
	private String studentName;
	private Long learningId;
	private Boolean approvement;
	private String createdAt;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Long getLearningId() {
		return learningId;
	}
	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}
	public Boolean getApprovement() {
		return approvement;
	}
	public void setApprovement(Boolean approvement) {
		this.approvement = approvement;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
}
