package com.account.mapper;

import com.account.dto.AccountsDto;
import com.account.entity.Accounts;

public class AccountsMapper {

	public static AccountsDto mapToAccountsDto(Accounts acc, AccountsDto accDto) {
		accDto.setAccountNumber(acc.getAccountNumber());
		accDto.setAccoutnType(acc.getAccoutnType());
		accDto.setBranchAddress(acc.getBranchAddress());

		return accDto;
	}

	public static Accounts mapToAccounts(AccountsDto accDto, Accounts acc) {
		acc.setAccountNumber(accDto.getAccountNumber());
		acc.setAccoutnType(accDto.getAccoutnType());
		acc.setBranchAddress(accDto.getBranchAddress());

		return acc;
	}

}
