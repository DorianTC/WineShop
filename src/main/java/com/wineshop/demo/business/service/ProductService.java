package com.wineshop.demo.business.service;

import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.web.dto.ProductDTO;

public interface ProductService {
    String save(ProductDTO productDTO);
}
