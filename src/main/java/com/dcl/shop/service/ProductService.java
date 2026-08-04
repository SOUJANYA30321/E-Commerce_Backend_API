package com.dcl.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dcl.shop.model.Product;

public interface ProductService {

	Product addProduct(Product product);

	List<Product> displayAllProducts();

	Product displayProductById(int productId);

	Product updateProductById(int productId, Product updatedProduct);

	Product deleteProductById(int productId);

	Product findProductByName(String productName);

	List<Product> searchProductsByKeyword(String productName);

	List<Product> findProductsByBrand(String brand);

	List<Product> findProductsByCategory(int categoryId);

	List<Product> findProductsByCategoryName(String categoryName);

	List<Product> findProductsByPriceRange(double pricemin, double pricemax);

	List<Product> sortProductsByPriceAscending();

	List<Product> sortProductsByPriceDescending();

	List<Product> sortProductsByNameAsc();

	boolean findProductExists(String productName);

	Page<Product> displayProductsWithPagination(int page, int size);

}
