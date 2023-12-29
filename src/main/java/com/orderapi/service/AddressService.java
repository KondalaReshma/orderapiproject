package com.orderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderapi.entities.Address;
import com.orderapi.entities.User;
import com.orderapi.repository.AddressRepository;
import com.orderapi.repository.UserRepository;

@Service
public class AddressService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	public Address addAddress(Address address, Long userId) {

		User user = userRepository.findById(userId).orElse(null);
		address.setUser(user);
		// Address address = addressRepository.findById(userId)

		// address.setUser(user);
		return addressRepository.save(address);

	}

	public void deleteCart(Long addressId) {

		Address address = addressRepository.findById(addressId).orElse(null);
		addressRepository.deleteById(addressId);

	}

	public Address updateAddress(Address address, Long addressId, Long userId) {

		Address updateAddress = addressRepository.findById(addressId).orElse(null);

		if (updateAddress != null) {

			updateAddress.setStreetname(address.getStreetname());
			updateAddress.setCity(address.getCity());
			updateAddress.setState(address.getState());
			updateAddress.setCountry(address.getCountry());
			updateAddress.setPincode(address.getPincode());

			addressRepository.save(updateAddress);
		}

		return updateAddress;
	}

}
