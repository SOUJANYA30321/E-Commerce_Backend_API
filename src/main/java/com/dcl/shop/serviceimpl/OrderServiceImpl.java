package com.dcl.shop.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dcl.shop.enums.OrderStatus;
import com.dcl.shop.enums.PaymentStatus;
import com.dcl.shop.exceptions.CartNotFoundByCartIdException;
import com.dcl.shop.exceptions.OrderHistoryNotFoundException;
import com.dcl.shop.exceptions.OrderNotFoundByOrderIdException;
import com.dcl.shop.exceptions.OrdersNotFoundException;
import com.dcl.shop.exceptions.UserNotFoundByUserIdException;
import com.dcl.shop.model.Cart;
import com.dcl.shop.model.CartItem;
import com.dcl.shop.model.Order;
import com.dcl.shop.model.OrderItem;
import com.dcl.shop.model.Product;
import com.dcl.shop.model.User;
import com.dcl.shop.repository.CartItemRepository;
import com.dcl.shop.repository.CartRepository;
import com.dcl.shop.repository.OrderItemRepository;
import com.dcl.shop.repository.OrderRepository;
import com.dcl.shop.repository.ProductRepository;
import com.dcl.shop.repository.UserRepository;
import com.dcl.shop.service.OrderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;
	private final OrderRepository orderRepository;

	
	/* *********************************** PLACE ORDER  *********************************** */
	@Override
	public Order placeOrder(int userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		}
		User user = optionalUser.get();

		Optional<Cart> optionalUserCart = cartRepository.findByUser_UserId(userId);
		if (optionalUserCart.isEmpty()) {
			throw new CartNotFoundByCartIdException("NO CART FOUND");
		}
		Cart cart = optionalUserCart.get();

		if (cart.getCartItems().isEmpty()) {
			throw new IllegalArgumentException("CART IS EMPTY");
		}

		Order order = new Order();
		order.setUser(user);
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.PLACED);
		order.setPaymentStatus(PaymentStatus.PENDING);

		List<OrderItem> orderItems = new ArrayList<>();
		double totalAmount = 0.0;

		for (CartItem cartItem : cart.getCartItems()) {

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getProduct().getPrice());

			Product product = cartItem.getProduct();

			if (cartItem.getQuantity() > product.getStock()) {
				throw new IllegalArgumentException("Quantity requested is greater than available stock.");
			}

			product.setStock(product.getStock() - cartItem.getQuantity());
			productRepository.save(product);

			double subTotal = orderItem.getPrice() * orderItem.getQuantity();
			totalAmount = totalAmount + subTotal;

			orderItems.add(orderItem);
		}

		order.setOrderItems(orderItems);
		order.setTotalAmount(totalAmount);

		Order savedOrder = orderRepository.save(order);

		cart.getCartItems().clear();
		cartRepository.save(cart);

		return savedOrder;
	}

	
	/* VIEW ORDER */
	@Override
	public Order viewOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);

		if (optionalOrder.isEmpty()) {
			throw new OrderNotFoundByOrderIdException("NO ORDER FOUND");
		} else {
			return optionalOrder.get();
		}
	}

	
	/*  DISPLAY ORDER HISTORY BY USER ID  */
	@Override
	public List<Order> displayOrderHistoryByUserId(int userId) {
		List<Order> optionalOrderHistory = orderRepository.findByUser_UserId(userId);

		if (optionalOrderHistory.isEmpty()) {
			throw new OrderHistoryNotFoundException("NO ORDER HISTORY");
		} else {
			return optionalOrderHistory;
		}
	}

	
	/*  CANCEL ORDER  */
	@Override
	public Order cancelOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);

		if (optionalOrder.isEmpty()) {
			throw new OrderNotFoundByOrderIdException("NO ORDER FOUND");
		}
		Order order = optionalOrder.get();

		if (order.getOrderStatus() == OrderStatus.CANCELLED) {
			return order;
		}

		for (OrderItem orderItem : order.getOrderItems()) {
			Product product = orderItem.getProduct();

			product.setStock(product.getStock() + orderItem.getQuantity());
			productRepository.save(product);
		}

		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}

	
	/*  TOTAL PRICE CALCULATION  */
	@Override
	public double totalPriceCalculation(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);

		if (optionalOrder.isEmpty()) {
			throw new OrderNotFoundByOrderIdException("NO ORDER FOUND");
		}
		Order order = optionalOrder.get();
		return order.getTotalAmount();
	}

	
	/*  DISPLAY ALL ORDERS  */
	@Override
	public List<Order> displayAllOrders() {
		List<Order> ordersList = orderRepository.findAll();
		
		if(ordersList.isEmpty()) {
			throw new OrdersNotFoundException("NO ORDERS FOUND");
		}
		else {
			return ordersList;
		}
	}

	
	/*  DELETE ORDER  */
	@Override
	public Order deleteOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
		
		if(optionalOrder.isEmpty()) {
			throw new OrderNotFoundByOrderIdException("NO ORDER FOUND");
		} else {
			Order deletedOrder = optionalOrder.get();
			orderRepository.delete(deletedOrder);
			return deletedOrder;
		}
	}

}
