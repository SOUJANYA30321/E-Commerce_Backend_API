package com.dcl.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Optional<Category> findByCategoryName(String category_name);

	List<Category> findByCategoryNameContaining(String categoryName);

	boolean existsByCategoryName(String categoryName);



}
