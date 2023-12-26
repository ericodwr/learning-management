package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_class")
public class Class extends BaseModel {

	@Column(name = "class_name", length = 30, nullable = false)
	private String className;

	@Column(name = "class_code", length = 5, nullable = false)
	private String classCode;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private File classImage;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public File getClassImage() {
		return classImage;
	}

	public void setClassImage(File classImage) {
		this.classImage = classImage;
	}

}
