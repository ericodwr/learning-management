package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.learning.LearningInsertReqDto;
import com.ocire.lms.dto.learning.LearningResDto;

public interface LearningService {
	List<LearningResDto> getLearningByClassId(Long classId);
	
	InsertResDto insertLearning(LearningInsertReqDto data);
}
