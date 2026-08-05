package com.dcl.shop.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDTO {

    private int cartId;

    private int userId;

    private String userName;

    private List<CartItemResponseDTO> cartItems;

    private double totalAmount;
}