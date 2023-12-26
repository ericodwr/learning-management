package com.ocire.lms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.MaterialFilesDao;
import com.ocire.lms.model.MaterialFiles;

@Repository
@Profile("native-query")
public class MaterialFilesDaoImpl implements MaterialFilesDao {
	@PersistenceContext
	private EntityManager em;

	public MaterialFilesDaoImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialFiles> getMaterialFilesByMaterialId(Long materialId) {
		final String sql = "SELECT * FROM t_material_files mf INNER JOIN t_file f ON mf.file_id = f.id WHERE mf.material_id = :materialId";

		final List<MaterialFiles> materialFilesList = this.em.createNativeQuery(sql, MaterialFiles.class)
				.setParameter("materialId", materialId).getResultList();
		return materialFilesList;
	}

	@Override
	public MaterialFiles insert(MaterialFiles materialFiles) {
		em.persist(materialFiles);
		return materialFiles;
	}

}
