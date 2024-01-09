package com.revature.RevShopServer.services;

import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.entities.Seller;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.repositories.ProductRepository;
import com.revature.RevShopServer.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(Transactional.TxType.REQUIRED)
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

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

    public Product getById(Integer productId) throws ObjectNotFoundException {
        Optional<Product> productLookup = productRepository.findById(productId);
        if (productLookup.isPresent()) {
            return productLookup.get();
        }
        throw new ObjectNotFoundException("Product ID not found in database!");
    }
}
