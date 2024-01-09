package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.dtos.ReviewDto;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.entities.Review;
import com.revature.RevShopServer.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // this mapping will be subject to change once implementation is finalized for other parts
    @PostMapping(path = "/product/{product_id}/{buyer_id}")
    public Review writeReview(@PathVariable(name = "product_id") Integer product_id,
                              @PathVariable(name = "buyer_id") Integer buyer_id,
                              @RequestBody ReviewDto reviewDto) {
        // filler product and author for now, have to wait for implementation
        return reviewService.writeReview(
            new Review(reviewDto.getRating(),
                    reviewDto.getDescription(),
                    LocalDateTime.now(),
                    new Product(),
                    new Buyer())
        );
    }

}
