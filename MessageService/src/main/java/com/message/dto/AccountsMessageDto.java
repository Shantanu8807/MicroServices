package com.message.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountsMessageDto {
     
	Long accountNumber;
	String name;
	String email;
	String mobileNumber;
	
	
}
