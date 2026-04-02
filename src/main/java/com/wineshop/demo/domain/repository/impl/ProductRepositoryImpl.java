package com.wineshop.demo.domain.repository.impl;

import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String save(Product product) {
        entityManager.persist(product);
        return "Product saved succesfully!";
    }

    @Override
    public String delete(Product product) {
        entityManager.remove(product);
        return "Product deleted succesfully!";
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

   @Override
    public String update(Product product) {
        entityManager.merge(product);
        return "Product updated succesfully!";
    }
    @Override
    public List<Product> getAll() {
        return entityManager
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
