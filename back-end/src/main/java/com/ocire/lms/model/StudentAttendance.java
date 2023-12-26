package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_student_attendance")
public class StudentAttendance extends BaseModel {
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;

	@ManyToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;

	@Column(name = "approvement", nullable = false)
	private Boolean approvement;
	
	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

	public Boolean getApprovement() {
		return approvement;
	}

	public void setApprovement(Boolean approvement) {
		this.approvement = approvement;
	}

}
