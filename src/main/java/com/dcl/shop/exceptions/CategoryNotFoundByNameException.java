package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CategoryNotFoundByNameException extends RuntimeException {
	private String message;
}
