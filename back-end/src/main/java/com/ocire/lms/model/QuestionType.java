package com.ocire.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_question_type")
public class QuestionType extends BaseModel {
	@Column(name = "type_name", length = 20, nullable = false)
	private String typeName;
	
	@Column(name = "type_code", length = 5, nullable = false)
	private String typeCode;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}
