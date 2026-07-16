package com.HospitalManagement.builder;

import com.HospitalManagement.dto.AddressDTO;
import com.HospitalManagement.dto.RegisterStaffDto;
import com.HospitalManagement.dto.StaffDetailsDTO;
import com.HospitalManagement.model.Address;
import com.HospitalManagement.model.StaffDetails;
import com.HospitalManagement.model.User;


public class StaffDetailsBuilder {


    // Convert DTO to Staff Entity
    public static StaffDetails buildStaffFromDTO(RegisterStaffDto dto) {
        return StaffDetails.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .dateOfJoining(dto.getDateOfJoining())
                .experienceInYears(dto.getExperienceInYears())
                .isEmployeeActive(true)
                .canLogin(dto.isCanLogin()) // Some staff may have login, some may not
                .address(Address.builder()
                        .street(dto.getAddressDTO().getStreet())
                        .landMark(dto.getAddressDTO().getLandMark())
                        .city(dto.getAddressDTO().getCity())
                        .postalCode(dto.getAddressDTO().getPostalCode())
                        .state(dto.getAddressDTO().getState())
                        .country(dto.getAddressDTO().getCountry())
                        .build())
                .user(User.builder()
                        .email(dto.getEmail() != null ? dto.getEmail() : null)
                        .password(null) 
                        .role(dto.getRole())
                        .build()) 
                .build();
        
    }
    
    
    // Convert Staff Entity to StaffDetailsDTO
    public static StaffDetailsDTO buildStaffDetailsDTOFromStaff( StaffDetails staff) {
        return StaffDetailsDTO.builder()
                .staffId(staff.getStaffId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .email(staff.getUser().getEmail() != null ? staff.getUser().getEmail() : null) // Only if user exists
                .phoneNumber(staff.getPhoneNumber())
                .addressDTO(AddressDTO.builder()
                        .street(staff.getAddress().getStreet())
                        .landMark(staff.getAddress().getLandMark())
                        .city(staff.getAddress().getCity())
                        .postalCode(staff.getAddress().getPostalCode())
                        .state(staff.getAddress().getState())
                        .country(staff.getAddress().getCountry())
                        .build())
                .experienceInYears(staff.getExperienceInYears())
                .dateOfJoining(staff.getDateOfJoining())
                .isActive(staff.getIsEmployeeActive())
                .role(staff.getUser().getRole()) // If user exists, get role
                .canLogin(staff.getCanLogin())
                .build();
    }

	
}
