package com.dcl.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.shop.model.Category;
import com.dcl.shop.service.CategoryService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CategoryController {
	
	private final CategoryService service;
	
	@PostMapping("/categories")
	public ResponseEntity<ResponseStructure<Category>> addCategory(@RequestBody Category category) {
		Category savedCategory = service.addCategory(category);
		
		ResponseStructure<Category> rs = new ResponseStructure<Category>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Category object created successfully.");
		rs.setData(savedCategory);
		
		return new ResponseEntity<ResponseStructure<Category>>(rs, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/categories") 
	public ResponseEntity<ResponseStructure<List<Category>>> displayAllCategories() {
		List<Category> allCategories = service.displayAllCategories();
		
		ResponseStructure<List<Category>> rs = new ResponseStructure<List<Category>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("ALL the categories in the database displayed successfully.");
		rs.setData(allCategories);
		
		return new ResponseEntity<ResponseStructure<List<Category>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<ResponseStructure<Category>> displayCategoryById(@PathVariable("id") int category_id) {
		Category categoryFetchedById = service.displayCatgoryById(category_id);
		
		ResponseStructure<Category> rs = new ResponseStructure<Category>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Category found for the requested id: "+category_id);
		rs.setData(categoryFetchedById);
		
		return new ResponseEntity<ResponseStructure<Category>>(rs, HttpStatus.OK);
		
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<ResponseStructure<Category>> updateCategoryById(@PathVariable("id") int category_id, @RequestBody Category updatedCategory) {
		Category modifiedCategory = service.updateCategoryById(category_id, updatedCategory);
		
		ResponseStructure<Category> rs = new ResponseStructure<Category>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Category modified successfully for the requested id: "+category_id);
		rs.setData(modifiedCategory);
		
		return new ResponseEntity<ResponseStructure<Category>>(rs, HttpStatus.OK);
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<ResponseStructure<Category>> deleteCategoryById(@PathVariable("id") int category_id) {
		Category deletedCategory = service.deleteCategoryById(category_id);
		
		ResponseStructure<Category> rs = new ResponseStructure<Category>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Category deleted successfully for the requested id: "+category_id);
		rs.setData(deletedCategory);
		
		return new ResponseEntity<ResponseStructure<Category>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/categories/name/{name}")
	public ResponseEntity<ResponseStructure<Category>> findCategoryByName(@PathVariable("name") String category_name) {
		Category categoryName = service.findCategoryByName(category_name);
		
		ResponseStructure<Category> rs = new ResponseStructure<Category>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Category found by name successfully.");
		rs.setData(categoryName);
		
		return new ResponseEntity<ResponseStructure<Category>>(rs, HttpStatus.OK);
	}

	@GetMapping("/categories/search/{name}")
	public ResponseEntity<ResponseStructure<List<Category>>> searchCategoryByKeyword(@PathVariable("name") String categoryName) {
		List<Category> matchingCategory = service.searchCategoryByKeyword(categoryName);
		
		ResponseStructure<List<Category>> rs = new ResponseStructure<List<Category>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Matching categories found successfully.");
		rs.setData(matchingCategory);
		
		return new ResponseEntity<ResponseStructure<List<Category>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/categories/exists/{name}")
	public ResponseEntity<ResponseStructure<Boolean>> findCategoryExists(@PathVariable("name") String categoryName) {
		boolean existingCategory = service.findCategoryExists(categoryName);
		
		ResponseStructure<Boolean> rs = new ResponseStructure<Boolean>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage(existingCategory? "Category exists." : "Category does not exist.");
		rs.setData(existingCategory);
		
		return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.OK);
	}
}
