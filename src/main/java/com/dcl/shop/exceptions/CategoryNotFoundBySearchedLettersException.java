package com.dcl.shop.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryNotFoundBySearchedLettersException extends RuntimeException {
	private String message;
}
