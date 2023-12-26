package com.ocire.lms.dto.forum;

public class CommentInsertReqDto {
	private String forumChat;
	private Long forumId;

	public String getForumChat() {
		return forumChat;
	}

	public void setForumChat(String forumChat) {
		this.forumChat = forumChat;
	}

	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
