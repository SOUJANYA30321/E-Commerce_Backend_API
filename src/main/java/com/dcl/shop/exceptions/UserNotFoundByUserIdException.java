package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundByUserIdException extends RuntimeException {
	private String message;
}
