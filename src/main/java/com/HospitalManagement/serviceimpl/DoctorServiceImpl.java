package com.HospitalManagement.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.HospitalManagement.builder.DoctorBuilder;	
import com.HospitalManagement.dao.DoctorRepository;
import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;
import com.HospitalManagement.exception.DoctorNotFoundException;
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

	@Override
	public DoctorDetailsDTO getDoctor(String id) {
		try {
			DoctorDetails doctor = doctorRepository.findById(id)
					.orElseThrow(() -> new DoctorNotFoundException("Doctor" + Constants.NOT_FOUND + id));
			logger.info(Constants.RETRIEVED, "Doctor", id);
			return DoctorBuilder.buildDoctorDetailsDTOFromDoctor(doctor);
		} catch (DataAccessException e) { // Catching database-related issues
			logger.error("{} Error updating doctor with ID: {} - Exception: {}", Constants.ERROR, id, e.getMessage(),
					e);
			throw new DoctorServiceException(Constants.ERROR + " Database error while updating doctor with ID: " + id);
		} catch (DoctorNotFoundException e) { // Letting this exception pass through
			logger.info(e.getMessage());
			throw e;
		} catch (Exception e) { // Catching unexpected exceptions
			logger.error("{} fetching Doctor details with id: {} - Exception - {}", Constants.ERROR, id, e);
			throw new DoctorServiceException(
					Constants.ERROR + " Unexpected error while fetching Doctor details with id: " + id);
		}
	}

	@Override
	public DoctorDetailsDTO getDoctorWithName(String firstName, String lastName) {
		try {
			DoctorDetails doctor = doctorRepository.findByFirstNameAndLastName(firstName, lastName)
					.orElseThrow(() -> new DoctorNotFoundException("Doctor" + Constants.NOT_FOUND + firstName +" "+ lastName));
			logger.info(Constants.RETRIEVED, "Doctor", firstName +" "+ lastName );
			return DoctorBuilder.buildDoctorDetailsDTOFromDoctor(doctor);
		} catch (DataAccessException e) { // Catching database-related issues
			logger.error("{} Error updating doctor with ID: {} - Exception: {}", Constants.ERROR, firstName +" "+ lastName, e.getMessage(),
					e);
			throw new DoctorServiceException(Constants.ERROR + " Database error while updating doctor with ID: " + firstName +" "+ lastName);
		} catch (DoctorNotFoundException e) { // Letting this exception pass through
			logger.info(e.getMessage());
			throw e;
		} catch (Exception e) { // Catching unexpected exceptions
			logger.error("{} fetching Doctor details with id: {} - Exception - {}", Constants.ERROR, firstName +" "+ lastName, e);
			throw new DoctorServiceException(
					Constants.ERROR + " Unexpected error while fetching Doctor details with id: " + firstName +" "+ lastName);
		}	
	
	}

}
