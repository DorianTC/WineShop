package com.wineshop.demo.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }
   @Test
    @DisplayName("Should product be displayed with default values")
    void shouldProductBeDisplayedWithDefaultValues() {
        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getCategory());
        assertNull(product.getPrice());
        assertNull(product.getDescription());
        assertNull(product.getCountry());
        assertNull(product.getRegion());
        assertNull(product.getAlcoholContent());
        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());
   }

    @Test
    @DisplayName("Should set and get id")
    void shouldSetAndGetId(){
        product.setId(1L);
        assertEquals(1L, product.getId());
    }

    @Test
    @DisplayName("Should set and get description")
    void shouldSetAndGetDescription(){
        product.setDescription("Un vin roșu de calitate superioară cu note de cireșe negre și vanilie");
        assertEquals("Un vin roșu de calitate superioară cu note de cireșe negre și vanilie", product.getDescription());
    }
    @Test
    @DisplayName("Should set and get name")
    void shouldSetAndGetCategory(){
        Category category = new Category();
        category.setName("Vin Alb");
        assertEquals(category, product.getCategory());
        assertEquals("Vin Alb", product.getCategory().getName());

    }

    @Test
    @DisplayName("Should set and get name")
    void shouldSetAndGetName(){
        product.setName("Cabernet Sauvignon Reserve");
        assertEquals("Cabernet Sauvignon Reserve", product.getName());
    }
}
