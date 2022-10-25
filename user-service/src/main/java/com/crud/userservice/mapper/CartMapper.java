package com.crud.userservice.mapper;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.model.Cart;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CartMapper {
    public Cart entityToDto(CartRequest request) {
        return Cart.builder()
                .userId(request.getUserId())
                .state(request.isState())
                .createDate(LocalTime.now())
                .build();
    }
}
