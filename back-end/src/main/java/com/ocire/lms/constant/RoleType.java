package com.ocire.lms.constant;

public enum RoleType {
	SUPERADMIN("sp1", "Super Admin"), TEACHER("tc1", "Teacher"), STUDENT("st1", "Student");
		
	public final String roleCode;
	public final String roleName;
		
	RoleType(String roleCode, String roleName) {
		this.roleCode = roleCode;
		this.roleName = roleName;
	}
}
