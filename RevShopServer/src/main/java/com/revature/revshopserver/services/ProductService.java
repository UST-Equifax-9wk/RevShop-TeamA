package com.revature.revshopserver.services;

import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.entities.Seller;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.ProductRepository;
import com.revature.revshopserver.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ProductService {
    private ProductRepository productRepository;
    private SellerRepository sellerRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    public Product saveProduct(Product product, int sellerId) throws ObjectNotFoundException {
        Optional<Seller> seller = sellerRepository.findById(sellerId);
        if (seller.isEmpty())
            throw new ObjectNotFoundException("Cannot find seller with that Id");
        product.setSeller(seller.get());
        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
