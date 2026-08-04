package com.dcl.shop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.shop.model.Product;
import com.dcl.shop.service.ProductService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product) {
		Product addedProduct = service.addProduct(product);
		
		ResponseStructure<Product> rs = new ResponseStructure<Product>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Product object created successfully.");
		rs.setData(addedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> displayAllProducts() {
		List<Product> productsList = service.displayAllProducts();
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All products displayed successfully");
		rs.setData(productsList);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> displayProductById(@PathVariable("id") int productId) {
		Product product = service.displayProductById(productId);
		
		ResponseStructure<Product> rs = new ResponseStructure<Product>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product displayed successfully for the requested id: " +productId);
		rs.setData(product);
		
		return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.OK);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> updateProductById(@PathVariable("id") int productId, @RequestBody Product updatedProduct) {
		Product modifiedProduct = service.updateProductById(productId, updatedProduct);
		
		ResponseStructure<Product> rs = new ResponseStructure<Product>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product updated successfully for the requested id: " +productId);
		rs.setData(modifiedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.OK);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteProductById(@PathVariable("id") int productId) {
		Product deletedProduct = service.deleteProductById(productId);
		
		ResponseStructure<Product> rs = new ResponseStructure<Product>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product deleted successfully for the requested id: " +productId);
		rs.setData(deletedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/name/{name}")
	public ResponseEntity<ResponseStructure<Product>> findProductByName(@PathVariable("name") String productName) {
		Product foundProductByName = service.findProductByName(productName);
		
		ResponseStructure<Product> rs = new ResponseStructure<Product>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product found successfully for the requested name: "+productName);
		rs.setData(foundProductByName);
		
		return new ResponseEntity<ResponseStructure<Product>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/search/{keyword}")
	public ResponseEntity<ResponseStructure<List<Product>>> searchProductsByKeyword(@PathVariable("keyword") String productName){
		List<Product> foundProduct = service.searchProductsByKeyword(productName);
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product found successfully for the requested keyword: "+productName);
		rs.setData(foundProduct);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByBrand(@PathVariable("brand") String brand) {
		List<Product> foundProductsByBrand = service.findProductsByBrand(brand);
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Product found successfully for the requested brand: " + brand);
		rs.setData(foundProductsByBrand);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/category/{categoryId}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByCategory(@PathVariable int categoryId) {
		List<Product> foundProductsByCategory = service.findProductsByCategory(categoryId);
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products found successfully for the requested category: " + categoryId);
		rs.setData(foundProductsByCategory);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/category/name/{categoryName}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByCategoryName(@PathVariable String categoryName) {
		List<Product> foundProductsByCategoryName = service.findProductsByCategoryName(categoryName);
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products found successfully for the requested category: " + categoryName);
		rs.setData(foundProductsByCategoryName);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/price/{minPrice}/{maxPrice}")
	public ResponseEntity<ResponseStructure<List<Product>>> findProductsByPriceRange(@PathVariable("minPrice") double pricemin, @PathVariable("maxPrice") double pricemax) {
		List<Product> foundProductsByPriceRange = service.findProductsByPriceRange(pricemin, pricemax);
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products found successfully for the requested price range: "+pricemin+ " to " +pricemax);
		rs.setData(foundProductsByPriceRange);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/sort/price/asc")
	public ResponseEntity<ResponseStructure<List<Product>>> sortProductsByPriceAscending() {
		List<Product> sortedProductsByPriceAsc = service.sortProductsByPriceAscending();
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products sorted by price in ascending order");
		rs.setData(sortedProductsByPriceAsc);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/sort/price/desc")
	public ResponseEntity<ResponseStructure<List<Product>>> sortProductsByPriceDescending() {
		List<Product> sortedProductsByPriceDesc = service.sortProductsByPriceDescending();
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products sorted by price in descending order");
		rs.setData(sortedProductsByPriceDesc);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/sort/name/asc")
	public ResponseEntity<ResponseStructure<List<Product>>> sortProductsByNameAsc() {
		List<Product> sortedProductsByNameAsc = service.sortProductsByNameAsc();
		
		ResponseStructure<List<Product>> rs = new ResponseStructure<List<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products sorted by name in alphabetical order");
		rs.setData(sortedProductsByNameAsc);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/exists/{name}")
	public ResponseEntity<ResponseStructure<Boolean>> findProductExists(@PathVariable("name") String productName) {
		boolean productExists = service.findProductExists(productName);
		
		ResponseStructure<Boolean> rs = new ResponseStructure<Boolean>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage(productExists? "Product exists" : "Product does not exists");
		rs.setData(productExists);
		
		return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/products/page")
	public ResponseEntity<ResponseStructure<Page<Product>>> displayProductsWithPagination(@RequestParam int page, @RequestParam int size) {
		Page<Product> product = service.displayProductsWithPagination(page, size);
		
		ResponseStructure<Page<Product>> rs = new ResponseStructure<Page<Product>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Products fetched successfully.");
		rs.setData(product);
		
		return new ResponseEntity<ResponseStructure<Page<Product>>>(rs, HttpStatus.OK);
	}
}
