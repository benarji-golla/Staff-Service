package com.HospitalManagement.builder;

import com.HospitalManagement.dto.AddressDTO;
import com.HospitalManagement.dto.DoctorDetailsDTO;
import com.HospitalManagement.dto.RegisterDoctorDTO;
import com.HospitalManagement.model.Address;
import com.HospitalManagement.model.DoctorDetails;
import com.HospitalManagement.model.User;


public class DoctorBuilder {

    // Convert DTO to Doctor Entity
    public static DoctorDetails buildDoctorFromDTO(RegisterDoctorDTO dto) {
        return DoctorDetails.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .dateOfJoining(dto.getDateOfJoining())
                .specialization(dto.getSpecialization())
                .experienceInYears(dto.getExperienceInYears())
                .isEmployeeActive(true)
                .canLogin(true)
                .address(Address.builder()
                        .street(dto.getAddressDTO().getStreet())
                        .landMark(dto.getAddressDTO().getLandMark())
                        .city(dto.getAddressDTO().getCity())
                        .postalCode(dto.getAddressDTO().getPostalCode())
                        .state(dto.getAddressDTO().getState())
                        .country(dto.getAddressDTO().getCountry())
                        .build())
                .user(User.builder()
                		.email(dto.getEmail())
                		.password(null)
                		.role("ROLE_DOCTOR")
                		.build()) // Associate the doctor with a User entity
                .build();
    }
    
    public static DoctorDetailsDTO buildDoctorDetailsDTOFromDoctor(DoctorDetails doctor) {
        return DoctorDetailsDTO.builder()
                .doctorId(doctor.getStaffId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .email(doctor.getUser().getEmail()) // Get email from User entity
                .phoneNumber(doctor.getPhoneNumber())
                .addressDTO(AddressDTO.builder()
                        .street(doctor.getAddress().getStreet())
                        .landMark(doctor.getAddress().getLandMark())
                        .city(doctor.getAddress().getCity())
                        .postalCode(doctor.getAddress().getPostalCode())
                        .state(doctor.getAddress().getState())
                        .country(doctor.getAddress().getCountry())
                        .build())
                .specialization(doctor.getSpecialization())
                .experienceInYears(doctor.getExperienceInYears())
                .dateOfJoining(doctor.getDateOfJoining())
                .isActive(doctor.getIsEmployeeActive())
                .build();
    }
    
}
