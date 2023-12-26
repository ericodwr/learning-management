package com.ocire.lms.dto.user;

import javax.validation.constraints.NotBlank;

public class UserUpdateReqDto {
	@NotBlank
	private Long id;
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
