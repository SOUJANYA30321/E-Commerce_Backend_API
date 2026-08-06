package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderHistoryNotFoundException extends RuntimeException {
	private String message;
}
