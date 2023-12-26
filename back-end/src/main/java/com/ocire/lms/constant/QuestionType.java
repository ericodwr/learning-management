package com.ocire.lms.constant;

public enum QuestionType {
	OPTION("Option", "op1"), ESSAY("Essay", "es1"), FILES("Files", "fl1");
	
	public final String typeName;
	public final String typeCode;
	
	private QuestionType(String typeName, String typeCode) {
		this.typeName = typeName;
		this.typeCode = typeCode;
	}
}
