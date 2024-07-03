package com.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.account.dto.AccountsDto;
import com.account.dto.CardsDto;
import com.account.dto.CutomerDetailsDto;
import com.account.dto.LoansDto;
import com.account.entity.Accounts;
import com.account.entity.Customer;
import com.account.exception.ResourceNotFoundException;
import com.account.mapper.AccountsMapper;
import com.account.mapper.CustomerMapper;
import com.account.repo.AccountRepo;
import com.account.repo.CustomerRepo;
import com.account.service.ICustomerService;
import com.account.service.clients.CardsFeignClients;
import com.account.service.clients.LoansFeignClient;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CardsFeignClients cardsFeignClient;
	@Autowired
	private LoansFeignClient loansFeignClient;

	@Override
	public CutomerDetailsDto fetchCutomerDetails(String mobileNumber, String correlationId) {
		Customer customer = customerRepo.findByMobileNumber(mobileNumber);
		if (customer == null) {
			throw new ResourceNotFoundException("no Customer found with mobile number " + mobileNumber);
		}

		Accounts account = accountRepo.findByCustomerId(customer.getCustomerId());
		if (account == null) {
			throw new ResourceNotFoundException("no account found with mobile number " + mobileNumber);
		}
		CutomerDetailsDto cutomerDetailsDdto = CustomerMapper.mapToCustomerDetailsDto(customer,
				new CutomerDetailsDto());
		cutomerDetailsDdto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

		ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,
				mobileNumber);
		if (loansDtoResponseEntity != null) {
			cutomerDetailsDdto.setLoansDto(loansDtoResponseEntity.getBody());
		}
		ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,
				mobileNumber);

		if (cardsDtoResponseEntity != null) {
			cutomerDetailsDdto.setCardsDto(cardsDtoResponseEntity.getBody());
		}
		return cutomerDetailsDdto;

	}

}
