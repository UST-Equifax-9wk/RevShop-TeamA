package com.revature.RevShopServer.services;

import com.revature.RevShopServer.dtos.ReviewDto;
import com.revature.RevShopServer.entities.Buyer;
import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.entities.Review;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.repositories.ReviewRepository;
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

    public Set<Review> getAllReviews(Integer productId) {
        return reviewRepository.findAllByProductId(productId);
    }
}
