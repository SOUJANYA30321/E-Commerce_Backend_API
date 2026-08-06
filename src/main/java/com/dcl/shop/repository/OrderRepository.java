package com.dcl.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	Optional<Order> findByOrderId(int orderId);

	List<Order> findByUser_UserId(int userId);

}
