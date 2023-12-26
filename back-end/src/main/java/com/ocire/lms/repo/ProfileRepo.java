package com.ocire.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocire.lms.model.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
	
}
