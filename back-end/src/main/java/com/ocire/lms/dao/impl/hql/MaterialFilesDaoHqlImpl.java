package com.ocire.lms.dao.impl.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialFilesDao;
import com.ocire.lms.model.MaterialFiles;

@Repository
@Profile("hql-query")
public class MaterialFilesDaoHqlImpl implements MaterialFilesDao {
	@PersistenceContext
	private EntityManager em;

	public MaterialFilesDaoHqlImpl(SessionFactory factory) {
	}

	@Override
	public List<MaterialFiles> getMaterialFilesByMaterialId(Long materialId) {
		final String sql = "SELECT mf FROM MaterialFiles mf WHERE mf.material.id = :materialId";

		final List<MaterialFiles> materialFilesList = this.em.createQuery(sql, MaterialFiles.class)
				.setParameter("materialId", materialId).getResultList();
		return materialFilesList;
	}

	@Override
	public MaterialFiles insert(MaterialFiles materialFiles) {
		em.persist(materialFiles);
		return materialFiles;
	}

}
