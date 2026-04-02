package com.wineshop.demo.business.service;


import com.wineshop.demo.web.dto.UserDTO;
import com.wineshop.demo.web.dto.request.RegisterUserRequest;
import com.wineshop.demo.web.dto.response.UserResponse;

public interface UserService  {
    UserResponse save(RegisterUserRequest registerUserRequest);
    String delete(Long id);
    String update(UserDTO userDTO);
    UserResponse login(RegisterUserRequest registerUserRequest);
}
