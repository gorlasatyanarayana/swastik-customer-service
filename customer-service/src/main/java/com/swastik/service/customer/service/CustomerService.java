package com.swastik.service.customer.service;

import com.swastik.service.customer.dto.CustomerLoginDto;
import com.swastik.service.customer.dto.CustomerLoginResponse;
import com.swastik.service.customer.dto.CustomerRegistrationDto;
import com.swastik.service.customer.dto.CustomerRegistrationResponse;

public interface CustomerService {	
	
	CustomerRegistrationResponse register(CustomerRegistrationDto request);
	CustomerLoginResponse login(CustomerLoginDto request);
}
