package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialFilesDao;
import com.ocire.lms.model.MaterialFiles;
import com.ocire.lms.repo.MaterialFilesRepo;

@Repository
@Profile("springdatajpa-query")
public class MaterialFilesDaoSpringDataJpaImpl implements MaterialFilesDao {
	@PersistenceContext
	private EntityManager em;
	private MaterialFilesRepo materialFilesRepo;

	public MaterialFilesDaoSpringDataJpaImpl(MaterialFilesRepo materialFilesRepo) {
		this.materialFilesRepo = materialFilesRepo;
	}

	@Override
	public List<MaterialFiles> getMaterialFilesByMaterialId(Long materialId) {

		final List<MaterialFiles> materialFilesList = materialFilesRepo.getByMaterialId(materialId);
		return materialFilesList;
	}

	@Override
	public MaterialFiles insert(MaterialFiles materialFiles) {
		materialFilesRepo.save(materialFiles);
		return materialFiles;
	}

}
