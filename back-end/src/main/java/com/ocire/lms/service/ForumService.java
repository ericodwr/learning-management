package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.forum.CommentInsertReqDto;
import com.ocire.lms.dto.forum.CommentResDto;
import com.ocire.lms.model.Forum;

public interface ForumService {
	Forum getForumByLearningId(Long learningId);

	Forum insertForum(Forum forum);
	
	List<CommentResDto> getCommentByForumId(Long forumId);

	InsertResDto insertComment(CommentInsertReqDto data);
}
