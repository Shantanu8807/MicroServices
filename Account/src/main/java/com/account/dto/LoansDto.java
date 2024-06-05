package com.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoansDto {

	private String mobileNumber;

	private String loanNumber;

	private String loanType;
	
	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;

}
