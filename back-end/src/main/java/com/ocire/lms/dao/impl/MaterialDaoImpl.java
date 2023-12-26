package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialDao;
import com.ocire.lms.model.Material;

@Repository
@Profile("native-query")
public class MaterialDaoImpl implements MaterialDao {
	@PersistenceContext
	private EntityManager em;

	public MaterialDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Material> getMaterialByLearningId(Long learningId) {
		final String sql = "SELECT * FROM t_material WHERE learning_id = :learningId";

		final List<Material> materialList = this.em.createNativeQuery(sql, Material.class)
				.setParameter("learningId", learningId).getResultList();
		return materialList;
	}

	@Override
	public Material insert(Material material) {
		em.persist(material);
		return material;
	}

	@Override
	public Material getById(Long id) {
		final Material material = this.em.find(Material.class, id);
		return material;
	}

}
