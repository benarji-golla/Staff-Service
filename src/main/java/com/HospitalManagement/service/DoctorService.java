package com.HospitalManagement.service;

import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;

public interface DoctorService {

	DoctorDetailsDTO registerDoctor(RegisterDoctorDTO doctorDto);

	DoctorDetailsDTO getDoctor(String id);

	DoctorDetailsDTO getDoctorWithName(String firstName, String lastName);

	void validateById(String id);

}
