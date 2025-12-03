package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
