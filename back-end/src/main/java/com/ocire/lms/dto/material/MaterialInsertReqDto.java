package com.ocire.lms.dto.material;

import java.util.List;

public class MaterialInsertReqDto {
	private String materialName;
	private String materialTitle;
	private String materialText;
	private Long learningId;
	
	private List<MaterialFilesInsertReqDto> materialFilesReq;
	
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

	public List<MaterialFilesInsertReqDto> getMaterialFilesReq() {
		return materialFilesReq;
	}

	public void setMaterialFilesReq(List<MaterialFilesInsertReqDto> materialFilesReq) {
		this.materialFilesReq = materialFilesReq;
	}

}
