package com.ocire.lms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_material")
public class Material extends BaseModel {

	@Column(name = "material_name", length = 30, nullable = false)
	private String materialName;

	@Column(name = "material_title", length = 30, nullable = false)
	private String materialTitle;
	
	@Column(name = "material_text", nullable = true)
	private String materialText;

	@ManyToOne
	@JoinColumn(name = "learning_id")
	private Learning learning;
	
	@Transient
	private List<MaterialFiles> materialFiles;

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialTitle() {
		return materialTitle;
	}

	public void setMaterialTitle(String materialTitle) {
		this.materialTitle = materialTitle;
	}

	public String getMaterialText() {
		return materialText;
	}

	public void setMaterialText(String materialText) {
		this.materialText = materialText;
	}

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

	public List<MaterialFiles> getMaterialFiles() {
		return materialFiles;
	}

	public void setMaterialFiles(List<MaterialFiles> materialFiles) {
		this.materialFiles = materialFiles;
	}

}
