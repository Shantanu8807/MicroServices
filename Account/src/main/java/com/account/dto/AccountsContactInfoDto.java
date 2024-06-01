package com.account.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix="accounts")
@Getter 
@Setter
public class AccountsContactInfoDto
{   
	String msg;
	Map<String,String> contactDetails;
	List<String> onCallSupport;
}
      
	
