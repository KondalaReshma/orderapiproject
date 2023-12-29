package com.orderapi.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.orderapi.entities.Cart;
import com.orderapi.entities.User;
//import com.orderapi.repository.CartRepository;
import com.orderapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Autowired 
	private EmailService emailService;
	

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User createUser(User user) {
		 emailService.sendSimpleMail(user.getEmail());
		return userRepository.save(user);

	}

	public void deleteItem(Long userId) {
		userRepository.deleteById(userId);
	}

}
