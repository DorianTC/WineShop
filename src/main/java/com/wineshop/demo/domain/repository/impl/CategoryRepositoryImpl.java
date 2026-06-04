package com.wineshop.demo.domain.repository.impl;
import jakarta.persistence.NoResultException;

import com.wineshop.demo.domain.entity.Category;
import com.wineshop.demo.domain.entity.Product;
import com.wineshop.demo.domain.entity.User;
import com.wineshop.demo.domain.repository.CategoryRepository;
import com.wineshop.demo.domain.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category getCategory(Long id) {
        return entityManager.find(Category.class, id);
    }
    @Override
    public Category save(Category category) {
        entityManager.persist(category);
        return category;

    }
    public Category update(Category category) {
        entityManager.merge(category);
        return category;
    }
    public Category delete(Category category) {
        entityManager.remove(category);
        return category;
    }

    @Override
    public Category findByName(String name) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Override
    public List<Category> getAll() {
        return entityManager
                .createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

}
