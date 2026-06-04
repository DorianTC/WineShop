package com.wineshop.demo.business.service;


import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.UserDTO;
import com.wineshop.demo.web.dto.request.RegisterUserRequest;
import com.wineshop.demo.web.dto.response.UserResponse;

public interface UserService  {
    UserResponse save(RegisterUserRequest registerUserRequest);
    MessageResponse delete(Long id);
    UserResponse update( UserDTO userDTO);
    UserResponse login(RegisterUserRequest registerUserRequest);
}
