package com.wineshop.demo.web.controller;

import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.business.service.CategoryService;

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
    public ResponseEntity<String> create(@RequestBody CategoryDTO dto) {
        String message = categoryService.save(dto);
        if (message.equals(ExceptionMessages.CATEGORY_ALREADY_EXIST)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody CategoryDTO dto) {

        String message = categoryService.update(dto);

        if (message.equals(ExceptionMessages.EMAIL_ADDRESS_NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = categoryService.delete(id);

        if (message.equals(ExceptionMessages.CATEGORY_NOT_FOUND)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.ok(message);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }
}

