package com.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderapi.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
