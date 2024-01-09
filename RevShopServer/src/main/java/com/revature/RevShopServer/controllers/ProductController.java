package com.revature.RevShopServer.controllers;

import com.revature.RevShopServer.entities.Product;
import com.revature.RevShopServer.exceptions.ObjectNotFoundException;
import com.revature.RevShopServer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/{sellerId}/addProduct")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product addNewProduct(@RequestBody Product product, @PathVariable int sellerId) throws ObjectNotFoundException {


        return productService.saveProduct(product, sellerId);
    }
}
