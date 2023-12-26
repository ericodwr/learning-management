package com.ocire.lms.dao.impl.springDataJpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialDao;
import com.ocire.lms.model.Material;
import com.ocire.lms.repo.MaterialRepo;

@Repository
@Profile("springdatajpa-query")
public class MaterialDaoSpringDataJpaImpl implements MaterialDao {
	@PersistenceContext
	private EntityManager em;
	private MaterialRepo materialRepo;

	public MaterialDaoSpringDataJpaImpl(MaterialRepo materialRepo) {
		this.materialRepo = materialRepo;
	}

	@Override
	public List<Material> getMaterialByLearningId(Long learningId) {

		final List<Material> materialList = materialRepo.getByLearningId(learningId);
		return materialList;
	}

	@Override
	public Material insert(Material material) {
		materialRepo.save(material);
		return material;
	}

	@Override
	public Material getById(Long id) {
		final Material material = materialRepo.findById(id).get();
		return material;
	}

}
