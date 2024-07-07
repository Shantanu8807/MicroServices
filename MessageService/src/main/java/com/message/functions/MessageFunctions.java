package com.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.message.dto.AccountsMessageDto;

@Configuration
public class MessageFunctions {

	private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

	@Bean
	public Function<AccountsMessageDto, AccountsMessageDto> email() {
		return (accountsMsgDto) -> {

			log.info("sending email with the details :" + accountsMsgDto.toString());

			return accountsMsgDto;

		};
	}

	@Bean
	public Function<AccountsMessageDto, Long> sms() {
		return (accountsMsgDto) -> {

			log.info("sending sms with the details :" + accountsMsgDto.toString());

			return accountsMsgDto.getAccountNumber();

		};
	}

}
