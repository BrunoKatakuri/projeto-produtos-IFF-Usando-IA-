package com.example.product_api.controller;

import com.example.product_api.model.Product;
import com.example.product_api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product created = service.create(product);

        return ResponseEntity.status(201).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();

        return ResponseEntity.ok(products);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id) {
        Product product = service.findById(id);

        return ResponseEntity.ok(product);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable UUID id,
            @RequestBody Product product
    ) {
        Product updated = service.update(id, product);

        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}






