package com.dcl.shop.model;

import java.time.LocalDateTime;
import java.util.List;

import com.dcl.shop.enums.OrderStatus;
import com.dcl.shop.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	@Column(nullable = false)
	private LocalDateTime order_date;
	
	@Column(nullable = false)
	private double total_amount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus order_status;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus payment_status;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
}
