package com.dcl.shop.service;

import java.util.List;

import com.dcl.shop.model.Order;

public interface OrderService {

	Order placeOrder(int userId);

	Order viewOrder(int orderId);

	List<Order> displayOrderHistoryByUserId(int userId);

	Order cancelOrder(int orderId);

	double totalPriceCalculation(int orderId);

	List<Order> displayAllOrders();

	Order deleteOrder(int orderId);

}
