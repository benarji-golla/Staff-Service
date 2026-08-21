package com.HospitalManagement.model;

import java.time.LocalDate;

import com.HospitalManagement.staffutils.StaffEntityListner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(value=StaffEntityListner.class)
public class StaffDetails {

	@Id
	@Column(name = "staff_id")
	private String staffId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "date_of_join")
	private LocalDate dateOfJoining;

	@Column(name = "experience", nullable = false)
	private double experienceInYears;

	@Column(name = "active")
	private Boolean isEmployeeActive; // Whether the staff is currently working

	@Column(name = "can_login", nullable = false)
	private Boolean canLogin;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user")
	private User user;
}
