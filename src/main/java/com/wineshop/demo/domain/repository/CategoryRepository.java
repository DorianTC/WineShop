package com.wineshop.demo.domain.repository;

import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.User;

import java.util.List;

public interface CategoryRepository {
    Category getCategory(Long id);
    Category save(Category category);
    Category update(Category category);
    Category delete(Category category);
    Category findByName(String name);
    List<Category> getAll();
    Category findById(Long id);
}
