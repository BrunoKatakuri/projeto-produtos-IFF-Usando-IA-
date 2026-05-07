package com.example.product_api.controller;

import com.example.product_api.model.Product;
import com.example.product_api.service.ProductService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

}
