package com.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.dto.CutomerDetailsDto;
import com.account.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private ICustomerService iCustomerService;
	
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CutomerDetailsDto> fetchCustomerDetails(@RequestParam String mobileNumber)
	{
		CutomerDetailsDto cutomerDetailsDto=iCustomerService.fetchCutomerDetails(mobileNumber);
	    
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cutomerDetailsDto);
	
	}
	

}
