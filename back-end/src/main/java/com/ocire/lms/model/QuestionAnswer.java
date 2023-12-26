package com.ocire.lms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_question_answer")
public class QuestionAnswer extends BaseModel {
	@Column(name = "essay_answer", nullable = false)
	private String essayAnswer;
	
	@ManyToOne
	@JoinColumn(name = "id_question")
	private Question question;
	
	@Transient
	private List<QuestionAnswerFiles> questionAnswerFiles;

	public String getEssayAnswer() {
		return essayAnswer;
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<QuestionAnswerFiles> getQuestionAnswerFiles() {
		return questionAnswerFiles;
	}

	public void setQuestionAnswerFiles(List<QuestionAnswerFiles> questionAnswerFiles) {
		this.questionAnswerFiles = questionAnswerFiles;
	}

}
