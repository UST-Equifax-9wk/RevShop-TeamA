package com.revature.RevShopServer.services;

import com.revature.RevShopServer.entities.Review;
import com.revature.RevShopServer.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review writeReview(Review review) {
        return reviewRepository.save(review);
    }

    public Set<Review> getAllReviews(Integer productId) {
        return reviewRepository.findAllByProductId(productId);
    }
}
