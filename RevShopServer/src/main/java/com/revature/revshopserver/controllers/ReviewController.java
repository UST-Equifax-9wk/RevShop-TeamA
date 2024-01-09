package com.revature.revshopserver.controllers;

import com.revature.revshopserver.dtos.ReviewDto;
import com.revature.revshopserver.entities.Review;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // TODO: adjust implementation once Product and Buyer are implemented fully
    @PostMapping(path = "/product/{product_id}")
    public Review writeReview(@PathVariable(name = "product_id") Integer productId,
                              @RequestBody ReviewDto reviewDto) throws ObjectNotFoundException {
        // filler product and author for now, have to wait for implementation
        return reviewService.writeReview(
            productId, reviewDto
        );
    }

    // TODO: adjust implementation based on ProductController endpoints later
    @GetMapping(path = "/product/{product_id}/reviews")
    public Set<Review> getAllProductReviews(@PathVariable(name = "product_id") Integer productId) {
        return reviewService.getAllProductReviews(productId);
    }

    // TODO: adjust implementation based on BuyerController endpoints later
    @GetMapping(path = "/buyer/{buyer_id}/reviews")
    public Set<Review> getAllBuyerReviews(@PathVariable(name = "buyer_id") Integer buyerId) {
        return reviewService.getAllBuyerReviews(buyerId);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
