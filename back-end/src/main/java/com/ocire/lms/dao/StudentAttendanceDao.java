package com.ocire.lms.dao;

import java.util.List;

import com.ocire.lms.model.StudentAttendance;

public interface StudentAttendanceDao {
	StudentAttendance getStudentAttendance(Long studentId, Long learningId);

	List<StudentAttendance> getStudentAttendanceByLearningId(Long learningId);

	StudentAttendance getById(Long id);

	StudentAttendance insert(StudentAttendance studentAttendance);
}
