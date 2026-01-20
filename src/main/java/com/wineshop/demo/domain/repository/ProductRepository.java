package com.wineshop.demo.domain.repository;

import com.wineshop.demo.domain.entity.Product;
import org.springframework.stereotype.Repository;


public interface  ProductRepository {
    String save(Product product);
}
