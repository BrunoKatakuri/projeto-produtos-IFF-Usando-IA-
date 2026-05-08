package com.example.product_api.service;

import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new RuntimeException("Nome obrigatório");
        }

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new RuntimeException("Preço inválido");
        }

        return repository.save(product);
    }

    // READ ALL
    public List<Product> findAll() {
        return repository.findAll();
    }

    // RED BY ID
    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // UPDATE
    public Product update(UUID id, Product updatedProduct) {
        Product product = findById(id);

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());

        return repository.save(product);
    }

    // DELETE
    public void delete(UUID id) {
        Product product = findById(id);

        repository.delete(product);
    }

}
