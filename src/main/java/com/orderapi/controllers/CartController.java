package com.orderapi.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderapi.entities.Cart;
import com.orderapi.entities.Item;
import com.orderapi.repository.CartRepository;
import com.orderapi.service.CartService;
//import com.orderapi.service.ItemService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
//	@Autowired
//	private ItemService itemService;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart,@PathVariable Long userId) {
		
        Cart newCart = cartService.createCart(cart,userId);
        return ResponseEntity.ok(newCart);
    }
	
	@DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Record deleted successfully");
    }
	
	@PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateCart( @RequestBody Cart cart,@PathVariable Long cartId,@RequestParam Long itemId,@RequestParam double totalPrice) {
        Cart updatedCart = cartService.updateCart( cart,cartId,itemId,totalPrice);
        if (updatedCart!= null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	
	
	 @PostMapping("/{itemId}")
	    public ResponseEntity<Cart> addItemToCart(@RequestParam Long cartId,@PathVariable Long itemId,@RequestParam int quantity) {
	        cartService.addItemToCart(cartId, itemId,quantity);
	        Cart cart = cartRepository.findById(cartId).get();
	        return new ResponseEntity<>(cart,HttpStatus.OK);
	    }
	 
	
	 @DeleteMapping("/{itemId}")
	    public ResponseEntity<String> removeCartItem(@RequestParam Long cartId,@PathVariable Long itemId) {
	        cartService.removeItemFromCart(cartId,itemId);
	        return ResponseEntity.ok("Item removed successfully");
	    }
	 
      	 
	 

	    @GetMapping("/{cartId}/getcartitems")
	    public List<Item> getItemsInCart(@PathVariable Long cartId) {
	        return cartService.getItemsInCart(cartId);
	    }
	    
	    
          @GetMapping("/totalprice/{cartId}")
	      public  ResponseEntity<Double> calculateTotalPrice(@PathVariable Long cartId) {
        	   
        	    double  totalPrice = cartService.getTotalPrice(cartId);
        	    return ResponseEntity.ok(totalPrice);
           }
	    	
}
	    	
	    	
	    	
	    	
	    	
	    	
	 	
	    	
	    
	
	
	

