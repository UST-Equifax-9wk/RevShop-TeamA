package com.revature.revshopserver.services;

import com.revature.revshopserver.dtos.ReviewDto;
import com.revature.revshopserver.entities.Buyer;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.entities.Review;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Set;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    // TODO: come back to this when BuyerService is implemented
    public Review writeReview(Integer productId, ReviewDto reviewDto) throws ObjectNotFoundException {
        Product product = productService.getById(productId);
        // Buyer author = buyerService.getById(reviewDto.getBuyerId());
        Buyer author = new Buyer();
        return reviewRepository.save(new Review(
            reviewDto.getRating(),
            reviewDto.getDescription(),
            ZonedDateTime.now(),
            product,
            author
        ));
    }

    public Set<Review> getAllProductReviews(Integer productId) {
        return reviewRepository.findAllByProductId(productId);
    }

    public Set<Review> getAllBuyerReviews(Integer buyerId) {
        return reviewRepository.findAllByBuyerId(buyerId);
    }
}
