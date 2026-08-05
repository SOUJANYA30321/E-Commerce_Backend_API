package com.dcl.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByUser_UserId(int userId);

	Optional<Cart> findByCartId(int cartId);


}
