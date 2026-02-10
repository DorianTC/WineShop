package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.repository.CategoryRepository;
import com.wineshop.demo.domain.repository.ProductRepository;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    @Override
    public String save(ProductDTO productDTO) {
        Category category = categoryRepository.getCategory(1L);
        if (category == null) {
            return "Product category does not exists!";
        }
        Product product = productMapper.toEntity(productDTO, category);
        if (product.getId() == null) {
            return productRepository.save(product);
        }
        return "Product already exists!";
    }
    public String delete(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return "Product not found";
        }
        if (product != null) {
            return productRepository.delete(product);
        }
        return null;
    }
    public String update(ProductDTO productDTO) {
        Category category = categoryRepository.getCategory(1L);
        if (category == null) {
            return "Product category does not exists!";
        }
        try {
            return productRepository.update(productMapper.toEntity(productDTO, category));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
