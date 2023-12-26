package com.ocire.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dao.LearningDao;
import com.ocire.lms.dao.MaterialDao;
import com.ocire.lms.dao.MaterialFilesDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.material.MaterialInsertReqDto;
import com.ocire.lms.dto.material.MaterialResDto;
import com.ocire.lms.model.File;
import com.ocire.lms.model.Learning;
import com.ocire.lms.model.Material;
import com.ocire.lms.model.MaterialFiles;
import com.ocire.lms.service.MaterialService;
import com.ocire.lms.service.PrincipalService;

@Service
public class MaterialServiceImpl implements MaterialService {
	private final MaterialDao materialDao;
	private final MaterialFilesDao materialFilesDao;
	private final LearningDao learningDao;
	private final PrincipalService principalService;
	private final FileDao fileDao;

	public MaterialServiceImpl(MaterialDao materialDao, MaterialFilesDao materialFilesDao, LearningDao learningDao,
			PrincipalService principalService, FileDao fileDao) {
		this.materialDao = materialDao;
		this.materialFilesDao = materialFilesDao;
		this.learningDao = learningDao;
		this.principalService = principalService;
		this.fileDao = fileDao;
	}

	@Override
	public List<MaterialResDto> getMaterialsByLearningId(Long learningId) {
		final List<MaterialResDto> materialList = new ArrayList<>();
		materialDao.getMaterialByLearningId(learningId).forEach(m -> {
			final MaterialResDto material = new MaterialResDto();
			final List<Long> materialFilesId = new ArrayList<>();
			material.setId(m.getId());
			material.setMaterialName(m.getMaterialName());
			material.setMaterialTitle(m.getMaterialTitle());
			material.setMaterialText(m.getMaterialText());
			material.setLearningId(m.getLearning().getId());
			
			materialFilesDao.getMaterialFilesByMaterialId(m.getId()).forEach(f -> {
				materialFilesId.add(f.getFile().getId());
			});
			material.setMaterialFilesId(materialFilesId);
			
			
			materialList.add(material);
		});
		
		return materialList;
	}

	@Override
	public List<MaterialFiles> getMaterialFiles(Long materialId) {
		return materialFilesDao.getMaterialFilesByMaterialId(materialId);
	}

	@Transactional
	@Override
	public InsertResDto insertMaterial(List<MaterialInsertReqDto> dataList) {
		final InsertResDto result = new InsertResDto();

		dataList.forEach(data -> {
			final Learning learning = learningDao.getById(data.getLearningId());
			final Material material = new Material();
			material.setLearning(learning);
			material.setMaterialName(data.getMaterialName());
			material.setMaterialTitle(data.getMaterialTitle());
			material.setMaterialText(data.getMaterialText());
			material.setCreatedBy(principalService.getPrincipal());

			final Material newMaterial = materialDao.insert(material);

			if (data.getMaterialFilesReq() != null) {
				data.getMaterialFilesReq().forEach(fileData -> {
					final File file = new File();
					file.setFileName(fileData.getFileName());
					file.setFileExtens(fileData.getFileExtens());
					file.setCreatedBy(principalService.getPrincipal());
					final File newFile = fileDao.insert(file);
					
					final MaterialFiles materialFile = new MaterialFiles();
					materialFile.setFile(newFile);
					materialFile.setMaterial(newMaterial);
					materialFile.setCreatedBy(principalService.getPrincipal());
					materialFilesDao.insert(materialFile);
				});
			}

		});
		result.setId(null);
		result.setMessage("Successfully added material!");

		return result;
	}

	@Override
	public MaterialResDto getById(Long id) {
		final Material material =  materialDao.getById(id);
		
		final MaterialResDto materialD = new MaterialResDto();
		final List<Long> materialFilesRes = new ArrayList<>();
		
		materialD.setId(material.getId());
		materialD.setLearningId(material.getLearning().getId());
		materialD.setMaterialName(material.getMaterialName());
		materialD.setMaterialText(material.getMaterialText());
		materialD.setMaterialTitle(material.getMaterialTitle());
		
		materialFilesDao.getMaterialFilesByMaterialId(id).forEach(f -> {
			materialFilesRes.add(f.getFile().getId());
		});
		materialD.setMaterialFilesId(materialFilesRes);
		
		return materialD;
	}

}
