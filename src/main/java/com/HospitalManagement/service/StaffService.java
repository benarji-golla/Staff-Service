package com.HospitalManagement.service;

import java.util.List;

import com.HospitalManagement.dto.RegisterStaffDto;
import com.HospitalManagement.dto.StaffDetailsDTO;

public interface StaffService {

	public StaffDetailsDTO registerStaff(RegisterStaffDto registerStaffDto);

	public StaffDetailsDTO updateStaffDetails(String staffId, RegisterStaffDto registerStaffDto);

	public StaffDetailsDTO findStaff(String staffId);

	public List<StaffDetailsDTO> getAllStaff();
	
	

}
