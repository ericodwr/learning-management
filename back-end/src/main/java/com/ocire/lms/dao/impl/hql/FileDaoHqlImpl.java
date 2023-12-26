package com.ocire.lms.dao.impl.hql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.model.File;

@Repository
@Profile("hql-query")
public class FileDaoHqlImpl implements FileDao {
	@PersistenceContext
	private EntityManager em;

	public FileDaoHqlImpl(SessionFactory factory) {
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
