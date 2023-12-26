package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.StudentAttendance;

public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> {
	StudentAttendance getByStudentIdAndLearningId(Long studentId, Long learningId);
	
	List<StudentAttendance> getByLearningId(Long learningId);
}
