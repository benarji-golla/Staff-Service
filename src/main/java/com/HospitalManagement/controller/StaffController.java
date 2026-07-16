package com.HospitalManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.dto.RegisterStaffDto;
import com.HospitalManagement.dto.StaffDetailsDTO;
import com.HospitalManagement.service.StaffService;

import lombok.RequiredArgsConstructor;

@Component
@RequestMapping("/Staff")
@RequiredArgsConstructor
@RestController
public class StaffController {

	private final StaffService staffService;

	@PostMapping("/rigister")
	public ResponseEntity<StaffDetailsDTO> RegisterStaff(@RequestBody RegisterStaffDto registerStaffDto) {
		StaffDetailsDTO staffDetailsDTO = staffService.registerStaff(registerStaffDto);
		return new ResponseEntity<StaffDetailsDTO>(staffDetailsDTO, HttpStatus.CREATED);
	}

	@PutMapping("/updateById/{staffId}")
	public ResponseEntity<StaffDetailsDTO> updateStaff(@PathVariable String staffId,
			@RequestBody RegisterStaffDto RegisterStaffDto) {
		StaffDetailsDTO updatedStaff = staffService.updateStaffDetails(staffId, RegisterStaffDto);
		return ResponseEntity.ok(updatedStaff);
	}

	@GetMapping("/getById/{staffId}")
	public ResponseEntity<StaffDetailsDTO> findById(@PathVariable String staffId) {

		StaffDetailsDTO staff = staffService.findStaff(staffId);
		return ResponseEntity.ok(staff);
	}

	@GetMapping("/getAllStaff")
	public ResponseEntity<List<StaffDetailsDTO>> findAllStaff() {
		List<StaffDetailsDTO> allStaffDetails = staffService.getAllStaff();

		return ResponseEntity.ok(allStaffDetails);

	}
}
