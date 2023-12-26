package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.QuestionAnswer;

public interface QuestionAnswerRepo extends JpaRepository<QuestionAnswer, Long> {
	List<QuestionAnswer> getByCreatedBy(Long id);
}
