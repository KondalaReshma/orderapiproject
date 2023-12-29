package com.orderapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderapi.entities.Item;
//import com.orderapi.entities.Pricing;
import com.orderapi.repository.ItemRepository;
//import com.orderapi.repository.PricingRepository;


@Service
public class ItemService {
	
	
	@Autowired
	private  ItemRepository itemRepository ;
	
	
	public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
	
	 public Item getItemById(Long itemId) {
	        return itemRepository.findById(itemId).orElse(null);
	    }
	
	 public Item createItem(Item item) {
		 
	        return itemRepository.save(item);
	    }

	 public void deleteItem(Long itemId) {
	        itemRepository.deleteById(itemId);
	    }
	 
	 public Item updateItem(Long itemId, Item newItemData) {
	        Item existingItem = itemRepository.findById(itemId).orElse(null);
	        if (existingItem != null) {
	            existingItem.setName(newItemData.getName());
	            existingItem.setDescription(newItemData.getDescription());
	            existingItem.setPrice(newItemData.getPrice());
	            existingItem.setCategory(newItemData.getCategory());
	            existingItem.setStatus(newItemData.getStatus());
	            existingItem.setTotalAvailable(newItemData.getTotalAvailable());
	            return itemRepository.save(existingItem);
	        }
	        return null;
	    }
	 
	 public void sellItem(Long itemId,int quantity) {
	        Item existingItem = itemRepository.findById(itemId).orElse(null);
	        if (existingItem != null && existingItem.getTotalAvailable() >= quantity) {
	            existingItem.setTotalAvailable(existingItem.getTotalAvailable() - quantity);
	            itemRepository.save(existingItem);}
	            
	            else {
	                throw new IllegalStateException("Insufficient available quantity");
	            
	        }
	        }
	    
}
	 
	