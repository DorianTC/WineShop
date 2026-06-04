package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.exception.ProductAlreadyExistsException;
import com.wineshop.demo.business.exception.ProductNotFoundException;
import com.wineshop.demo.business.exception.messages.ExceptionMessages;
import com.wineshop.demo.business.service.ProductService;
import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.repository.CategoryRepository;
import com.wineshop.demo.domain.repository.ProductRepository;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.ProductDTO;
import com.wineshop.demo.web.dto.response.ProductResponse;
import com.wineshop.demo.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.wineshop.demo.business.exception.messages.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse save(ProductDTO productDTO) {
        Category category = categoryRepository.getCategory(productDTO.getCategoryId());
        if (category == null) {
            throw new RuntimeException(CATEGORY_NOT_FOUND);
        }

        Product existingProduct = productRepository.findByName(productDTO.getName());
        if (existingProduct != null) {
            throw new ProductAlreadyExistsException();
        }

        Product product = productMapper.toEntity(productDTO, category);
        productRepository.save(product);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setCategoryName(product.getCategory().getName());
        return productMapper.toResponse(product);
    }

    @Override
    public MessageResponse delete(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }

        productRepository.delete(product);
        MessageResponse messageResponse = new MessageResponse(PRODUCT_DELETED_SUCCESSFULLY);
        return messageResponse;
    }

    @Override
    public ProductResponse update(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id);

        if (existingProduct == null) {
            throw new ProductNotFoundException();
        }

        Product productWithSameName = productRepository.findByName(productDTO.getName());
        if (productWithSameName != null && !productWithSameName.getId().equals(id)) {
            throw new ProductAlreadyExistsException();
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
                throw new RuntimeException(CATEGORY_NOT_FOUND);
            }
            existingProduct.setCategory(category);
        }
        return productMapper.toResponse(existingProduct);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.getAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        return productMapper.toDTO(product);
    }
}