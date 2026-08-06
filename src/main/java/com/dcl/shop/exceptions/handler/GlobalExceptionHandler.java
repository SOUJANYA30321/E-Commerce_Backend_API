package com.dcl.shop.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dcl.shop.exceptions.CategoryNotFoundByIdException;
import com.dcl.shop.exceptions.CategoryNotFoundByNameException;
import com.dcl.shop.exceptions.CategoryNotFoundBySearchedLettersException;
import com.dcl.shop.exceptions.OrderHistoryNotFoundException;
import com.dcl.shop.exceptions.OrderNotFoundByOrderIdException;
import com.dcl.shop.exceptions.OrdersNotFoundException;
import com.dcl.shop.exceptions.ProductNotFoundByBrandException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryNameException;
import com.dcl.shop.exceptions.ProductNotFoundByProductIdException;
import com.dcl.shop.exceptions.ProductNotFoundByNameException;
import com.dcl.shop.exceptions.ProductNotFoundByPriceRangeException;
import com.dcl.shop.exceptions.ProductsNotFoundException;
import com.dcl.shop.exceptions.UserNotFoundByUserIdException;
import com.dcl.shop.exceptions.UserNotFoundEmailException;
import com.dcl.shop.exceptions.UserNotFoundNameException;
import com.dcl.shop.exceptions.UsersNotFoundException;
import com.dcl.shop.exceptions.CartItemNotFoundException;
import com.dcl.shop.exceptions.CartNotFoundByCartIdException;
import com.dcl.shop.exceptions.CartNotFoundByUserIdException;
import com.dcl.shop.exceptions.CartNotFoundException;
import com.dcl.shop.exceptions.CategoriesNotFoundException;
import com.dcl.shop.util.ErrorStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/* ************************************ CATEGORY EXCEPTIONS ************************************ */
	
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
	

	/* ************************************ PRRODUCT EXCEPTIONS ************************************ */
	
	
	@ExceptionHandler(ProductsNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductsNotFoundException(ProductsNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();

		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No products are available in the database");
		es.setError(exception.getMessage());

		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ProductNotFoundByProductIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleProductNotFoundByProductIdException(ProductNotFoundByProductIdException exception) {
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
	
	
	/* ************************************** USER EXCEPTIONS ************************************** */
	
	
	@ExceptionHandler(UsersNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleUsersNotFoundException(UsersNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No user exists in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundByUserIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundByUserIdException(UserNotFoundByUserIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No user exists in the database for the requested user id.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundEmailException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundEmailException(UserNotFoundEmailException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No user exists in the database for the requested email.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundNameException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundNameException(UserNotFoundNameException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No user exists in the database for the requested name.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	
	/* ************************************ CART EXCEPTIONS ************************************ */
	
	
	@ExceptionHandler(CartNotFoundByUserIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleCartNotFoundByUserIdException(CartNotFoundByUserIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No cart exists in the database for the requested user Id.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleCartNotFoundException(CartNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No cart exists in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleCartItemNotFoundException(CartItemNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Cart Item does not exist in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CartNotFoundByCartIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleCartNotFoundByCartIdException(CartNotFoundByCartIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Cart with the requested cart Id does not exist in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	
	
	/* ************************************ ORDER EXCEPTIONS ************************************ */
	
	
	@ExceptionHandler(OrderNotFoundByOrderIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleOrderNotFoundByOrderIdException(OrderNotFoundByOrderIdException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Failed to display the order since the requested order does not exist in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderHistoryNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleOrderHistoryNotFoundException(OrderHistoryNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("Order History does not exist for a requested userId.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrdersNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleOrdersNotFoundException(OrdersNotFoundException exception) {
		ErrorStructure<String> es = new ErrorStructure<String>();
		
		es.setErrorCode(HttpStatus.NOT_FOUND.value());
		es.setErrorMessage("No orders are present in the database.");
		es.setError(exception.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(es, HttpStatus.NOT_FOUND);
	}
}
