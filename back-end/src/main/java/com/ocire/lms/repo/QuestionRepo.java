package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Long> {

	List<Question> getByTaskId(Long id);

}
