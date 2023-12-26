package com.ocire.lms.service;

import com.ocire.lms.dto.InsertResDto;
import com.ocire.lms.dto.student.ReviewInsertReqDto;

public interface ReviewService {
	InsertResDto insertReview(ReviewInsertReqDto data);
}
