package com.HospitalManagement.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

	@NotBlank(message = "Street cannot be blank")
	private String street;

	@NotBlank(message = "landMark cannot be blank")
	private String landMark;

	@NotBlank(message = "City cannot be blank")
	private String city;

    @NotBlank(message = "Postal code cannot be blank")
    @Pattern(regexp = "^[0-9]{5,6}$", message = "Postal code must be 5 or 6 digits")
	private String postalCode;

    @NotBlank(message = "Postal code cannot be blank")
	private String state;

    @NotBlank(message = "Postal code cannot be blank")
	private String country;
}
