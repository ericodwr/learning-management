package com.ocire.lms.dto.user;

import javax.validation.constraints.NotBlank;

public class UserInsertReqDto {

	@NotBlank
	private String username;

	private Long roleId;
	private String fullName;
	private String phoneNumb;
	private String fileName;
	private String fileExtens;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumb() {
		return phoneNumb;
	}

	public void setPhoneNumb(String phoneNumb) {
		this.phoneNumb = phoneNumb;
	}

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
