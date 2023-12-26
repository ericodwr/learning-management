package com.ocire.lms.service;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.EnrollInsertReqDto;

public interface EnrollService {
	InsertResDto insertEnroll(EnrollInsertReqDto data);
}
