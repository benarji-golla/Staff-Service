package com.HospitalManagement.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.HospitalManagement.builder.StaffDetailsBuilder;
import com.HospitalManagement.dao.StaffRepository;
import com.HospitalManagement.dto.RegisterStaffDto;
import com.HospitalManagement.dto.StaffDetailsDTO;
import com.HospitalManagement.exception.StaffNotFoundException;
import com.HospitalManagement.exception.StaffServiceException;
import com.HospitalManagement.model.StaffDetails;
import com.HospitalManagement.service.StaffService;
import com.HospitalManagement.staffutils.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

	private final StaffRepository staffRepository;

	private final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

	@Override
	public StaffDetailsDTO registerStaff(RegisterStaffDto registerStaffDto) {

		try {
			StaffDetails staff = StaffDetailsBuilder.buildStaffFromDTO(registerStaffDto);
			StaffDetails savedStaff = staffRepository.save(staff);
			logger.info("Staff " + Constants.CREATED, savedStaff.getStaffId());
			return StaffDetailsBuilder.buildStaffDetailsDTOFromStaff(savedStaff);
		} catch (Exception e) {
			logger.error("{} saving staff: {}", Constants.ERROR, e.getMessage());
			throw new StaffServiceException(Constants.ERROR + " saving staff: " + e.getMessage());

		}
	}

	@Override
	public StaffDetailsDTO updateStaffDetails(String staffId, RegisterStaffDto registerStaffDto) {
		try {
			StaffDetails existingStaff = staffRepository.findById(staffId)
					.orElseThrow(() -> new StaffNotFoundException("Staff " + Constants.NOT_FOUND + staffId));
			logger.info(Constants.RETRIEVED, "Staff", staffId);

			StaffDetails updatedStaff = StaffDetailsBuilder.buildStaffFromDTO(registerStaffDto);

			// Preserve essential details from existing staff
			updatedStaff.getUser().setUserId(existingStaff.getUser().getUserId());
			updatedStaff.getUser().setPassword(existingStaff.getUser().getPassword());
			updatedStaff.setStaffId(existingStaff.getStaffId());
			updatedStaff.getAddress().setAddressId(existingStaff.getAddress().getAddressId());

			staffRepository.save(updatedStaff);
			logger.info("Staff " + Constants.UPDATED, staffId);

			return StaffDetailsBuilder.buildStaffDetailsDTOFromStaff(updatedStaff);
		} catch (DataAccessException e) {
			logger.error("{} updating staff with ID: {} - Exception: {}", Constants.ERROR, staffId, e.getMessage());
			throw new StaffServiceException(Constants.ERROR + " updating staff with ID: " + staffId);
		} catch (StaffNotFoundException e) {
			logger.warn(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("{} updating staff with ID: {} - Exception - {}", Constants.ERROR, staffId, e);
			throw new StaffServiceException(Constants.ERROR + " updating staff with ID: " + staffId);
		}

	}

	@Override
	public StaffDetailsDTO findStaff(String staffId) {
		try {
			StaffDetails staff = staffRepository.findById(staffId)
					.orElseThrow(() -> new StaffNotFoundException("Staff \" + Constants.NOT_FOUND + staffId"));
			logger.info(Constants.RETRIEVED, "Staff", staffId);
			return StaffDetailsBuilder.buildStaffDetailsDTOFromStaff(staff);

		} catch (DataAccessException e) {
			logger.error("{} fetching staff details with ID: {} - Exception: {}", Constants.ERROR, staffId,
					e.getMessage());
			throw new StaffServiceException(Constants.ERROR + " fetching staff details with ID: " + staffId);
		} catch (StaffNotFoundException e) {
			logger.warn(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("{} fetching staff details with ID: {} - Exception - {}", Constants.ERROR, staffId, e);
			throw new StaffServiceException(Constants.ERROR + " fetching staff details with ID: " + staffId);
		}
	}

	@Override
	public List<StaffDetailsDTO> getAllStaff() {
		try {
			List<StaffDetails> allStaff = staffRepository.findAll();

			if (allStaff.isEmpty()) {
				logger.info("No staff members found");
			}
			return allStaff.stream()
					.map(StaffDetailsBuilder::buildStaffDetailsDTOFromStaff)
					.collect(Collectors.toList());
		} catch (Exception e) {
			logger.error("{} Fetching all staff details: {}", Constants.ERROR, e.getMessage());
			throw new StaffServiceException(Constants.ERROR + " Fetching all staff details: " + e.getMessage());
		}

	}

}
