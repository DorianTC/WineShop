package com.wineshop.demo.business.service;

import com.wineshop.demo.web.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    String save(ProductDTO productDTO);
    String delete(Long id);
    String update(Long id, ProductDTO productDTO);
    List<ProductDTO> getAll();
    ProductDTO getById(Long id);
}
