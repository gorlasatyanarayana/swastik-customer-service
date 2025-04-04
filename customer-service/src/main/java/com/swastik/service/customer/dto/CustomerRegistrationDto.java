package com.swastik.service.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private String address;
}
