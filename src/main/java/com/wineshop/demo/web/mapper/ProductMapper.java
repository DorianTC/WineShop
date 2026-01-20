package com.wineshop.demo.web.mapper;

import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.web.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(category);
        product.setAlcoholContent(dto.getAlcoholContent());
        product.setVolumeMl(dto.getVolumeMl());
        product.setCountry(dto.getCountry());
        product.setRegion(dto.getRegion());
        product.setYear(dto.getYear());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .alcoholContent(product.getAlcoholContent())
                .volumeMl(product.getVolumeMl())
                .country(product.getCountry())
                .region(product.getRegion())
                .year(product.getYear())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
