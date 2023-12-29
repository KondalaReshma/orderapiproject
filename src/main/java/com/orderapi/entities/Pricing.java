package com.orderapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
@Data
public class Pricing {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pricingId ;
	private double discount;
	private String specialOffer;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	@JsonBackReference
	private Item item;
	

}
