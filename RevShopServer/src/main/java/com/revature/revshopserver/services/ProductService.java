package com.revature.revshopserver.services;

import com.revature.revshopserver.dtos.ProductDto;
import com.revature.revshopserver.entities.Product;
import com.revature.revshopserver.entities.Seller;
import com.revature.revshopserver.exceptions.ObjectNotFoundException;
import com.revature.revshopserver.repositories.ProductRepository;
import com.revature.revshopserver.repositories.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<ProductDto> getAllProducts(){
        List<Product> result = productRepository.findAll();
        List<ProductDto> arr = new ArrayList<>();
        for (Product product : result) {
            arr.add(new ProductDto(product));
        }
        return arr;
    }

    public Product getById(Integer productId) throws ObjectNotFoundException {
        Optional<Product> productLookup = productRepository.findById(productId);
        if (productLookup.isPresent()) {
            return productLookup.get();
        }
        throw new ObjectNotFoundException("Product ID not found in database!");
    }

    public Set<Product> getAllProductsBySellerId(Integer sellerId){
        return productRepository.findAllBySellerId(sellerId);
    }
}
