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

import java.util.List;
import java.util.stream.Collectors;

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
    public String update(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id);

        if (existingProduct == null) {
            return "Product not found";
        }
        existingProduct.setName(productDTO.getName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());
        existingProduct.setAlcoholContent(productDTO.getAlcoholContent());
        existingProduct.setVolumeMl(productDTO.getVolumeMl());
        existingProduct.setCountry(productDTO.getCountry());
        existingProduct.setRegion(productDTO.getRegion());
        existingProduct.setYear(productDTO.getYear());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.getCategory(productDTO.getCategoryId());
            if (category == null) {
                return "Category not found";
            }
            existingProduct.setCategory(category);
        }
        productRepository.update(existingProduct);

        return "Product updated successfully!";
    }
    @Override
    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                .stream()
                .map(product -> productMapper.toDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return null;
        }
        return productMapper.toDTO(product);
    }
}
