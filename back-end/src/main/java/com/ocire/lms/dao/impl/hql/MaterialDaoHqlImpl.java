package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialDao;
import com.ocire.lms.model.Material;

@Repository
@Profile("hql-query")
public class MaterialDaoHqlImpl implements MaterialDao {
	@PersistenceContext
	private EntityManager em;

	public MaterialDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<Material> getMaterialByLearningId(Long learningId) {
		final String sql = "SELECT m FROM Material m WHERE m.learning.id = :learningId";

		final List<Material> materialList = this.em.createQuery(sql, Material.class)
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
