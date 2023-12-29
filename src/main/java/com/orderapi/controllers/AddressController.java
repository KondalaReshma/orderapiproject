package com.orderapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.orderapi.entities.Address;
import com.orderapi.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	

	@PostMapping("/addAddress/{userId}")
	public ResponseEntity<Address> addAddress(@RequestBody Address address, @PathVariable Long userId) {

		Address newaddress = addressService.addAddress(address, userId);

		return new ResponseEntity<>(newaddress, HttpStatus.OK);
	}

	@DeleteMapping("/{addressId}")

	public ResponseEntity<String> removeAddress(@PathVariable Long addressId) {
		addressService.deleteCart(addressId);
		return ResponseEntity.ok("Address removed successfully");
	}
	
	
	@PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress( @RequestBody Address address,@PathVariable Long addressId,@RequestParam Long userId) {
        Address newaddress = addressService.updateAddress(address,addressId,userId);
        if (newaddress != null) {
            return new ResponseEntity<>(newaddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
