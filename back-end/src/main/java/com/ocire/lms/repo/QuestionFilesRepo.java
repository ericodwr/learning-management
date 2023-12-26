package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.QuestionFiles;

public interface QuestionFilesRepo extends JpaRepository<QuestionFiles, Long> {
	List<QuestionFiles> getByQuestionId(Long id);
}
