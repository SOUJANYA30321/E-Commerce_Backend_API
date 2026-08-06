package com.dcl.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.shop.model.Order;
import com.dcl.shop.service.OrderService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	private final OrderService service;

	
	/* ********************************** PLACE ORDER ********************************** */
	@PostMapping("/orders/place/{userId}")
	public ResponseEntity<ResponseStructure<Order>> placeOrder(@PathVariable  int userId){
		Order orderPlaced = service.placeOrder(userId);
		
		ResponseStructure<Order> rs = new ResponseStructure<Order>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Order placed successfully.");
		rs.setData(orderPlaced);
		
		return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.CREATED);
	}
	
	
	/* VIEW ORDER */
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<ResponseStructure<Order>> viewOrder(@PathVariable int orderId){
		Order orderViewed = service.viewOrder(orderId);
		
		ResponseStructure<Order> rs = new ResponseStructure<Order>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Order placed successfully.");
		rs.setData(orderViewed);
		
		return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.OK);
	}
	
	
	/*  DISPLAY ORDER HISTORY BY USER ID  */
	@GetMapping("/orders/user/{userId}")
	public ResponseEntity<ResponseStructure<List<Order>>> displayOrderHistoryByUserId(@PathVariable int userId) {
		List<Order> orderHistory = service.displayOrderHistoryByUserId(userId);
		
		ResponseStructure<List<Order>> rs = new ResponseStructure<List<Order>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Order history displayed successfully.");
		rs.setData(orderHistory);
		
		return new ResponseEntity<ResponseStructure<List<Order>>>(rs, HttpStatus.OK);
	}
	
	
	/*  CANCEL ORDER  */
	@PutMapping("/orders/cancel/{orderId}")
	public ResponseEntity<ResponseStructure<Order>> cancelOrder(@PathVariable int orderId) {
		Order cancelledOrder = service.cancelOrder(orderId);
		
		ResponseStructure<Order> rs = new ResponseStructure<Order>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Order cancelled successfully.");
		rs.setData(cancelledOrder);
		
		return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.OK);
	}
	
	
	/*  TOTAL PRICE CALCULATION  */
	@GetMapping("/orders/{orderId}/total")
	public ResponseEntity<ResponseStructure<Double>> totalPriceCalculation(@PathVariable int orderId) {
		double order = service.totalPriceCalculation(orderId);
		
		ResponseStructure<Double> rs = new ResponseStructure<Double>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Total Price of the order calculated successfully.");
		rs.setData(order);
		
		return new ResponseEntity<ResponseStructure<Double>>(rs, HttpStatus.OK);
	}
	
	
	/* DISPLAY ALL ORDERS  */
	@GetMapping("/orders")
	public ResponseEntity<ResponseStructure<List<Order>>> displayAllOrders(){
		List<Order> ordersList = service.displayAllOrders();
		
		ResponseStructure<List<Order>> rs = new ResponseStructure<List<Order>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All the orders displayed successfully.");
		rs.setData(ordersList);
		
		return new ResponseEntity<ResponseStructure<List<Order>>>(rs, HttpStatus.OK);
	}
	
	
	/*  DELETE ORDER  */
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<ResponseStructure<Order>> deleteOrder(@PathVariable int orderId){
		Order deletedOrder = service.deleteOrder(orderId);
		
		ResponseStructure<Order> rs = new ResponseStructure<Order>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Order deleted successfully.");
		rs.setData(deletedOrder);
		
		return new ResponseEntity<ResponseStructure<Order>>(rs, HttpStatus.OK);
	}
	
}
