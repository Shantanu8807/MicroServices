package com.account.service.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.account.dto.CardsDto;

import jakarta.validation.constraints.Pattern;


@Component
public class CardsFallback implements CardsFeignClients {

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
