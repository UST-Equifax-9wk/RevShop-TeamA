package com.revature.revshopserver.services;


import com.revature.revshopserver.entities.Discount;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.DiscountRepository;
import com.revature.revshopserver.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class DiscountService {
    private DiscountRepository discountRepository;
    private ProductRepository productRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, ProductRepository productRepository) {
        this.discountRepository = discountRepository;
        this.productRepository = productRepository;
    }

    public Discount saveDiscount(Discount discount, int productId) throws ObjectNotFoundException {
        Optional< Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new ObjectNotFoundException("Could not find a product with that id");
        discount.setProduct(product.get());
        return discountRepository.save(discount);
    }
}
