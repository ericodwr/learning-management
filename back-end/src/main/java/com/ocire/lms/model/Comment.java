package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_comment")
public class Comment extends BaseModel {
	
	@Column(name = "forum_chat", nullable = true)
	private String forumChat;
	
	@ManyToOne
	@JoinColumn(name = "forum_id")
	private Forum forum;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getForumChat() {
		return forumChat;
	}

	public void setForumChat(String forumChat) {
		this.forumChat = forumChat;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
