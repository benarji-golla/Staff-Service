package com.HospitalManagement.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.HospitalManagement.builder.DoctorBuilder;
import com.HospitalManagement.dao.DoctorRepository;
import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;
import com.HospitalManagement.exception.DoctorServiceException;
import com.HospitalManagement.model.DoctorDetails;
import com.HospitalManagement.service.DoctorService;
import com.HospitalManagement.staffutils.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

	private final DoctorRepository doctorRepository;

	private final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

	@Override
	public DoctorDetailsDTO registerDoctor(RegisterDoctorDTO doctorDto) {
		try {
			DoctorDetails doctor = DoctorBuilder.buildDoctorFromDTO(doctorDto);
			DoctorDetails savedDoctor = doctorRepository.save(doctor);
			logger.info("Doctor " + Constants.CREATED, savedDoctor.getStaffId());
			return DoctorBuilder.buildDoctorDetailsDTOFromDoctor(savedDoctor);
		} catch (Exception e) {
			logger.error(Constants.ERROR + " saving doctor: {}", e.getMessage());
			throw new DoctorServiceException(Constants.ERROR + " saving doctor" + e.getMessage());
		}
		
	}

}
