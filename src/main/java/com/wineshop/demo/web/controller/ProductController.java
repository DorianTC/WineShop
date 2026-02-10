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
    @DeleteMapping("/{id}")  // ← endpoint DELETE
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = productService.delete(id);

        if (message.equals("Product cannot be found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }
    @PatchMapping("/update")
    public ResponseEntity<String> update(@RequestBody ProductDTO dto) {
        String message = productService.update(dto);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product could not be updated" +
                    " because invalid data was provided.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
