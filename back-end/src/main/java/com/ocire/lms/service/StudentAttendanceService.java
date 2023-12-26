package com.ocire.lms.service;

import java.util.List;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.UpdateResDto;
import com.ocire.lms.dto.student.AttendanceInsertReqDto;
import com.ocire.lms.dto.student.AttendanceResDto;
import com.ocire.lms.model.StudentAttendance;

public interface StudentAttendanceService {
	StudentAttendance getStudentAttendance(Long studentId, Long learningId);
	
	List<AttendanceResDto> getStudentAttendanceByLearningId(Long learningId);
	
	UpdateResDto updateStudentAttendanceStatus(AttendanceInsertReqDto data);
	
	InsertResDto insertStudentAttendance(AttendanceInsertReqDto data);
}
