package com.ocire.lms.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.ocire.lms.dao.FileDao;
import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.files.FilesInsertReqDto;
import com.ocire.lms.model.File;
import com.ocire.lms.service.FileService;
import com.ocire.lms.service.PrincipalService;

@Service
public class FileServiceImpl implements FileService {
	private final FileDao fileDao;

	@PersistenceContext
	private EntityManager em;
	private PrincipalService principalService;

	public FileServiceImpl(FileDao fileDao, SessionFactory factory, PrincipalService principalService) {
		this.principalService = principalService;
		this.fileDao = fileDao;
	}

	@Override
	public InsertResDto insertFile(FilesInsertReqDto file) {
		final File newFile = new File();
		newFile.setFileName(file.getFileName());
		newFile.setFileExtens(file.getFileExtens());
		newFile.setCreatedBy(principalService.getPrincipal());
		final InsertResDto result = new InsertResDto();

		File inputFile = fileDao.insert(newFile);
		
		
		result.setMessage("Success insert new files");
		result.setId(inputFile.getId());
		
		return result;

	}

	@Override
	public File getById(Long id) {
		final File file = fileDao.getById(id);
		return file;
	}

}
