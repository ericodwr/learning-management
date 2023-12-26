package com.ocire.lms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ocire.lms.model.Class;

public interface ClassRepo extends JpaRepository<Class, Long> {
	List<Class> getByTeacherId(Long id);
	
	@Query(value = "SELECT " + "* " + "FROM t_class tc " + "WHERE " + "tc.id NOT IN (SELECT " + "te.class_id " + "FROM "
			+ "t_enroll te " + "WHERE " + "te.student_id = :studentId)", nativeQuery = true)
	List<Class> getByEnrolled(Long studentId);
}
