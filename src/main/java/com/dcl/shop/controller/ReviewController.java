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

import com.dcl.shop.model.Review;
import com.dcl.shop.service.ReviewService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ReviewController {
	private final ReviewService service;
	
	
	/*  ADD REVIEW  */
	@PostMapping("/reviews/user/{userId}/product/{productId}")
	public ResponseEntity<ResponseStructure<Review>> addReview(@RequestBody Review review, @PathVariable int userId, @PathVariable int productId) {
		Review reviewAdded = service.addReview(review, userId, productId);
		
		ResponseStructure<Review> rs = new ResponseStructure<Review>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Review object created successfully.");
		rs.setData(reviewAdded);
		
		return new ResponseEntity<ResponseStructure<Review>>(rs, HttpStatus.CREATED);
	}
	
	
	/*  DISPLAY ALL REVIEWS  */
	@GetMapping("/reviews")
	public ResponseEntity<ResponseStructure<List<Review>>> displayAllReviews() {
		List<Review> reviewList = service.displayAllReviews();
		
		ResponseStructure<List<Review>> rs = new ResponseStructure<List<Review>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All the reviews displayed successfully.");
		rs.setData(reviewList);
		
		return new ResponseEntity<ResponseStructure<List<Review>>>(rs, HttpStatus.OK);
	}
	
	
	/*  GET REVIEW BY REVIEW ID  */
	@GetMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<Review>> getReviewByReviewId(@PathVariable int reviewId) {
		Review reviewFound = service.getReviewByReviewId(reviewId);
		
		ResponseStructure<Review> rs = new ResponseStructure<Review>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Review fetched successfully for reviewId: "+reviewId);
		rs.setData(reviewFound);
		
		return new ResponseEntity<ResponseStructure<Review>>(rs, HttpStatus.OK);
	}
	
	
	/*  GET REVIEW BY PRODUCT ID  */
	@GetMapping("/reviews/product/{productId}")
	public ResponseEntity<ResponseStructure<List<Review>>> getReviewByProductId(@PathVariable int productId) {
		List<Review> productReview = service.getReviewByProductId(productId);
		
		ResponseStructure<List<Review>> rs = new ResponseStructure<List<Review>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Review fetched successfully for the requested product.");
		rs.setData(productReview);
		
		return new ResponseEntity<ResponseStructure<List<Review>>>(rs, HttpStatus.OK);
	}
	
	
	/*  GET ALL REVIEWS WRITTEN BY A USER USING USER ID  */
	@GetMapping("/reviews/user/{userId}")
	public ResponseEntity<ResponseStructure<List<Review>>> getAllReviewsWrittenByUser(@PathVariable int userId) {
		List<Review> userReviewList = service.getAllReviewsWrittenByUser(userId);
		
		ResponseStructure<List<Review>> rs = new ResponseStructure<List<Review>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("All the reviews written by user: " + userId+ " fetched successfully.");
		rs.setData(userReviewList);
		
		return new ResponseEntity<ResponseStructure<List<Review>>>(rs, HttpStatus.OK);
	}
	
	
	/*  UPDATE REVIEW BY REVIEW ID  */
	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<Review>> updateReviewByReviewId(@PathVariable int reviewId, @RequestBody Review updatedReview) {
		Review newReview = service.updateReview(reviewId, updatedReview);	
		
		ResponseStructure<Review> rs = new ResponseStructure<Review>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Review updated successfully for the requested review Id: "+reviewId);
		rs.setData(newReview);
		
		return new ResponseEntity<ResponseStructure<Review>>(rs, HttpStatus.OK);
	}
	
	
	/*  DELETE REVIEW  */
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ResponseStructure<Review>> deleteReview(@PathVariable int reviewId) {
		Review deletedReview = service.deleteReview(reviewId);
		
		ResponseStructure<Review> rs = new ResponseStructure<Review>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Review deleted successfully for the requested review Id: "+reviewId);
		rs.setData(deletedReview);
		
		return new ResponseEntity<ResponseStructure<Review>>(rs, HttpStatus.OK);
	}
	

	/*  TOTAL REVIEW COUNT  */
	@GetMapping("/reviews/product/{productId}/count")
	public ResponseEntity<ResponseStructure<Integer>> totalReviewCount(@PathVariable int productId) {
		Integer reviewCount = service.totalReviewCount(productId);
		
		ResponseStructure<Integer> rs = new ResponseStructure<Integer>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Total review count of a product displayed successfully.");
		rs.setData(reviewCount);
		
		return new ResponseEntity<ResponseStructure<Integer>>(rs, HttpStatus.OK);
	}
	
	
	/*  GET REVIEWS BY RATING  */
	@GetMapping("/reviews/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<Review>>> getReviewsByRating(@PathVariable int rating) {
		List<Review> reviewList = service.getReviewsByRating(rating);
		
		ResponseStructure<List<Review>> rs = new ResponseStructure<List<Review>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Reviews displayed successfully for a requested rating: "+rating);
		rs.setData(reviewList);
		
		return new ResponseEntity<ResponseStructure<List<Review>>>(rs, HttpStatus.OK);
	}
	
	
	/*  AVERAGE-RATING OF A PRODUCT  */
	@GetMapping("/reviews/product/{productId}/average-rating")
	public ResponseEntity<ResponseStructure<Double>> getAverageRatingOfAProduct(@PathVariable int productId) {
		Double avgRating = service.getAverageRatingOfAProduct(productId);
		
		ResponseStructure<Double> rs = new ResponseStructure<Double>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Average-Rating of the requested product displayed successfully.");
		rs.setData(avgRating);
		
		return new ResponseEntity<ResponseStructure<Double>>(rs, HttpStatus.OK);
	}
}
