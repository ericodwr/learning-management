package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.QuestionType;

public interface QuestionTypeRepo extends JpaRepository<QuestionType, Long> {

}
