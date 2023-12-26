package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
	
@Entity
@Table(name = "t_profile")
public class Profile extends BaseModel {
	@Column(name = "full_name", nullable = false)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "profile_photo")
	private File profilePhoto;

	@Column(name = "phone_numb", nullable = false)
	private String phoneNumb;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public File getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(File profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getPhoneNumb() {
		return phoneNumb;
	}

	public void setPhoneNumb(String phoneNumb) {
		this.phoneNumb = phoneNumb;
	}

}
