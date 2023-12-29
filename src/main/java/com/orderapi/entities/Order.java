package com.orderapi.entities;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.orderapi.Enums.OrderStatus;
import com.orderapi.Enums.PaymentMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order_tracking")
public class Order {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@CreationTimestamp
	private LocalDateTime orderDate;
	
	private double billAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="item_id")
	private Item item;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address shippingAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	
	
	
		
	}


