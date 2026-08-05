package com.dcl.shop.service;

import com.dcl.shop.dto.CartResponseDTO;
import com.dcl.shop.model.Cart;
import com.dcl.shop.model.CartItem;

public interface CartService {

	Cart addCart(Cart cart);

	Cart getCartByUserId(int userId);

	Cart addProductToCart(int cartId, int productId, int quantity);

	Cart updateQuantity(int cartId, int productId, CartItem updatedQuantity);

	Cart removeProduct(int cartId, int productId);

	CartResponseDTO displayCartByCartId(int cartId);

	Cart clearCart(int cartId);

}
