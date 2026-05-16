package com.example.product_api.service;

import com.example.product_api.dto.ProductRequestDTO;
import com.example.product_api.dto.ProductResponseDTO;
import com.example.product_api.exception.ProductNotFoundException;
import com.example.product_api.model.Product;
import com.example.product_api.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private static final Logger logger =
            LoggerFactory.getLogger(ProductService.class);

    public ProductResponseDTO create(ProductRequestDTO dto) {

        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        Product saved = repository.save(product);
        logger.info("Criando produto: {}", dto.getName());

        return toResponse(saved);
    }

    public List<ProductResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProductResponseDTO findById(UUID id) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
    }

    public ProductResponseDTO update(
            UUID id,
            ProductRequestDTO dto
    ) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());

        Product updatedProduct = repository.save(product);

        return new ProductResponseDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getDescription()
        );
    }

    public void delete(UUID id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

        repository.delete(product);
        logger.info("Removendo produto: {}", id);
    }

    private ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
    }

}
