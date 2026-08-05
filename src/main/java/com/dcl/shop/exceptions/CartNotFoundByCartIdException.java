package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartNotFoundByCartIdException extends RuntimeException {
	private String message;
}
