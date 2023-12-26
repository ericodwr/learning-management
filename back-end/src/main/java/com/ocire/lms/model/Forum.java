package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_forum")
public class Forum extends BaseModel {
	
	@Column(name = "forum_name", length = 30, nullable = false)
	private String forumName;
	
	@OneToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

}
