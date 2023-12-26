package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Material;

public interface MaterialDao {
	List<Material> getMaterialByLearningId(Long learningId);

	Material insert(Material material);

	Material getById(Long id);
}
