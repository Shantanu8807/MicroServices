package com.loan.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loan.constants.LoansConstants;
import com.loan.dto.LoansContactInfoDto;
import com.loan.dto.LoansDto;
import com.loan.dto.ResponseDto;
import com.loan.service.ILoansService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

/**
 * @author Eazy Bytes
 */

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class LoansController {
     
	private static final Logger logger=LoggerFactory.getLogger(LoansController.class);
	   
	
	private ILoansService iLoansService;
	
	private LoansContactInfoDto loansContactsInfoDto;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoan(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		iLoansService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201, LocalDateTime.now()));
	}

	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("shantanu-correlation-id")String correlationId,
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		logger.debug("correlation id is   "+correlationId);
		LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(loansDto);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
		boolean isUpdated = iLoansService.updateLoan(loansDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200, LocalDateTime.now()));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
					new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE, LocalDateTime.now()));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteLoanDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200, LocalDateTime.now()));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
					new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE, LocalDateTime.now()));
		}
	}
	
	@GetMapping("/contact-info")
	public ResponseEntity<LoansContactInfoDto> getContactInfo()
	{
		return ResponseEntity.status(HttpStatus.OK).body(loansContactsInfoDto);	
	}

}
