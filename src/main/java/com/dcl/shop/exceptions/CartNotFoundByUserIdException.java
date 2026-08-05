package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartNotFoundByUserIdException extends RuntimeException {
	private String message;
}
