package com.ocire.lms.dao;

import com.ocire.lms.model.Forum;

public interface ForumDao {
	Forum getForumByLearningId(Long learningId);

	Forum insert(Forum forum);
	
	Forum getById(Long id);
}
