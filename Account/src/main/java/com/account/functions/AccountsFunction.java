package com.account.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.account.service.IAccountsService;

@Configuration
public class AccountsFunction {
	
	private static final Logger log= LoggerFactory.getLogger(AccountsFunction.class);
	
	@Bean
	public Consumer<Long> updateCommunication(IAccountsService accountsService)
	{
		return accountNumber -> {
			
			log.info("updating info for "+accountNumber);
		    accountsService.updateCommunicationSwitch(accountNumber);
		};
	}

}
