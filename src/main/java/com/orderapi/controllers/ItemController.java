package com.orderapi.controllers;

import java.util.List;
import java.util.NoSuchElementException;


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

import com.orderapi.entities.Item;
import com.orderapi.service.ItemService;


    @RestController
	@RequestMapping("/items")
	public class ItemController {
    	
    	@Autowired
	    private ItemService itemService;
    	
    	

	    

	    @GetMapping("/GetItems")
	    public ResponseEntity<List<Item>> getAllItems() {
	        List<Item> items = itemService.getAllItems();
	        return new ResponseEntity<>(items, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
	        Item item = itemService.getItemById(itemId);
	        if (item != null) {
	            return new ResponseEntity<>(item, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/CreateItem")
	    public ResponseEntity<Item> createItem(@RequestBody Item item) {
	        Item newItem = itemService.createItem(item);
	        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
	    }
	    
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
	        Item updatedItem = itemService.updateItem(itemId, item);
	        if (updatedItem != null) {
	            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PostMapping("/sell/{id}")
	    public ResponseEntity<String> sellItem(@PathVariable Long itemId,@RequestParam int quantity){
	    	 try {
	             itemService.sellItem(itemId,quantity);
	             return ResponseEntity.ok("Item sold successfully");
	         } catch (NoSuchElementException e) {
	             return ResponseEntity.notFound().build();
	         } catch (IllegalStateException e) {
	             return ResponseEntity.badRequest().body("Item is already sold out");
	         } catch (Exception e) {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sell item");
	         }
	     }
	    
	   

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId ){
	        itemService.deleteItem(itemId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	   
	}
	


	
	
	
	

