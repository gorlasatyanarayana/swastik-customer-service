package com.swastik.service.customer.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swastik.service.customer.dto.CustomerLoginDto;
import com.swastik.service.customer.dto.CustomerLoginResponse;
import com.swastik.service.customer.dto.CustomerLogoutDto;
import com.swastik.service.customer.dto.CustomerLogoutResponse;
import com.swastik.service.customer.dto.CustomerRegistrationDto;
import com.swastik.service.customer.dto.CustomerRegistrationResponse;
import com.swastik.service.customer.entity.CustomerMastEntity;
import com.swastik.service.customer.entity.CustomerSessionEntity;
import com.swastik.service.customer.repository.CustomerMastRepository;
import com.swastik.service.customer.repository.CustomerSessionRepository;
import com.swastik.service.customer.service.CustomerService;

import jakarta.persistence.Column;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	CustomerMastRepository customerMastRepository;
	
	@Autowired
	CustomerSessionRepository customerSessionRepository;
	
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
		CustomerLoginResponse response = null;
		log.info("[Login] entered {} ",request);
		log.info("[Login] finding the existing  customer");
		Optional<CustomerMastEntity> user  = customerMastRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
		if(user.isPresent()) {
			log.info("[Login] user is found");
			CustomerMastEntity customerMastEntity = user.get();
			
			log.info("[Login] creating the token");
			
			UUID uuid = UUID.randomUUID();
	        String uuidAsString = uuid.toString();
	        
			CustomerSessionEntity customerSessionEntity = new CustomerSessionEntity();
			customerSessionEntity.setCustId(customerMastEntity.getId());
			customerSessionEntity.setIsActive(true);
			customerSessionEntity.setToken(uuidAsString);
			customerSessionRepository.save(customerSessionEntity);
			
			log.info("[Login] token  is created");
			response = CustomerLoginResponse.builder().success(true).
					customerId(customerMastEntity.getId().toString()).
					token(uuidAsString).
					build();
			
		} else {
			log.info("[Login] user not found");
			response = CustomerLoginResponse.builder().success(false).build();
		}
		
		return response;
	}

	@Override
	public CustomerLogoutResponse logout(CustomerLogoutDto request, String token) {

		// TODO Auto-generated method stub
		CustomerLogoutResponse response = null;
		log.info("[Logout] entered {} ",request);
		log.info("[Logout] finding the existing token");
		
	    String[] tokenArray = token.split(" ");
        String tokenS = tokenArray[1];
		
		Optional<CustomerSessionEntity> customerSession  = customerSessionRepository.findByCustIdAndToken(UUID.fromString(request.getCustomerId()), tokenS);
		if(customerSession.isPresent()) {
			log.info("[Logout] user is found");
			CustomerSessionEntity customerSessionEntity = customerSession.get();
			
			log.info("[Logout] Invalidating the token");
			
			customerSessionEntity.setIsActive(false);       
			
			customerSessionRepository.save(customerSessionEntity);
			
			log.info("[Logout] Logout  is successful");
			response = CustomerLogoutResponse.builder().success(true).
					customerId(customerSessionEntity.getId().toString()).					
					build();
			
		} else {
			log.info("[Logout] user not found");
			response = CustomerLogoutResponse.builder().success(false).build();
		}
		
		return response;
	
	}

}
