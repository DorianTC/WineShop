package com.wineshop.demo.domain.repository;

import com.wineshop.demo.domain.entity.Category;

public interface CategoryRepository {
    Category getCategory(Long id);
}
