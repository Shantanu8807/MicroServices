package com.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.dto.CutomerDetailsDto;
import com.account.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private static final Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private ICustomerService iCustomerService;
	
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CutomerDetailsDto> fetchCustomerDetails(@RequestHeader("shantanu-correlation-id")String correlationId,@RequestParam String mobileNumber)
	{
		
		logger.debug("shantanu-correlation-id   -"+correlationId);
		CutomerDetailsDto cutomerDetailsDto=iCustomerService.fetchCutomerDetails(correlationId,mobileNumber);
	    
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cutomerDetailsDto);
	
	}
	

}
