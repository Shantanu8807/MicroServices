package com.account.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.contants.Constants;
import com.account.dto.AccountsContactInfoDto;
import com.account.dto.CustomerDto;
import com.account.dto.ResponseDto;
import com.account.service.IAccountsService;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping(path = "/api/v1", produces = (MediaType.APPLICATION_JSON_VALUE))
public class AccountsController {
    
	@Autowired
	private IAccountsService iaccService;
	
	@Autowired
	private AccountsContactInfoDto accContactsInfoDto;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
		iaccService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(Constants.Status_201, Constants.Accounts_Message_201, LocalDateTime.now()));
	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
		CustomerDto customerDto = iaccService.fetchAccount(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);

	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {
		boolean isUpdated = iaccService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(Constants.Status_200, Constants.Message_200, LocalDateTime.now()));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(Constants.Status_500, Constants.Message_500, LocalDateTime.now()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
		boolean isDeleted = iaccService.deleteAccount(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(Constants.Status_200, Constants.Message_200, LocalDateTime.now()));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(Constants.Status_500, Constants.Message_500, LocalDateTime.now()));
		}
	}
	/*
	 * @GetMapping("/build-info") public ResponseEntity<String> getBuildInfo() {
	 * return ResponseEntity.status(HttpStatus.OK).body(buildVersion); }
	 */
	@Retry(name="/getContactInfo",fallbackMethod="getContactInfoFallback")
	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo()
	{
		return ResponseEntity.status(HttpStatus.OK).body(accContactsInfoDto);	
	}
	
	
	public ResponseEntity<AccountsContactInfoDto> getContactInfoFallback(Throwable throwable)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);	
	}
	

}
