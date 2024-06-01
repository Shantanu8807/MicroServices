package com.loan.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(prefix="loans")
@Getter
@Setter
public class LoansContactInfoDto
{
	String msg;
	Map<String,String> contactDetails;
	List<String> onCallSupport;	
}
