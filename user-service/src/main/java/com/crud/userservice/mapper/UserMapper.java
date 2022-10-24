package com.crud.userservice.mapper;

import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User entityToDto(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();
    }

    public UserResponse dtoToEntity(User user) {
      return UserResponse.builder()
              .id(user.getId())
              .email(user.getEmail())
              .name(user.getName())
              .build();
    }
}
