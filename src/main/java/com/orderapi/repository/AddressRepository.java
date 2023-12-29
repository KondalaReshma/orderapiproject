package com.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderapi.entities.Address;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long>{

	
	
	

}
