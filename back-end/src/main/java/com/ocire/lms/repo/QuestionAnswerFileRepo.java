package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.QuestionAnswerFiles;

public interface QuestionAnswerFileRepo extends JpaRepository<QuestionAnswerFiles, Long> {
	List<QuestionAnswerFiles> getByQuestionAnswerId(Long id);
	
}
