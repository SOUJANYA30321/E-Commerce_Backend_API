package com.dcl.shop.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dcl.shop.exceptions.ProductNotFoundByBrandException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryException;
import com.dcl.shop.exceptions.ProductNotFoundByCategoryNameException;
import com.dcl.shop.exceptions.ProductNotFoundByNameException;
import com.dcl.shop.exceptions.ProductNotFoundByPriceRangeException;
import com.dcl.shop.exceptions.ProductNotFoundByProductIdException;
import com.dcl.shop.exceptions.ProductsNotFoundException;
import com.dcl.shop.model.Product;
import com.dcl.shop.repository.ProductRepository;
import com.dcl.shop.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductRepository repository;

	
	/*  ADD PRODUCT  */
	@Override
	public Product addProduct(Product product) {
		return repository.save(product);
	}

	
	/*  DISPLAY ALL PRODUCTS  */
	@Override
	public List<Product> displayAllProducts()  {
		List<Product> list = repository.findAll();
		
		if(list.isEmpty()) {
			throw new ProductsNotFoundException("NO PRODUCTS FOUND");
		} else {
			return list;
		}
	}

	
	/*  DISPLAY PRODUCT BY PRODUCT ID  */
	@Override
	public Product displayProductById(int productId) {
		Optional<Product> optional = repository.findByProductId(productId);
		
		if(optional.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  UPDATE PRODUCT BY PRODUCT ID  */
	@Override
	public Product updateProductById(int productId, Product updatedProduct) {

	    Optional<Product> optional = repository.findByProductId(productId);

	    if (optional.isEmpty()) {
	        throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
	    }

	    Product existingProduct = optional.get();

	    existingProduct.setProductName(updatedProduct.getProductName());
	    existingProduct.setDescription(updatedProduct.getDescription());
	    existingProduct.setBrand(updatedProduct.getBrand());
	    existingProduct.setPrice(updatedProduct.getPrice());
	    existingProduct.setStock(updatedProduct.getStock());
	    existingProduct.setImageUrl(updatedProduct.getImageUrl());
	    existingProduct.setCategory(updatedProduct.getCategory());

	    return repository.save(existingProduct);
	}

	
	/* DELETE PRODUCT BY PRODUCT ID  */
	@Override
	public Product deleteProductById(int productId) {
		Optional<Product> optional = repository.findByProductId(productId);
		
		if(optional.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		} else {
			Product existingProduct = optional.get();
			repository.delete(existingProduct);
			return existingProduct;
		}
	}

	
	/*  FIND PRODUCT BY PRODUCT NAME  */
	@Override
	public Product findProductByName(String productName) {
		Optional<Product> optional = repository.findByProductNameIgnoreCase(productName);
		
		if(optional.isEmpty()) {
			throw new ProductNotFoundByNameException("NO PRODUCT FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  SEARCH PRODUCTS BY KEYWORD  */
	@Override
	public List<Product> searchProductsByKeyword(String keyword) {
		List<Product> productList = repository.findByProductNameContainingIgnoreCase(keyword);
		
		if(productList.isEmpty()) {
			throw new ProductNotFoundByNameException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  FIND PRODUCTS BY BRAND  */
	@Override
	public List<Product> findProductsByBrand(String brand) {
		List<Product> productList = repository.findByBrandIgnoreCase(brand);
		
		if(productList.isEmpty()) {
			throw new ProductNotFoundByBrandException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  FIND PRODUCTS BY CATEGORY ID  */
	@Override
	public List<Product> findProductsByCategory(int categoryId) {
		List<Product> productList = repository.findByCategoryCategoryId(categoryId);
		
		if(productList.isEmpty()) {
			throw new ProductNotFoundByCategoryException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  FIND PRODUCTS BY CATEGORY NAME  */
	@Override
	public List<Product> findProductsByCategoryName(String categoryName) {
		List<Product> productList = repository.findByCategoryCategoryNameIgnoreCase(categoryName);
		
		if(productList.isEmpty()) {
			throw new ProductNotFoundByCategoryNameException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  FIND PRODUCTS BY PRICE RANGE  */
	@Override
	public List<Product> findProductsByPriceRange(double pricemin, double pricemax) {
		List<Product> productList = repository.findByPriceBetween(pricemin, pricemax);
		
		if(productList.isEmpty()) {
			throw new ProductNotFoundByPriceRangeException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  SORT PRODUCTS BY PRICE IN ASCENDING ORDER  */
	@Override
	public List<Product> sortProductsByPriceAscending() {
		List<Product> productList = repository.findAllByOrderByPriceAsc();
		
		if(productList.isEmpty()) {
			throw new ProductsNotFoundException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  SORT PRODUCTS BY PRICE IN DESCENDING ORDER  */
	@Override
	public List<Product> sortProductsByPriceDescending() {
		List<Product> productList = repository.findAllByOrderByPriceDesc();
		
		if(productList.isEmpty()) {
			throw new ProductsNotFoundException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  SORT PRODUCTS BY NAME IN ASCENDING ORDER  */
	@Override
	public List<Product> sortProductsByNameAsc() {
		List<Product> productList = repository.findAllByOrderByProductNameAsc();
		
		if(productList.isEmpty()) {
			throw new ProductsNotFoundException("NO PRODUCT FOUND");
		} else {
			return productList;
		}
	}

	
	/*  CHECK PRODUCT EXISTS USING PRODUCT NAME  */
	@Override
	public boolean findProductExists(String productName) {
		return repository.existsByProductNameIgnoreCase(productName);			
	}

	
	/*  DISPLAY PRODUCTS WITH PAGINATION  */
	@Override
	public Page<Product> displayProductsWithPagination(int page, int size) {

	    Pageable pageable = PageRequest.of(page, size);

	    Page<Product> productPage = repository.findAll(pageable);

	    if(productPage.isEmpty()) {
	        throw new ProductsNotFoundException("NO PRODUCTS FOUND");
	    }

	    return productPage;
	}
}
