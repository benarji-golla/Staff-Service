package com.HospitalManagement.staffutils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.HospitalManagement.model.StaffDetails;

import jakarta.persistence.PrePersist;

@Component
public class StaffEntityListner {

	public static StaffIdGenerator staffIdGenerator;

	@Autowired
	public void init(StaffIdGenerator staffIdGenerator) {

		this.staffIdGenerator = staffIdGenerator;
	}

	@PrePersist
	public void generateStaffId(StaffDetails staff) {
		if (staff.getStaffId() == null || staff.getStaffId().isEmpty()) {
			staff.setStaffId(staffIdGenerator.generateNextStaffId());
		}
	}

}
