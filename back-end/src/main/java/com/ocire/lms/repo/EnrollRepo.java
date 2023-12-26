package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Enroll;

public interface EnrollRepo extends JpaRepository<Enroll, Long> {

}
