package com.crud.userservice.service;

import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;

public interface UserService {
    UserResponse create(UserRequest userRequest);

    UserResponse getBy(Long id);
}
