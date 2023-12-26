package com.ocire.lms.service;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.files.FilesInsertReqDto;
import com.ocire.lms.model.File;

public interface FileService {
	InsertResDto insertFile(FilesInsertReqDto data);
	
	File getById(Long id);
}
