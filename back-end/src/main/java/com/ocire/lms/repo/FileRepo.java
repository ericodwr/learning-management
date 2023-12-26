package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.File;

public interface FileRepo extends JpaRepository<File, Long> {

}
