package com.loan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="loans")
public record LoansContactInfoDto(String msg,Map<String,String> contactDetails,List<String> onCallSupport) {
      
	
}
