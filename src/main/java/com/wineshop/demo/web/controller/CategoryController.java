package com.wineshop.demo.web.controller;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.business.service.CategoryService;

import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.dto.response.CategoryResponse;
import com.wineshop.demo.web.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryDTO dto) {
        CategoryResponse response = categoryService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @RequestBody CategoryDTO dto) {

        CategoryResponse response = categoryService.update(id ,dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));
        }
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
}

