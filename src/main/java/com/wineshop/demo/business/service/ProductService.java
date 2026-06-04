package com.wineshop.demo.business.service;

import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse save(ProductDTO productDTO);
    MessageResponse delete(Long id );
    ProductResponse update(Long id, ProductDTO productDTO);
    List<ProductDTO> getAll();
    ProductDTO getById(Long id);
}
