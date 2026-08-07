package com.dcl.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcl.shop.model.Review;
import com.dcl.shop.model.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	Optional<Review> findByReviewId(int reviewId);

	Optional<Review> findByUser_UserIdAndProduct_ProductId(int userId, int productId);

	List<Review> findByProduct_ProductId(int productId);

	List<Review> findByUser(User user);

	List<Review> findByRating(int rating);

}
