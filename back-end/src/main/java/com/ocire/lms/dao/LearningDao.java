package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Learning;

public interface LearningDao {
	List<Learning> getLearningByClassId(Long classId);

	Learning insert(Learning learning);

	Learning getById(Long id);
}
