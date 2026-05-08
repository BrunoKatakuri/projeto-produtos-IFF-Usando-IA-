package com.example.product_api.controller;

import com.example.product_api.dto.ProductRequestDTO;
import com.example.product_api.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    private final ProductService service;

    public WebController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "products",
                service.findAll()
        );

        return "products";
    }

    @PostMapping("/products")
    public String createProduct(
            ProductRequestDTO dto
    ) {

        service.create(dto);

        return "redirect:/";
    }
}