package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Material;

public interface MaterialRepo extends JpaRepository<Material, Long> {
	List<Material> getByLearningId(Long id);
}
