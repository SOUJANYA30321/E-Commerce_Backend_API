package com.dcl.shop.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dcl.shop.exceptions.CategoryNotFoundByIdException;
import com.dcl.shop.exceptions.CategoryNotFoundByNameException;
import com.dcl.shop.exceptions.CategoryNotFoundBySearchedLettersException;
import com.dcl.shop.exceptions.ProductNotFoundByBrandException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryNameException;
import com.dcl.shop.exceptions.ProductNotFoundByIdException;
import com.dcl.shop.exceptions.ProductNotFoundByNameException;
import com.dcl.shop.exceptions.ProductNotFoundByPriceRangeException;
import com.dcl.shop.exceptions.ProductsNotFoundException;
import com.dcl.shop.exceptions.CategoriesNotFoundException;
import com.dcl.shop.util.ErrorStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CategoriesNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoriesNotFoundException(
			CategoriesNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No categories are present in the database");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CategoryNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundByIdException(
			CategoryNotFoundByIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Category for the requested Id is not available in the database");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CategoryNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundByNameException(
			CategoryNotFoundByNameException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Category not found in the database for the requested name.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CategoryNotFoundBySearchedLettersException.class)
	public ResponseEntity<ErrorStructure<String>> handleCategoryNotFoundBySearchedLettersException(
			CategoryNotFoundBySearchedLettersException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Unable to find the category for your search request");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductsNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductsNotFoundException(ProductsNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No products are available in the database");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByIdException(ProductNotFoundByIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested Id does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByNameException(ProductNotFoundByNameException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested name does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundByBrandException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByBrandException(ProductNotFoundByBrandException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested brand does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundByCategoryException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByCategoryException(ProductNotFoundByCategoryException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested category does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundByCategoryNameException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByCategoryNameException(ProductNotFoundByCategoryNameException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested category does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductNotFoundByPriceRangeException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByPriceRangeException(ProductNotFoundByPriceRangeException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Product with the requested price range does not exist in the database.");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
}
