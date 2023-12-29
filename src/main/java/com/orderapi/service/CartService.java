package com.orderapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orderapi.entities.Cart;
import com.orderapi.entities.Item;
import com.orderapi.entities.Pricing;
import com.orderapi.entities.User;
import com.orderapi.repository.CartRepository;
import com.orderapi.repository.ItemRepository;
import com.orderapi.repository.PricingRepository;
import com.orderapi.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PricingRepository pricingRepository;

	public Cart createCart(Cart cart, Long userId) {

		User user = userRepository.findById(userId).orElse(null);
		cart.setUser(user);
		if (user != null) {

			return cartRepository.save(cart);

		} else
			return null;
	}

	public Cart updateCart(Cart cart, Long cartId, Long itemId, double totalPrice) {
		Cart existingCart = cartRepository.findById(cartId).orElse(null);
		if (existingCart != null) {
			existingCart.setTotalPrice(totalPrice);

			return cartRepository.save(existingCart);
		}
		return null;
	}

	public void deleteCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}

	public void addItemToCart(Long cartId, Long itemId, int quantity) {
		Cart cart = cartRepository.findById(cartId).orElse(null);

		Item existingItem = cart.getItems().stream().filter(item -> item.getItemId().equals(itemId)).findFirst()
				.orElse(null);

		if (existingItem != null) {
			existingItem.setQuantity(existingItem.getQuantity() + quantity);
		} else {
			Item newItem = createNewItem(itemId, quantity);
			cart.getItems().add(newItem);
		}

		cartRepository.save(cart);
	}

	private Item createNewItem(Long itemId, int quantity) {

		Item newItem = new Item();
		newItem.setItemId(itemId);
		newItem.setQuantity(quantity);

		return newItem;
	}

	public List<Item> getItemsInCart(Long cartId) {
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
		return cart.getItems();
	}

	public void removeItemFromCart(Long cartId, Long itemId) {

		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

		cart.removeItem(item);
		cartRepository.save(cart);

	}

	public double getTotalPrice(Long cartId) {
		java.util.Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if (optionalCart.isPresent()) {
			Cart cart = optionalCart.get();
			List<Item> cartItems = cart.getItems();

			double totalPrice = 0;
			for (Item cartItem : cartItems) {
				double itemPrice = cartItem.getPrice();
				int quantity = cartItem.getQuantity();
				double discount = getDiscountForItem(cartItem.getItemId());
				totalPrice += applyDiscount(itemPrice * quantity, discount);
			}
			return totalPrice;
		}
		return cartId;
	}

	private double getDiscountForItem(Long itemId) {
		Pricing pricing = pricingRepository.findById(itemId).orElse(null);
		return (pricing != null) ? pricing.getDiscount() : 0;
	}

	private double applyDiscount(double price, double discount) {
		return price - (price * discount / 100);
	}
}
