package com.dcl.shop.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dcl.shop.exceptions.CategoryNotFoundByIdException;
import com.dcl.shop.exceptions.CategoryNotFoundByNameException;
import com.dcl.shop.exceptions.CategoryNotFoundBySearchedLettersException;
import com.dcl.shop.exceptions.CategoriesNotFoundException;
import com.dcl.shop.util.ErrorStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoriesNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoriesNotFoundException(CategoriesNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No categories are present in the database");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CategoryNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundByIdException(CategoryNotFoundByIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Category for the requested Id is not available in the database");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundByNameException(CategoryNotFoundByNameException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Category not found in the database for the requested name.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CategoryNotFoundBySearchedLettersException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundBySearchedLettersException(CategoryNotFoundBySearchedLettersException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Unable to find the category for your search request");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

}
