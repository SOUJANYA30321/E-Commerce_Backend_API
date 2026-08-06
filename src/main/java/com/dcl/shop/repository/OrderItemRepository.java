package com.dcl.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
