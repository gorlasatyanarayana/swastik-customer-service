package com.swastik.service.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swastik.service.customer.entity.CustomerMastEntity;

public interface CustomerMastRepository extends JpaRepository<CustomerMastEntity, UUID>{
	
	Optional<CustomerMastEntity> findByEmailAndPassword(String email, String password);
	
	


}
