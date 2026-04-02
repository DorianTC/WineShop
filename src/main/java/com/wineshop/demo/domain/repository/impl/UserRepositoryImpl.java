package com.wineshop.demo.domain.repository.impl;
import jakarta.persistence.EntityManager;
import com.wineshop.demo.domain.entity.User;
import com.wineshop.demo.domain.repository.UserRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);  // INSERT
        return user;
    }

    @Override
    public User findById(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public User delete(User user) {
        entityManager.remove(user);
        return user;
    }

    @Override
    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        try {
            return entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
