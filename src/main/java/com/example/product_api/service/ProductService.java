package com.example.product_api.service;

import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }
}
