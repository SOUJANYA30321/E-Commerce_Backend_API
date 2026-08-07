package com.dcl.shop.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dcl.shop.exceptions.ProductNotFoundByProductIdException;
import com.dcl.shop.exceptions.ProductReviewAlreadyExistsException;
import com.dcl.shop.exceptions.RatingRangeException;
import com.dcl.shop.exceptions.ReviewNotFoundByReviewIdException;
import com.dcl.shop.exceptions.ReviewNotFoundException;
import com.dcl.shop.exceptions.UserNotFoundByUserIdException;
import com.dcl.shop.model.Product;
import com.dcl.shop.model.Review;
import com.dcl.shop.model.User;
import com.dcl.shop.repository.ProductRepository;
import com.dcl.shop.repository.ReviewRepository;
import com.dcl.shop.repository.UserRepository;
import com.dcl.shop.service.ReviewService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	
	/*  ADD REVIEW  */
	@Override
	public Review addReview(Review review, int userId, int productId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);

		if(optionalUser.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		}
		User user = optionalUser.get();

		Optional<Product> optionalProduct = productRepository.findByProductId(productId);

		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		}
		Product product = optionalProduct.get();

		Optional<Review> optionalReview = reviewRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);

		if(optionalReview.isPresent()) {
			throw new ProductReviewAlreadyExistsException("YOU HAVE ALREADY REVIEWED THIS PRODUCT");
		}
		review.setUser(user);
		review.setProduct(product);

		if(review.getRating() < 1 || review.getRating() > 5) {
			throw new RatingRangeException("Rating must be between 1 and 5.");
		}

		return reviewRepository.save(review);
	}


	/*  DISPLAY ALL REVIEWS  */
	@Override
	public List<Review> displayAllReviews() {
		List<Review> reviewList = reviewRepository.findAll();
		
		if(reviewList.isEmpty()) {
			throw new ReviewNotFoundException("NO REVIEWS FOUND");
		} else {
			return reviewList;
		}
	}


	/*  GET REVIEW BY REVIEW ID  */
	@Override
	public Review getReviewByReviewId(int reviewId) {
		Optional<Review> reviewFound = reviewRepository.findByReviewId(reviewId);
		
		if(reviewFound.isEmpty()) {
			throw new ReviewNotFoundByReviewIdException("NO REVIEW FOUND");
		} else {
			return reviewFound.get();
		}
	}


	/*  GET REVIEW BY PRODUCT ID  */
	@Override
	public List<Review> getReviewByProductId(int productId) {
		List<Review> productReviewList = reviewRepository.findByProduct_ProductId(productId);
		
		if(productReviewList.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO REVIEW EXISTS FOR THE REQUESTED PRODUCT.");
		} else {
			return productReviewList;
		}
	}


	/*  GET ALL REVIEWS WRITTEN BY A USER USING USER ID  */
	@Override
	public List<Review> getAllReviewsWrittenByUser(int userId) {
		Optional<User> optionalUser = userRepository.findByUserId(userId);
		
		if(optionalUser.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		}
		User user = optionalUser.get();
		
		List<Review> usersReviewList = reviewRepository.findByUser(user);
		
		if(usersReviewList.isEmpty()) {
			throw new ReviewNotFoundException("NO REVIEWS FOUND");
		} else {
			return usersReviewList;
		}
	}


	/*  UPDATE REVIEW USING REVIEW ID  */
	@Override
	public Review updateReview(int reviewId, Review updatedReview) {
		Optional<Review> optionalReview = reviewRepository.findByReviewId(reviewId);
		
		if(optionalReview.isEmpty()) {
			throw new ReviewNotFoundByReviewIdException("NO REVIEW FOUND");
		}else {
			Review existingReview = optionalReview.get();
			
			if(updatedReview.getRating() < 1 || updatedReview.getRating() > 5) {
				throw new RatingRangeException("Rating must be between 1 and 5.");
			}
			existingReview.setRating(updatedReview.getRating());
			existingReview.setComment(updatedReview.getComment());
			return reviewRepository.save(existingReview);
		}
	}


	/*  DELETE REVIEW  */
	@Override
	public Review deleteReview(int reviewId) {
		Optional<Review> optionalReview = reviewRepository.findByReviewId(reviewId);
		
		if(optionalReview.isEmpty()) {
			throw new ReviewNotFoundByReviewIdException("NO REVIEW FOUND");
		} else {
			Review existingReview = optionalReview.get();
			reviewRepository.delete(existingReview);
			return existingReview;
		}
	}


	/*  TOTAL REVIEW COUNT  */
	@Override
	public Integer totalReviewCount(int productId) {
		Optional<Product> optionalProduct = productRepository.findByProductId(productId);
		
		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		}
		
		List<Review> reviews = reviewRepository.findByProduct_ProductId(productId);
		return reviews.size();
	}


	/*  GET REVIEWS BY RATING  */
	@Override
	public List<Review> getReviewsByRating(int rating) {
		
		if(rating < 1 || rating > 5) {
			throw new RatingRangeException("RATING IS BETWEEN 1 AND 5.");
		}
		
		List<Review> list = reviewRepository.findByRating(rating);
		
		if(list.isEmpty()) {
			throw new ReviewNotFoundException("NO REVIEW FOUND");
		} else {
			return list;
		}
	}


	/*  AVERAGE-RATING OF A PRODUCT  */
	@Override
	public Double getAverageRatingOfAProduct(int productId) {
		Optional<Product> optionalProduct = productRepository.findByProductId(productId);
		
		if(optionalProduct.isEmpty()) {
			throw new ProductNotFoundByProductIdException("NO PRODUCT FOUND");
		}
		
		List<Review> reviewsList = reviewRepository.findByProduct_ProductId(productId);
		
		if(reviewsList.isEmpty()) {
			throw new ReviewNotFoundException("NO REVIEW FOUND");
		} 
		
		Double totalRating = 0.0, average = 0.0, count = 0.0;
		
		for (Review review : reviewsList) {
			totalRating = totalRating + review.getRating();
			count++;
		}
		average = totalRating/count;
		return average;
	}
}
