package com.orderapi.entities;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
//import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class Cart {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;   
	
	private  double totalPrice;
	
	
	@ManyToMany
	private List<Item> items = new ArrayList<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	
	

}
    