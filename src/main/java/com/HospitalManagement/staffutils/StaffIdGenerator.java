package com.HospitalManagement.staffutils;

import org.springframework.stereotype.Component;

import com.HospitalManagement.dao.StaffRepository;

@Component
public class StaffIdGenerator {

    private final StaffRepository staffRepository;
    
    public StaffIdGenerator(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}



	public  String generateNextStaffId() {
        String lastId = staffRepository.findLastStaffId();
        int nextNumber = 1;

        if (lastId != null && lastId.startsWith("BENNU-")) {
            String numberPart = lastId.substring(6);
            nextNumber = Integer.parseInt(numberPart) + 1;
            return String.format("BENNU-%05d", nextNumber);
        }

        return String.format("BENNU-%05d", nextNumber);
    }
}
