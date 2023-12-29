package com.orderapi.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderapi.Enums.OrderStatus;
import com.orderapi.Enums.PaymentMethod;
import com.orderapi.entities.Address;
import com.orderapi.entities.Order;
import com.orderapi.entities.User;
import com.orderapi.repository.AddressRepository;
import com.orderapi.repository.ItemRepository;
import com.orderapi.repository.OrderRepository;
import com.orderapi.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private AddressRepository addressRepository;



	public Order createOrder(Order order, Long userId, Long itemId, Long addressId, OrderStatus orderstatus,
			PaymentMethod paymentmethod) {

		User user = userRepository.findById(userId).orElse(null);

		com.orderapi.entities.Item item = itemRepository.findById(itemId).orElse(null);

		Address address = addressRepository.findById(addressId).orElse(null);

		order.setUser(user);

		order.setItem(item);

		order.setOrderStatus(orderstatus);

		order.setShippingAddress(address);

		order.setPaymentMethod(paymentmethod);

		
		
		
		orderRepository.save(order);
		
		
		

		return order;
	}

	public OrderStatus getOrderStatus(Long orderId) {

		Order order = orderRepository.findById(orderId).orElse(null);

		return order.getOrderStatus();
	}

	public Order updateOrderStatus(Long orderId, OrderStatus orderstatus) {

		Order order = orderRepository.findById(orderId).orElse(null);

		order.setOrderStatus(orderstatus);

		return orderRepository.save(order);

	}

	public List<Order> getPrevoiusOrders(Long userId) {

		return orderRepository.findByUserId(userId);

	}

	// public List<Order> getOrdersOfUserByName(String username) {

	public List<Order> getDeliveredOrders() {

		List<Order> deliveredOrders = orderRepository.getDeliveredOrders();

		return deliveredOrders;
	}

	public List<Order> getUserOrders(String username) {

		User user = userRepository.findByUsername(username);
		return orderRepository.findByUserId(user.getUserId());
	}

	public List<Order> getUserOrdersContaining(String username) {

		List<User> users = userRepository.findByUsernameContaining(username);
		List<Order> orders = new ArrayList<>();
		for (User user : users) {
			orders.addAll(orderRepository.findByUserId(user.getUserId()));
		}
		return orders;
	
	}
	
	

}