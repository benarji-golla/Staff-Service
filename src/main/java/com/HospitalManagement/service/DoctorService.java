package com.HospitalManagement.service;

import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;

public interface DoctorService {

	DoctorDetailsDTO registerDoctor(RegisterDoctorDTO doctorDto);

}
