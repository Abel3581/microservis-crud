package com.crud.userservice.service;

import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.model.Cart;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest);

    UserResponse getBy(Long id);

    List<Cart> getCarts(Long userId);
}
