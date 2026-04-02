package com.wineshop.demo.domain.repository;

import com.wineshop.demo.domain.entity.Product;

import java.util.List;


public interface  ProductRepository {
    String save(Product product);
    String delete(Product product);
    Product findById(Long id);
    String update(Product product);
    List<Product> getAll();
}
