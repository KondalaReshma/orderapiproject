package com.orderapi.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long itemId ;
	private String name;
    private String description;
    
    private double price;
    private String category;
    private String status;
    private int totalAvailable;
    private int quantity;
    
    @OneToOne(mappedBy = "item",cascade = CascadeType.ALL)
    private Pricing pricing ;
	
}
