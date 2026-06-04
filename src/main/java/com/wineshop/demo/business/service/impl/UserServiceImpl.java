package com.wineshop.demo.business.service.impl;

import com.wineshop.demo.business.exception.UnauthorizedException;
import com.wineshop.demo.business.exception.UserAlreadyExistsException;
import com.wineshop.demo.business.service.UserService;
import com.wineshop.demo.domain.entity.User;
import com.wineshop.demo.domain.repository.UserRepository;
import com.wineshop.demo.web.dto.MessageResponse;
import com.wineshop.demo.web.dto.UserDTO;
import com.wineshop.demo.web.dto.request.RegisterUserRequest;
import com.wineshop.demo.web.dto.response.UserResponse;
import com.wineshop.demo.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.wineshop.demo.business.exception.messages.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(RegisterUserRequest registerUserRequest) {
        User existingUser = userRepository.findByEmail(registerUserRequest.getEmail());

        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        User user = userMapper.toEntity(registerUserRequest);

        String hashedPassword = passwordEncoder.encode(registerUserRequest.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
    public UserResponse login(RegisterUserRequest registerUserRequest) {
        User loggedUser = userRepository.findByEmail(registerUserRequest.getEmail());


        if(loggedUser == null){
            throw new UsernameNotFoundException(USER_NOT_FOUND );
        }
        boolean userPassword = passwordEncoder.matches(registerUserRequest.getPassword(),loggedUser.getPassword());
        if(!userPassword){
            throw new UnauthorizedException("Password Incorrect");
        }
        return  userMapper.toResponse(loggedUser);

    }

    public MessageResponse delete(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        userRepository.delete(user);
        MessageResponse messageResponse = new MessageResponse(USER_DELETED_SUCCESSFULLY);
        return messageResponse;
    }

    public UserResponse update(UserDTO userDTO) {
        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setEmail(userDTO.getEmail());
        if (userDTO.getId()!=null) {
            if (userDTO.getEmail() == null) {
                throw new RuntimeException(EMAIL_ADDRESS_NOT_FOUND);
            }
            existingUser.setEmail(userDTO.getEmail());
        }
        userRepository.update(existingUser);
        return   userMapper.toResponse(existingUser);
    }


}

