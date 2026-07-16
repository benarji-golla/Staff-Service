package com.HospitalManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.StaffDetails;

@Repository
public interface StaffRepository extends JpaRepository<StaffDetails,String> {
	
	@Query(value="SELECT staff_id from staff where staff_id like 'BENNU%' ORDER BY staff_id desc limit 1 ", nativeQuery = true)
	String findLastStaffId();

}
