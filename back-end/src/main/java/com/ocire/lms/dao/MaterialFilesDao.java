package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.MaterialFiles;

public interface MaterialFilesDao {
	List<MaterialFiles> getMaterialFilesByMaterialId(Long materialId);

	MaterialFiles insert(MaterialFiles materialFiles);
}
