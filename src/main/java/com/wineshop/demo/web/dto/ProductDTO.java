package com.wineshop.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long categoryId;
    private String categoryName;
    private String imageUrl;
    private BigDecimal alcoholContent;
    private Integer volumeMl;
    private String country;
    private String region;
    private Integer year;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
