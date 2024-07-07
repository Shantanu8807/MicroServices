package com.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accounts extends BaseEntity {
	
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Id
	private Long accountNumber;
	
	private String accoutnType;
	
	private String branchAddress;

	private Boolean communicationSwitch;
}
