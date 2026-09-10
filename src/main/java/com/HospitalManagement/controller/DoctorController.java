package com.HospitalManagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;
import com.HospitalManagement.service.DoctorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Doctor")
@RequiredArgsConstructor
public class DoctorController {
	private final DoctorService doctorService;

	@PostMapping("/register")
	public ResponseEntity<DoctorDetailsDTO> registerDoctor(@Valid @RequestBody RegisterDoctorDTO doctorDto) {
		DoctorDetailsDTO savedDoctor = doctorService.registerDoctor(doctorDto);
		return new ResponseEntity<DoctorDetailsDTO>(savedDoctor, HttpStatus.CREATED);

	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<DoctorDetailsDTO> getDoctorById(@PathVariable String id) {
		DoctorDetailsDTO doctor = doctorService.getDoctor(id);
		return ResponseEntity.ok(doctor);
	}

	@GetMapping("/getByName")
	public ResponseEntity<DoctorDetailsDTO> getDoctorByName(@RequestParam String firstName,
			@RequestParam String lastName) {
		DoctorDetailsDTO doctor = doctorService.getDoctorWithName(firstName,lastName);
		return ResponseEntity.ok(doctor);
	}
	
	@GetMapping("/validateDoctor/{id}")
	public ResponseEntity<Void> validateDoctor(@PathVariable String id){
	     doctorService.validateById(id);
	     return ResponseEntity.ok().build();
	}
	
}
