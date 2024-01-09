package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.dtos.ReviewDto;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.entities.Review;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
    public Review writeReview(@PathVariable(name = "product_id") Integer product_id,
                              @RequestBody ReviewDto reviewDto) throws ObjectNotFoundException {
        // filler product and author for now, have to wait for implementation
        return reviewService.writeReview(
            product_id, reviewDto
        );
    }

    // TODO: adjust implementation later
    @GetMapping(path = "/product/{product_id}/reviews")
    public Set<Review> getAllReviews(@PathVariable(name = "product_id") Integer productId) {
        return reviewService.getAllReviews(productId);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> internalErrorHandler(ObjectNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
