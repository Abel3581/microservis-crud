package com.crud.userservice.service;

import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.entity.User;
import com.crud.userservice.mapper.UserMapper;
import com.crud.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;

    private final UserMapper userMapper;
    @Override
    public UserResponse create(UserRequest request) {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"email no encontrado");
        }
        User userCreate = userRepository.save(userMapper.entityToDto(request));
        User userSaved = userRepository.save(userCreate);
        return userMapper.dtoToEntity(userSaved);
    }

    @Override
    public UserResponse getBy(Long id) {
        User user = getUser(id);

        return userMapper.dtoToEntity(user);
    }

    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not exist");
        }
        return user.get();
    }
}
