package com.swastik.service.customer.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "swastikcustomer", name = "cust_session")
public class CustomerSessionEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6299952695275861705L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true,  nullable = false)
	private UUID id;
	
	@Column(name = "cust_id")
	private UUID custId;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "created_by")
	private String createdBy;


}
