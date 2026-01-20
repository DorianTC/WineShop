package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.repository.ProductRepository;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public String save(ProductDTO productDTO) {
        Category category = new Category();
        Product product = productMapper.toEntity(productDTO, category);
        return productRepository.save(product);
    }
}
