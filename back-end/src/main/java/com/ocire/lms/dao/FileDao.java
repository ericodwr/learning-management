package com.ocire.lms.dao;

import com.ocire.lms.model.File;

public interface FileDao {
	File insert(File file);
	
	File getById(Long id);
}
