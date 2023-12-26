package com.ocire.lms.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_learning")
public class Learning extends BaseModel {
	@Column(name = "learning_name", length = 40, nullable = false)
	private String learningName;

	@Column(name = "learning_code", length = 5, nullable = false)
	private String learningCode;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class learningClass;
	
	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

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

	public Class getLearningClass() {
		return learningClass;
	}

	public void setLearningClass(Class learningClass) {
		this.learningClass = learningClass;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}
