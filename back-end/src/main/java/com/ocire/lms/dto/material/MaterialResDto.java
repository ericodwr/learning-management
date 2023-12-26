package com.ocire.lms.dto.material;

import java.util.List;

public class MaterialResDto {
	private Long id;
	private String materialName;
	private String materialTitle;
	private String materialText;
	private Long learningId;
	private List<Long> materialFilesId;

	public List<Long> getMaterialFilesId() {
		return materialFilesId;
	}

	public void setMaterialFilesId(List<Long> materialFilesId) {
		this.materialFilesId = materialFilesId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getLearningId() {
		return learningId;
	}

	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}

}
