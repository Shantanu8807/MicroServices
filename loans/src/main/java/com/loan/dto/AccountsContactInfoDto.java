package com.loan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="accounts")
public record AccountsContactInfoDto(String msg,Map<String,String> contactDetails,List<String> onCallSupport) {
      
	
}
