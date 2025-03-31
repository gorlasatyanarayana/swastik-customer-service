package com.swastik.service.customer.service;

public interface TokenService {
	 Boolean validateToken(String token);
	 
	 void invalidateToken(String token);
}
