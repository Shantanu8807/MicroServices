package com.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix="accounts")
public record AccountsContactInfoDto(String msg,Map<String,String> contactDetails,List<String> onCallSupport) {
      
	
}
