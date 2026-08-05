package com.dcl.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	Optional<CartItem> findByCartCartIdAndProductProductId(int cartId, int productId);

}
