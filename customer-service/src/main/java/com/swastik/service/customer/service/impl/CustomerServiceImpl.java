package com.swastik.service.customer.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swastik.service.customer.dto.CustomerLoginDto;
import com.swastik.service.customer.dto.CustomerLoginResponse;
import com.swastik.service.customer.dto.CustomerRegistrationDto;
import com.swastik.service.customer.dto.CustomerRegistrationResponse;
import com.swastik.service.customer.entity.CustomerMastEntity;
import com.swastik.service.customer.repository.CustomerMastRepository;
import com.swastik.service.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	CustomerMastRepository customerMastRepository;
	
	@Override
	public CustomerRegistrationResponse register(CustomerRegistrationDto request) {
		// TODO Auto-generated method stub
		CustomerRegistrationResponse response = null;
		log.info("[Register] entered {} ",request);
		log.info("[Register] registering new customer");
		
		CustomerMastEntity customerMastEntity = new CustomerMastEntity();
		customerMastEntity.setFirstName(request.getFirstName());
		customerMastEntity.setLastName(request.getLastName());
		customerMastEntity.setEmail(request.getEmail());
		customerMastEntity.setPassword(request.getPassword());
		customerMastEntity.setAddress(request.getAddress());
		customerMastEntity.setPhone(request.getPhone());
		
		customerMastEntity.setCreatedBy("Customer Service");
		customerMastEntity.setCreatedAt(LocalDateTime.now());	
		customerMastEntity = customerMastRepository.save(customerMastEntity);

		if(customerMastEntity !=null && customerMastEntity.getId() != null) {
			log.info("[Register] prodcut added successfully with product id : {} ",customerMastEntity.getId());
			response = CustomerRegistrationResponse.builder().success(true).customerId(customerMastEntity.getId().toString()).build();
		} else {	
			log.info("[Register] failed to register the customer");
			response = CustomerRegistrationResponse.builder().success(false).customerId(null).build();
		}
			
		
		return response;

	
	}

	@Override
	public CustomerLoginResponse login(CustomerLoginDto request) {
		// TODO Auto-generated method stub
		return null;
	}

}
