package com.dcl.shop.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dcl.shop.exceptions.CategoryNotFoundByIdException;
import com.dcl.shop.exceptions.CategoryNotFoundByNameException;
import com.dcl.shop.exceptions.CategoryNotFoundBySearchedLettersException;
import com.dcl.shop.exceptions.CategoriesNotFoundException;
import com.dcl.shop.model.Category;
import com.dcl.shop.repository.CategoryRepository;
import com.dcl.shop.service.CategoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository repository;

	
	/*  ADD CATEGORY  */
	@Override
	public Category addCategory(Category category) {
		return repository.save(category);
	}

	
	/* DISPLAY ALL CATEGORIES  */
	@Override
	public List<Category> displayAllCategories() {
		List<Category> list = repository.findAll();

		if (list.isEmpty()) {
			throw new CategoriesNotFoundException("NO CATEGORIES FOUND");
		} else {
			return list;
		}
	}

	
	/* DISPLAY CATEGORY BY CATEGORY ID  */
	@Override
	public Category displayCatgoryById(int category_id) {
		Optional<Category> optional = repository.findById(category_id);

		if (optional.isEmpty()) {
			throw new CategoryNotFoundByIdException("NO CATEGORY FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  UPDATE CATEGORY BY CATEGORY ID  */
	@Override
	public Category updateCategoryById(int category_id, Category updatedCategory) {
		Optional<Category> optional = repository.findById(category_id);

		if (optional.isEmpty()) {
			throw new CategoryNotFoundByIdException("NO CATEGORY FOUND");
		} else {
			Category existingCategory = optional.get();
			updatedCategory.setCategoryId(existingCategory.getCategoryId());
			return repository.save(updatedCategory);
		}
	}

	
	/*  DELETE CATEGORY BY CATEGORY ID  */
	@Override
	public Category deleteCategoryById(int category_id) {
		Optional<Category> optional = repository.findById(category_id);

		if (optional.isEmpty()) {
			throw new CategoryNotFoundByIdException("NO CATEGORY FOUND");
		} else {
			Category existingCategory = optional.get();
			repository.delete(existingCategory);
			return existingCategory;
		}
	}

	
	/*  FIND CATEGORY BY CATEGORY NAME  */
	@Override
	public Category findCategoryByName(String category_name) {
		Optional<Category> optional = repository.findByCategoryName(category_name);
		
		if(optional.isEmpty()) {
			throw new CategoryNotFoundByNameException("NO CATEGORY FOUND"); 
		} else {
			return optional.get();
		}
	}

	
	/*  SEARCH CATEGORY BY KEYWORD  */
	@Override
	public List<Category> searchCategoryByKeyword(String categoryName) {
		List<Category> list = repository.findByCategoryNameContaining(categoryName);
		
		if(list.isEmpty()) {
			throw new CategoryNotFoundBySearchedLettersException("NO CATEGORY FOUND");
		} else {
			return list;
		}
	}

	
	/*  CHECK CATEGORY EXISTS BY CATEGORY NAME  */
	@Override
	public boolean findCategoryExists(String categoryName) {
	    return repository.existsByCategoryName(categoryName);
	}
	
	
}
