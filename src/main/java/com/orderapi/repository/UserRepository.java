package com.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
//	@Query(value = "SELECT * FROM user u WHERE u.username = ?1",nativeQuery = true)
	 User findByUsername(String username);
	
	List<User> findByUsernameContaining(String username);
	
}
