package com.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orderapi.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	
	  @Query(value = "SELECT * FROM order_tracking o WHERE o.user_id = :userId",nativeQuery = true)
	   List<Order> findByUserId(@Param("userId") Long userId);

	
	  
	//  @Query(value = "SELECT * FROM order_tracing  o where  o.user.user_name name LIKE = :username",nativeQuery=true)
	//List<Order> getOrdersOfUserByName(String username);
	
   
	  
	  @Query(value = "SELECT * FROM order_tracking o where o.order_status= 'Delivered'",nativeQuery=true)  
	  List<Order> getDeliveredOrders();


}
  