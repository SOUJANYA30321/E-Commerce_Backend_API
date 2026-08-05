package com.dcl.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.shop.dto.CartResponseDTO;
import com.dcl.shop.model.Cart;
import com.dcl.shop.model.CartItem;
import com.dcl.shop.service.CartService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class CartController {
	private final CartService service;
	
	@PostMapping("/carts")
	public ResponseEntity<ResponseStructure<Cart>> addCart(@RequestBody Cart cart) {
		Cart addedCart = service.addCart(cart);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Cart Object created successfully.");
		rs.setData(addedCart);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.CREATED);
	}
	
	@GetMapping("/carts/user/{userId}")
	public ResponseEntity<ResponseStructure<Cart>> getCartByUserId(@PathVariable int userId) {
		Cart userCart = service.getCartByUserId(userId);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Cart fetched successfully.");
		rs.setData(userCart);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.OK);
	}
	
	
	/* ************************************* IMPORTANT ************************************* */
	/* ADD PRODUCT TO CART */
	@PostMapping("/carts/{cartId}/products/{productId}")
	public ResponseEntity<ResponseStructure<Cart>> addProductToCart(@PathVariable int cartId, @PathVariable int productId, @RequestParam int quantity) {
		Cart productAddedToCart = service.addProductToCart(cartId, productId, quantity);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product added to cart sucessfully.");
		rs.setData(productAddedToCart);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.OK);
	}
	
	@PutMapping("/carts/{cartId}/products/{productId}")
	public ResponseEntity<ResponseStructure<Cart>> updateQuantity(@PathVariable int cartId, @PathVariable int productId, @RequestBody CartItem updatedQuantity) {
		Cart updatedCart = service.updateQuantity(cartId, productId, updatedQuantity);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product quantity modified sucessfully.");
		rs.setData(updatedCart);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.OK);
	}
	
	@DeleteMapping("/carts/{cartId}/products/{productId}") 
	public ResponseEntity<ResponseStructure<Cart>> removeProduct(@PathVariable int cartId, @PathVariable int productId){
		Cart productRemoved = service.removeProduct(cartId, productId);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product deleted sucessfully for your requested cart: " +cartId);
		rs.setData(productRemoved);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/carts/{cartId}")
	public ResponseEntity<ResponseStructure<CartResponseDTO>> displayCartByCartId(@PathVariable int cartId) {
		CartResponseDTO cartFound = service.displayCartByCartId(cartId);
		
		ResponseStructure<CartResponseDTO> rs = new ResponseStructure<CartResponseDTO>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Cart found for your requested cartId: " +cartId);
		rs.setData(cartFound);
		
		return new ResponseEntity<ResponseStructure<CartResponseDTO>>(rs, HttpStatus.OK);
	}
	
	@DeleteMapping("/carts/{cartId}")
	public ResponseEntity<ResponseStructure<Cart>> clearCart(@PathVariable int cartId) {
		Cart deletedCart = service.clearCart(cartId);
		
		ResponseStructure<Cart> rs = new ResponseStructure<Cart>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Cart cleared sucessfully for your requested cartId: " +cartId);
		rs.setData(deletedCart);
		
		return new ResponseEntity<ResponseStructure<Cart>>(rs, HttpStatus.OK);
	}
}
