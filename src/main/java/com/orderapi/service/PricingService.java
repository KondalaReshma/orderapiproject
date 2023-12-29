package com.orderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.orderapi.entities.Item;
import com.orderapi.entities.Pricing;
//import com.orderapi.repository.ItemRepository;
import com.orderapi.repository.PricingRepository;

@Service
public class PricingService {

	@Autowired
	private PricingRepository pricingRepository;

//	@Autowired
//	private ItemRepository itemRepository;

	public Pricing addDiscount(Long pricingId, double discount) {
		Pricing pricing = pricingRepository.findById(pricingId).orElse(null);
		if (pricing != null) {
			pricing.setDiscount(discount);
			return pricingRepository.save(pricing);
		}
		return null;

	}

	public Pricing removeDiscount(Long pricingId) {
		Pricing pricing = pricingRepository.findById(pricingId).orElse(null);
		if (pricing != null) {
			pricing.setDiscount(0);
			return pricingRepository.save(pricing);
		}
		return null;
	}

	public Pricing addSpecialOffer(Long pricingId, String specialOffer) {

		Pricing pricing = pricingRepository.findById(pricingId).orElse(null);
		if (pricing != null) {
			pricing.setSpecialOffer(specialOffer);
			return pricingRepository.save(pricing);
		}
		return null;
	}

	public Pricing removeSpecialOffer(Long pricingId) {
		Pricing pricing = pricingRepository.findById(pricingId).orElse(null);
		if (pricing != null) {
			pricing.setSpecialOffer(null);
			return pricingRepository.save(pricing);
		}
		return null;
	}

}