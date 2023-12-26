package com.ocire.lms.dao.impl.springDataJpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.model.File;
import com.ocire.lms.repo.FileRepo;

@Repository
@Profile("springdatajpa-query")
public class FileDaoSpringDataJpaImpl implements FileDao {
	@PersistenceContext
	private EntityManager em;
	private FileRepo fileRepo;

	public FileDaoSpringDataJpaImpl(FileRepo fileRepo) {
		this.fileRepo = fileRepo;
	}

	@Override
	public File insert(File file) {
		fileRepo.save(file);
		return file;
	}

	@Override
	public File getById(Long id) {
		final File file = fileRepo.findById(id).get();
		return file;
	}

}
