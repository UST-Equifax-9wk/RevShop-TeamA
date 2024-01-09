package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Products")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/{sellerName}/addProduct")
    public Product addNewProduct(@RequestBody Product product, @PathVariable String sellerName){


        return productService.saveProduct(product);
    }
}
