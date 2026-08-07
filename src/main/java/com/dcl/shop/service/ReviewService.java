package com.dcl.shop.service;

import java.util.List;

import com.dcl.shop.model.Review;

public interface ReviewService {

	Review addReview(Review review, int userId, int productId);

	List<Review> displayAllReviews();

	Review getReviewByReviewId(int reviewId);

	List<Review> getReviewByProductId(int productId);

	List<Review> getAllReviewsWrittenByUser(int userId);

	Review updateReview(int reviewId, Review updatedReview);

	Review deleteReview(int reviewId);

	Integer totalReviewCount(int productId);

	List<Review> getReviewsByRating(int rating);

	Double getAverageRatingOfAProduct(int productId);

}
