package com.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderapi.entities.Pricing;

@Repository
public interface PricingRepository  extends  JpaRepository<Pricing,Long>{

}
