package com.orderapi.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.orderapi.Enums.OrderStatus;
import com.orderapi.Enums.PaymentMethod;
import com.orderapi.entities.Order;
import com.orderapi.service.EmailService;
import com.orderapi.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService ;
	
	@Autowired
	private EmailService emailService;
		
	
	@PostMapping("/createorder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order,@RequestParam Long userId,@RequestParam Long itemId,@RequestParam Long addressId,@RequestParam OrderStatus orderstatus,@RequestParam PaymentMethod paymentmethod) {
        
		Order newOrder = orderService.createOrder(order,userId,itemId,addressId,orderstatus,paymentmethod);
        
		emailService.sendSimpleMail(order.getUser().getEmail());
		
		
		return ResponseEntity.ok(newOrder);
		
		
    }
	
	
	@GetMapping("/trackstatus/{orderId}")	
	public OrderStatus getOrderStatus(@PathVariable Long orderId) {
		
		OrderStatus order = orderService.getOrderStatus(orderId);	
	
		return order;	
	
	}
	
	@PutMapping("/{orderId}")
	public OrderStatus updateOrderStatus(@PathVariable Long orderId,@RequestParam OrderStatus orderstatus)
	{	
		Order order = orderService. updateOrderStatus(orderId,orderstatus);
		return orderstatus;
			
	}
	
	
	@GetMapping("/{userId}")
	public List<Order> getPrevoiusOrders(@PathVariable Long userId) {	
		return  orderService.getPrevoiusOrders(userId);		
		 
	}
	
	@GetMapping("/getDeliveredOrders")
	public List<Order> getDeliveredOrders(){
		return orderService.getDeliveredOrders();
	}
	
	@GetMapping("/get-orders/{username}")
	public List<Order> getOrdersByUserName(@PathVariable String username){
		
		return  orderService.getUserOrders(username);
	}
	
	@GetMapping("/Like/{username}")
	public List<Order> getOrdersByUserNameLike(@PathVariable String username){
		return orderService.getUserOrdersContaining(username);
	}
	
	
	
}
