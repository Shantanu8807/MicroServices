package com.account.service.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.account.dto.LoansDto;

import jakarta.validation.constraints.Pattern;


@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId,String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
