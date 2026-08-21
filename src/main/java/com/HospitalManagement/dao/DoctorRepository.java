package com.HospitalManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.DoctorDetails;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails, String>{
	
	List<DoctorDetails> findByFirstNameContainingIgnoreCase(String firstName);

}
