package com.dcl.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByProductId(int productId);

	List<Product> findByProductNameContainingIgnoreCase(String productName);

	Optional<Product> findByProductNameIgnoreCase(String productName);

	List<Product> findByBrandIgnoreCase(String brand);
	
	List<Product> findByCategoryCategoryId(int categoryId);

	List<Product> findByCategoryCategoryNameIgnoreCase(String categoryName);

	List<Product> findByPriceBetween(double pricemin, double pricemax);

	List<Product> findAllByOrderByPriceAsc();

	List<Product> findAllByOrderByPriceDesc();

	List<Product> findAllByOrderByProductNameAsc();

	boolean existsByProductNameIgnoreCase(String productName);

}
