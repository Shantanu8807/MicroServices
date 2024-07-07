package com.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsMessageDto {
	
	
	Long accountNumber;
	String name;
	String email;
	String mobileNumber;
	
	

}
