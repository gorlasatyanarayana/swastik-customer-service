package com.swastik.service.customer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swastik.service.customer.entity.CustomerSessionEntity;

public interface CustomerSessionRepository extends JpaRepository<CustomerSessionEntity, UUID>{
	
	Optional<CustomerSessionEntity> findByCustIdAndToken(UUID custId, String token);
	Optional<CustomerSessionEntity> findByToken(String token);
	

}
