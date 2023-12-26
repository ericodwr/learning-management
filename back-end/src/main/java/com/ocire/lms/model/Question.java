package com.ocire.lms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_question")
public class Question extends BaseModel {
	@Column(name = "question_name", nullable = false)
	private String questionName;

	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private QuestionType type;

	@Transient
	private List<QuestionFiles> questionFiles;

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public List<QuestionFiles> getQuestionFiles() {
		return questionFiles;
	}

	public void setQuestionFiles(List<QuestionFiles> questionFiles) {
		this.questionFiles = questionFiles;
	}

}
