package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.Class;
import com.ocire.lms.model.Enroll;

public interface ClassDao {
	List<Enroll> getClassByStudentId(Long studentId);

	List<Class> getAllClass();

	List<Class> getClassByTeacherId(Long teacherId);
	
	List<Class> getClassByUnenrollStudent(Long studentId);
		
	Class insert(Class newClass);

	Class getById(Long id);
}
