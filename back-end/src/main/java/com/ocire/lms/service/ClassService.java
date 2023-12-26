package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.classlms.ClassInsertReqDto;
import com.ocire.lms.dto.classlms.ClassResDto;

public interface ClassService {
	List<ClassResDto> getClassByStudentId();

	List<ClassResDto> getAllClasses();
	
	List<ClassResDto> getClassByTeacherId();

	InsertResDto insertClass(ClassInsertReqDto newClass);
	
	List<ClassResDto> getClassByStudent();
}
