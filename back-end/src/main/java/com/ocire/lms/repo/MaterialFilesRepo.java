package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.MaterialFiles;

public interface MaterialFilesRepo extends JpaRepository<MaterialFiles, Long> {
	List<MaterialFiles> getByMaterialId(Long id);
}
