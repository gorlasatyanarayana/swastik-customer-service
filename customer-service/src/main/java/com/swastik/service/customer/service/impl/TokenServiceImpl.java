package com.swastik.service.customer.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.swastik.service.customer.entity.CustomerSessionEntity;
import com.swastik.service.customer.repository.CustomerSessionRepository;
import com.swastik.service.customer.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{
	
	private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	CustomerSessionRepository customerSessionRepository;

	@Override
	public Boolean validateToken(String token) {
		// TODO Auto-generated method stub
		Boolean  isTokenValid = false;
		
		String[] tokenArray = token.split(" ");
	    String tokenS = tokenArray[1];
	    		
		log.info("[Validate-Token] entered");
		log.info("[Validate-Token] finding the existing token");
		
		Optional<CustomerSessionEntity> customerSession  = customerSessionRepository.findByToken(tokenS);
		if(customerSession.isPresent()) {
			log.info("[Validate-Token] Token is found");
			CustomerSessionEntity customerSessionEntity = customerSession.get();
			
			log.info("[Logout] Token current status : {}", customerSessionEntity.getIsActive());
			
			if(customerSessionEntity.getIsActive()) {
				isTokenValid = true;
			}		
		} 
		
		return isTokenValid;
	
	
	}

	@Override
	public void invalidateToken(String token) {
		// TODO Auto-generated method stub
		String[] tokenArray = token.split(" ");
        String tokenS = tokenArray[1];

        
		
	}

}
