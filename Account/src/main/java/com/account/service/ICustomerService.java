package com.account.service;

import com.account.dto.CutomerDetailsDto;

public interface ICustomerService {
     
	
	public CutomerDetailsDto fetchCutomerDetails(String correlationId,String mobileNumber);
}
