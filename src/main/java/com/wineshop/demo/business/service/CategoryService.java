package com.wineshop.demo.business.service;

import com.wineshop.demo.web.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    String save(CategoryDTO categoryDTO);
    String update(CategoryDTO categoryDTO);
    String delete(Long id);
    List<CategoryDTO> getAll();
}
