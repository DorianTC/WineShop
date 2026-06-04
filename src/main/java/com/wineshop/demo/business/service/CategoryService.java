package com.wineshop.demo.business.service;

import com.wineshop.demo.web.dto.CategoryDTO;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse save(CategoryDTO categoryDTO);
    CategoryResponse update(Long id,CategoryDTO categoryDTO);
    MessageResponse delete(Long id);
    List<CategoryResponse> getAll();
}
