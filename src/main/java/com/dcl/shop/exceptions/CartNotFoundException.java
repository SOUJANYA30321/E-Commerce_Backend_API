package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartNotFoundException extends RuntimeException {
	private String message;
}
