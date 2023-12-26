package com.ocire.lms.dto.forum;

public class CommentResDto {
	private String forumChat;
	private String fullName;
	private Long userId;
	private String createdAt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getForumChat() {
		return forumChat;
	}

	public void setForumChat(String forumChat) {
		this.forumChat = forumChat;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
