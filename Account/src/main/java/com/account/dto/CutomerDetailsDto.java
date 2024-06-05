package com.account.dto;

import lombok.Data;

@Data
public class CutomerDetailsDto {
	
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private AccountsDto accountsDto;
	
	private LoansDto loansDto;
	
	private CardsDto cardsDto;
	
	
	
}
