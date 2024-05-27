package com.account.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.account.contants.Constants;
import com.account.dto.AccountsDto;
import com.account.dto.CustomerDto;
import com.account.entity.Accounts;
import com.account.entity.Customer;
import com.account.exception.CustomerAlreadyExistsException;
import com.account.exception.ResourceNotFoundException;
import com.account.mapper.AccountsMapper;
import com.account.mapper.CustomerMapper;
import com.account.repo.AccountRepo;
import com.account.repo.CustomerRepo;
import com.account.service.IAccountsService;

@Service
public class AccountServiceImpl implements IAccountsService {

	private AccountRepo accountRepo;
	private CustomerRepo customerRepo;

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer cus = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
		if (cus == null) {
			throw new CustomerAlreadyExistsException("Customer Already Exists with this mobile number");
		}
		Customer customer = new Customer();
		customer = CustomerMapper.mapToCustomer(customerDto, customer);
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Anonymus");
		Customer savedCustomer = customerRepo.save(customer);

		Accounts newAccount = createNewAccount(savedCustomer);

		accountRepo.save(newAccount);
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(90000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccoutnType(Constants.SAVINGS);
		newAccount.setBranchAddress(Constants.Address);
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCreatedBy("Anonymus");
		return newAccount;
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber);
		if (customer == null) {
			throw new ResourceNotFoundException("no Customer found with mobile number " + mobileNumber);
		}

		Accounts account = accountRepo.findByCustomerId(customer.getCustomerId());
		if (account == null) {
			throw new ResourceNotFoundException("no account found with mobile number " + mobileNumber);
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto = CustomerMapper.mapToCustomerDto(customer, customerDto);
		AccountsDto accountsDto = new AccountsDto();
		accountsDto = AccountsMapper.mapToAccountsDto(account, accountsDto);
		customerDto.setAccountsDto(accountsDto);
		return customerDto;
	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		
		boolean isUpdated=false;
		AccountsDto accountsDto=customerDto.getAccountsDto();
		if(accountsDto!=null)
		{
			Accounts accounts=accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
					()-> new ResourceNotFoundException("No Account Found")
					);
			
			AccountsMapper.mapToAccounts(accountsDto, accounts);
			accounts=accountRepo.save(accounts);
			Long customerId=accounts.getCustomerId();
			Customer customer=customerRepo.findById(customerId).orElseThrow(
					()-> new ResourceNotFoundException("No Customer Found"));
		    CustomerMapper.mapToCustomer(customerDto, customer);
		    customerRepo.save(customer);
		    isUpdated=true;
		}
		return isUpdated;
		}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer= customerRepo.findByMobileNumber(mobileNumber);
		if(customer==null) {
			throw new ResourceNotFoundException("customer with mobile number :"+mobileNumber);
		}
		
		accountRepo.deleteByCustomerId(customer.getCustomerId());
		customerRepo.deleteById(customer.getCustomerId());
		return true;
				
	}

}
