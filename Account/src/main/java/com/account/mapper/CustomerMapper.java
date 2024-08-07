package com.account.mapper;

import com.account.dto.CustomerDto;
import com.account.dto.CutomerDetailsDto;
import com.account.entity.Customer;

public class CustomerMapper {
	
	public static CustomerDto mapToCustomerDto(Customer cus, CustomerDto cusDto) {
		cusDto.setEmail(cus.getEmail());
		cusDto.setMobileNumber(cus.getMobileNumber());
		cusDto.setName(cus.getName());

		return cusDto;
	}

	public static Customer mapToCustomer(CustomerDto cusDto, Customer cus) {
		cus.setEmail(cusDto.getEmail());
		cus.setMobileNumber(cusDto.getMobileNumber());
		cus.setName(cusDto.getName());

		return cus;
	}
	
	public static CutomerDetailsDto mapToCustomerDetailsDto(Customer cus,CutomerDetailsDto cusDto) {
		cusDto.setEmail(cus.getEmail());
		cusDto.setMobileNumber(cus.getMobileNumber());
		cusDto.setName(cus.getName());

		return cusDto;
	}


}
