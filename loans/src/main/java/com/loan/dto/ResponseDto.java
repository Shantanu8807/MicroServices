package com.loan.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
	
	private String statusCode;
	
	private String statusMessage;
	
	private LocalDateTime dateTime;
	
	
	

}
