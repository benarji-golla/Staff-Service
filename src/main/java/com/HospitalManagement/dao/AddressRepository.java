package com.HospitalManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalManagement.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
