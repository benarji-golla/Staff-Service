package com.HospitalManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagement.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{
	

}
