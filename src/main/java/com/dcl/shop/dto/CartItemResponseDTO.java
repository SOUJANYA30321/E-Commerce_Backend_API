package com.dcl.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {

    private int cartItemId;

    private int productId;

    private String productName;

    private String brand;

    private double price;

    private int quantity;

    private double subTotal;
}
