package com.dcl.shop.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dcl.shop.dto.CartItemResponseDTO;
import com.dcl.shop.dto.CartResponseDTO;
import com.dcl.shop.exceptions.CartItemNotFoundException;
import com.dcl.shop.exceptions.CartNotFoundByCartIdException;
import com.dcl.shop.exceptions.CartNotFoundByUserIdException;
import com.dcl.shop.exceptions.ProductNotFoundByProductIdException;
import com.dcl.shop.model.Cart;
import com.dcl.shop.model.CartItem;
import com.dcl.shop.model.Product;
import com.dcl.shop.repository.CartItemRepository;
import com.dcl.shop.repository.CartRepository;
import com.dcl.shop.repository.ProductRepository;
import com.dcl.shop.service.CartService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;

	@Override
	public Cart addCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartByUserId(int userId) {
		Optional<Cart> optional = cartRepository.findByUser_UserId(userId);
		
		if(optional.isEmpty()) {
			throw new CartNotFoundByUserIdException("NO CART FOUND");
		} else {
			return optional.get();
		}
	}

	/* ************************************* IMPORTANT ************************************* */
	/* ADD PRODUCT TO CART */
	@Override
	public Cart addProductToCart(int cartId, int productId, int quantity) {

	    Optional<Cart> optionalCart = cartRepository.findById(cartId);
	    if (optionalCart.isEmpty()) {
	        throw new CartNotFoundByCartIdException("NO CART FOUND");
	    }
	    Cart cart = optionalCart.get();

	    Optional<Product> optionalProduct = productRepository.findByProductId(productId);
	    if (optionalProduct.isEmpty()) {
	        throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
	    }
	    Product product = optionalProduct.get();

	    
	    if (quantity <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than zero.");
	    }

	    Optional<CartItem> optionalCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cartId, productId);

	    if (optionalCartItem.isPresent()) {
	        CartItem existingCartItem = optionalCartItem.get();
	        int newQuantity = existingCartItem.getQuantity() + quantity;

	        if (newQuantity > product.getStock()) {
	            throw new IllegalArgumentException("Insufficient stock available.");
	        }

	        existingCartItem.setQuantity(newQuantity);
	        cartItemRepository.save(existingCartItem);

	    } else {
	        if (quantity > product.getStock()) {
	            throw new IllegalArgumentException("Insufficient stock available.");
	        }

	        CartItem cartItem = new CartItem();
	        cartItem.setCart(cart);
	        cartItem.setProduct(product);
	        cartItem.setQuantity(quantity);
	        cartItem.setPrice(product.getPrice());

	        cartItemRepository.save(cartItem);
	    }

	    return cart;
	}

	
	/* UPDATE QUANTITY */
	@Override
	public Cart updateQuantity(int cartId, int productId, CartItem updatedQuantity) {

	    Optional<Cart> optionalCart = cartRepository.findById(cartId);
	    if (optionalCart.isEmpty()) {
	        throw new CartNotFoundByCartIdException("NO CART FOUND");
	    }
	    Cart cart = optionalCart.get();

	    Optional<Product> optionalProduct = productRepository.findByProductId(productId);
	    if (optionalProduct.isEmpty()) {
	        throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
	    }
	    Product product = optionalProduct.get();

	    Optional<CartItem> optionalCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cartId, productId);

	    if (optionalCartItem.isEmpty()) {
	        throw new CartItemNotFoundException("NO CART ITEM FOUND");
	    }

	    if (updatedQuantity.getQuantity() <= 0) {
	        throw new IllegalArgumentException("Quantity must be greater than zero.");
	    }

	    if (updatedQuantity.getQuantity() > product.getStock()) {
	        throw new IllegalArgumentException("Insufficient stock available.");
	    }

	    CartItem existingCartItem = optionalCartItem.get();
	    existingCartItem.setQuantity(updatedQuantity.getQuantity());

	    cartItemRepository.save(existingCartItem);

	    return cart;
	}

	
	/* DELETE PRODUCT */
	@Override
	public Cart removeProduct(int cartId, int productId) {
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if (optionalCart.isEmpty()) {
			throw new CartNotFoundByCartIdException("NO CART FOUND");
		}
		Cart cart = optionalCart.get();

		
		Optional<Product> optionalProduct = productRepository.findByProductId(productId);
		if (optionalProduct.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		}
		Product product = optionalProduct.get();
		
		
		Optional<CartItem> optionalCartItem = cartItemRepository.findByCartCartIdAndProductProductId(cartId, productId);
		
		if(optionalCartItem.isEmpty()) {
			throw new CartItemNotFoundException("NO CART ITEM FOUND");
		} else {
			CartItem existingCartItem = optionalCartItem.get();
			cartItemRepository.delete(existingCartItem);
		}
		return cart;
	}

	
	
	/* ************************************* IMPORTANT ************************************* */
	/* VIEW CART */
	@Override
	public CartResponseDTO displayCartByCartId(int cartId) {

	    Optional<Cart> optional = cartRepository.findByCartId(cartId);

	    if(optional.isEmpty()) {
	        throw new CartNotFoundByCartIdException("NO CART FOUND");
	    }
	    Cart cart = optional.get();
	    CartResponseDTO dto = new CartResponseDTO();

	    dto.setCartId(cart.getCartId());
	    dto.setUserId(cart.getUser().getUserId());
	    dto.setUserName(cart.getUser().getName());

	    
	    List<CartItemResponseDTO> items = new ArrayList<>();
	    double totalAmount = 0;

	    for(CartItem cartItem : cart.getCartItems()) {

	        CartItemResponseDTO itemDTO = new CartItemResponseDTO();

	        itemDTO.setCartItemId(cartItem.getCartItemId());
	        itemDTO.setProductId(cartItem.getProduct().getProductId());
	        itemDTO.setProductName(cartItem.getProduct().getProductName());
	        itemDTO.setBrand(cartItem.getProduct().getBrand());

	        itemDTO.setQuantity(cartItem.getQuantity());
	        itemDTO.setPrice(cartItem.getPrice());

	        double subTotal = cartItem.getPrice() * cartItem.getQuantity();
	        itemDTO.setSubTotal(subTotal);
	        totalAmount = totalAmount + subTotal;
	        items.add(itemDTO);
	    }
	    dto.setCartItems(items);
	    dto.setTotalAmount(totalAmount);

	    return dto;
	}

	@Override
	public Cart clearCart(int cartId) {
		Optional<Cart> optional = cartRepository.findById(cartId);
		
		if(optional.isEmpty()) {
			throw new CartNotFoundByCartIdException("NO CART FOUND");
		} else {
			Cart existingCart = optional.get();
			existingCart.getCartItems().clear();
			return cartRepository.save(existingCart);
		}
	}

}
