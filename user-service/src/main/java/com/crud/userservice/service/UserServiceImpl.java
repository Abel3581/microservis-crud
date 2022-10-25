package com.crud.userservice.service;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.dto.CartResponse;
import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.entity.User;
import com.crud.userservice.feignClients.CartFeignClient;
import com.crud.userservice.mapper.CartMapper;
import com.crud.userservice.mapper.UserMapper;
import com.crud.userservice.model.Cart;
import com.crud.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RestTemplate restTemplate;
    @Autowired
    CartFeignClient cartFeignClient;
    private final CartMapper cartMapper;

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
    //Con restTemplate
    @Override
    public List<Cart> getCarts(Long userId) {
        User user = getUser(userId);
        List<Cart> carts = restTemplate.getForObject("http://localhost:8002/cart/byUser/" + userId,List.class);
        return carts;
    }
    // con openfeign
    @Override
    public CartResponse createCart(CartRequest request) {
        CartResponse cart = cartFeignClient.createCart(request);
        return cart;
    }

    private User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not exist");
        }
        return user.get();
    }


}
