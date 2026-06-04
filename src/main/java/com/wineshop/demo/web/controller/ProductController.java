package com.wineshop.demo.web.controller;

import com.wineshop.demo.business.exception.CategoryNotFoundException;
import com.wineshop.demo.business.exception.ProductAlreadyExistsException;
import com.wineshop.demo.business.exception.ProductNotFoundException;
import com.wineshop.demo.business.exception.UserAlreadyExistsException;
import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.dto.response.ProductResponse;
import com.wineshop.demo.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductDTO dto) {
            ProductResponse response = productService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/delete/{id}")  // ← endpoint DELETE
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        return   ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody ProductDTO dto) {

        ProductResponse productResponse = productService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO product = productService.getById(id);
        return ResponseEntity.ok(product);
    }
}
