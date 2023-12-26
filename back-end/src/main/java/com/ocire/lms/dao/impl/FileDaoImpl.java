package com.ocire.lms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.model.File;

@Repository
@Profile("native-query")
public class FileDaoImpl implements FileDao {
	@PersistenceContext
	private EntityManager em;

	public FileDaoImpl() {
	}

	@Override
	public File insert(File file) {
		em.persist(file);
		return file;
	}

	@Override
	public File getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
