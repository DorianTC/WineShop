package com.wineshop.demo.domain.repository;

import com.wineshop.demo.domain.entity.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
    User delete(User user);
    User update(User user);
    User findByEmail(String email);
}
