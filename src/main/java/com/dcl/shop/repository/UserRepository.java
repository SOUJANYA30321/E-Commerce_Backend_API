package com.dcl.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserId(int userId);

	Optional<User> findByEmail(String email);

	Optional<User> findByName(String name);

	List<User> findByNameContainingIgnoreCase(String keyword);

	Boolean existsByEmailIgnoreCase(String email);

	List<User> findAllByOrderByNameAsc();

}
