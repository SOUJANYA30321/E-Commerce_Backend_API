package com.dcl.shop.service;

import java.util.List;

import com.dcl.shop.model.Category;

public interface CategoryService {

	Category addCategory(Category category);

	List<Category> displayAllCategories();

	Category displayCatgoryById(int category_id);

	Category updateCategoryById(int category_id, Category updatedCategory);

	Category deleteCategoryById(int category_id);

	Category findCategoryByName(String category_name);

	List<Category> searchCategoryByKeyword(String categoryName);

	boolean findCategoryExists(String categoryName);

}
