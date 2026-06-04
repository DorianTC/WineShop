package com.wineshop.demo.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProductRequest {
    private Long id;
    private String description;
    private int price;
    private String category;
    private int stockQuantity;
    private BigDecimal alcoholContent;
    private int volumeMl;
    private String country;
    private String region;
    private int year;


}
