package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.account.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	
	
	public Customer findByMobileNumber(String mobile);
	

}
