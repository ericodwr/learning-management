package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.material.MaterialInsertReqDto;
import com.ocire.lms.dto.material.MaterialResDto;
import com.ocire.lms.model.MaterialFiles;

public interface MaterialService {
	List<MaterialResDto> getMaterialsByLearningId(Long learningId);

	List<MaterialFiles> getMaterialFiles(Long materialId);
	
	InsertResDto insertMaterial(List<MaterialInsertReqDto> data);
	
	MaterialResDto getById(Long id);
}
