package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_file")
public class File extends BaseModel {
	
	@Column(name = "file_name", nullable = false)
	private String fileName;
	
	@Column(name = "file_extens", nullable = false)
	private String fileExtens;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExtens() {
		return fileExtens;
	}

	public void setFileExtens(String fileExtens) {
		this.fileExtens = fileExtens;
	}

}
