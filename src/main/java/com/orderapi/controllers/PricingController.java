package com.orderapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderapi.entities.Pricing;
import com.orderapi.service.PricingService;

@RestController
@RequestMapping("/pricing")
public class PricingController {

	@Autowired
	private PricingService pricingService;

	@GetMapping("/welcome")
	public String pricing() {
		return "welcome to pricing";
	}

	@PostMapping("/addDiscount/{pricingId}")
	public ResponseEntity<Pricing> addDiscount(@PathVariable Long pricingId, @RequestParam double discount) {
		Pricing updatedPricing = pricingService.addDiscount(pricingId, discount);
		if (updatedPricing != null) {
			return ResponseEntity.ok(updatedPricing);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/removeDiscount/{pricingId}")
	public ResponseEntity<Pricing> removeDiscount(@PathVariable Long pricingId) {
		Pricing updatedPricing = pricingService.removeDiscount(pricingId);
		if (updatedPricing != null) {
			return ResponseEntity.ok(updatedPricing);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("{pricingId}/addSpecialOffer")
	public ResponseEntity<Pricing> addSpecialOffer(@PathVariable Long pricingId, @RequestParam String specialOffer) {
		Pricing updatedPricing = pricingService.addSpecialOffer(pricingId, specialOffer);
		if (updatedPricing != null) {
			return ResponseEntity.ok(updatedPricing);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{pricingId}/removeSpecialOffer")
	public ResponseEntity<Pricing> removeSpecialOffer(@PathVariable Long pricingId) {
		Pricing updatedPricing = pricingService.removeSpecialOffer(pricingId);
		if (updatedPricing != null) {
			return ResponseEntity.ok(updatedPricing);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
