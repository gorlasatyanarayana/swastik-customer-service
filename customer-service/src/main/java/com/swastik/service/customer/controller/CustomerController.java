package com.swastik.service.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swastik.service.customer.dto.CustomerLoginDto;
import com.swastik.service.customer.dto.CustomerLoginResponse;
import com.swastik.service.customer.dto.CustomerLogoutDto;
import com.swastik.service.customer.dto.CustomerLogoutResponse;
import com.swastik.service.customer.dto.CustomerRegistrationDto;
import com.swastik.service.customer.dto.CustomerRegistrationResponse;
import com.swastik.service.customer.service.CustomerService;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping(value = "/{version}/customer/register")
	public ResponseEntity<?> register(@PathVariable("version") String version, @RequestBody CustomerRegistrationDto request){
		log.info("Entered in method-register of class-CustomerController");
		CustomerRegistrationResponse response = customerService.register(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/{version}/customer/login")
	public ResponseEntity<?> login(@PathVariable("version") String version, @RequestBody CustomerLoginDto request){
		log.info("Entered in method-login of class-CustomerController");
		CustomerLoginResponse response = customerService.login(request);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/{version}/customer/logout")
	public ResponseEntity<?> logout(@PathVariable("version") String version, @RequestHeader("Authorization") String token ,@RequestBody CustomerLogoutDto request){
		log.info("Entered in method-logout of class-CustomerController");
		
		log.info("Token : {}",token);
		

        String[] tokenArray = token.split(" ");
        String tokenS = tokenArray[1];
    
		
		CustomerLogoutResponse response = customerService.logout(request, tokenS);
		return ResponseEntity.ok(response);
	}

}
