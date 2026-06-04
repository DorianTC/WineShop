package com.wineshop.demo.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    @DisplayName("Should create category with default values")
    void shouldCreateCategoryWithDefaultValues() {
        assertNotNull(category);
        assertNull(category.getId());
        assertNull(category.getName());
        assertNull(category.getDescription());
        assertNull(category.getCreatedAt());
        assertNull(category.getUpdatedAt());
    }

    @Test
    @DisplayName("Should set and get id")
    void shouldSetAndGetId() {
        category.setId(1L);
        assertEquals(1L, category.getId());
    }

    @Test
    @DisplayName("Should set and get name")
    void shouldSetAndGetName() {
        category.setName("Vin Rosu");
        assertEquals("Vin Rosu", category.getName());
    }

    @Test
    @DisplayName("Should set and get description")
    void shouldSetAndGetDescription() {
        category.setDescription("Vinuri rosii de calitate");
        assertEquals("Vinuri rosii de calitate", category.getDescription());
    }

    @Test
    @DisplayName("Should initialize products list")
    void shouldInitializeProductsList() {
        assertNotNull(category.getProducts());
        assertTrue(category.getProducts().isEmpty());
    }

    @Test
    @DisplayName("Should set and get products list")
    void shouldSetAndGetProductsList() {
        Product product = new Product();
        product.setName("Merlot");

        category.getProducts().add(product);

        assertEquals(1, category.getProducts().size());
        assertEquals("Merlot", category.getProducts().get(0).getName());
    }

    @Test
    @DisplayName("Should set and get createdAt")
    void shouldSetAndGetCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        category.setCreatedAt(now);
        assertEquals(now, category.getCreatedAt());
    }

    @Test
    @DisplayName("Should set and get updatedAt")
    void shouldSetAndGetUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        category.setUpdatedAt(now);
        assertEquals(now, category.getUpdatedAt());
    }

    @Test
    @DisplayName("onCreate should set createdAt and updatedAt")
    void onCreateShouldSetTimestamps() {
        // Act
        category.onCreate();

        // Assert
        assertNotNull(category.getCreatedAt());
        assertNotNull(category.getUpdatedAt());
        assertEquals(category.getCreatedAt().getSecond(), category.getUpdatedAt().getSecond());
    }

    @Test
    @DisplayName("onUpdate should set updatedAt")
    void onUpdateShouldSetUpdatedAt() {
        // Arrange
        category.onCreate();
        LocalDateTime originalUpdatedAt = category.getUpdatedAt();

        // Wait a moment to ensure different timestamp
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Act
        category.onUpdate();

        // Assert
        assertNotNull(category.getUpdatedAt());
        assertTrue(category.getUpdatedAt().isAfter(originalUpdatedAt));
    }

    @Test
    @DisplayName("Should handle null description")
    void shouldHandleNullDescription() {
        category.setName("Vin Alb");
        category.setDescription(null);

        assertNull(category.getDescription());
        assertEquals("Vin Alb", category.getName());
    }
}