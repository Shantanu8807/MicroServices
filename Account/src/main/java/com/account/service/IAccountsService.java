package com.account.service;

import com.account.dto.CustomerDto;

public interface IAccountsService {
	
	void createAccount(CustomerDto customerDto);
	
	public CustomerDto fetchAccount(String mobileNumber);
    
	public boolean updateAccount(CustomerDto customerDto);
	
	public boolean deleteAccount(String mobileNumber);	
}
