package com.wineshop.demo.web.controller;

import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.web.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ProductDTO dto) {
        String message = productService.save(dto);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product creation failed," +
                    " product already exists!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
