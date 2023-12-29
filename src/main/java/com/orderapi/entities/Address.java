package com.orderapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {
	
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long addressId;
     private  String streetname;
     private String city;
     private String state;
     private String country;
     private String pincode;
     
     @ManyToOne
     @JsonBackReference
     private User user ;
 
	
}
