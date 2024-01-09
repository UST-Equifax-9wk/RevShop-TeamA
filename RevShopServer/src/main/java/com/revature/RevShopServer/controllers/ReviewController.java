package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.dtos.ReviewDto;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.entities.Review;
import com.revature.RevShopServer.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // TODO: adjust implementation once Product and Buyer are implemented fully
    @PostMapping(path = "/product/{product_id}/{buyer_id}")
    public Review writeReview(@PathVariable(name = "product_id") Integer product_id,
                              @PathVariable(name = "buyer_id") Integer buyer_id,
                              @RequestBody ReviewDto reviewDto) {
        // filler product and author for now, have to wait for implementation
        return reviewService.writeReview(
            new Review(reviewDto.getRating(),
                    reviewDto.getDescription(),
                    ZonedDateTime.now(),
                    new Product(),
                    new Buyer())
        );
    }

    // TODO: adjust implementation later
    @GetMapping(path = "/product/{product_id}/reviews")
    public Set<Review> getAllReviews(@PathVariable(name = "product_id") Integer productId) {
        return reviewService.getAllReviews(productId);
    }

}
