package com.crud.carritoservice.mapper;

import com.crud.carritoservice.dto.CartRequest;
import com.crud.carritoservice.dto.CartResponse;
import com.crud.carritoservice.entity.Cart;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CartMapper {
    public Cart entityToDto(CartRequest request) {
        return Cart.builder()
                .createDate(LocalTime.now())
                .state(true)
                .userId(request.getUserId())
                .build();
    }

    public CartResponse dtoToEntity(Cart cartCreate) {
        return CartResponse.builder()
                .id(cartCreate.getId())
                .createDate(cartCreate.getCreateDate())
                .state(cartCreate.isState())
                .userId(cartCreate.getUserId())
                .build();
    }

    public List<CartResponse> dtoToEntityList(List<Cart> carts) {
        List<CartResponse> responses = new ArrayList<>();
        CartResponse response;
        for(Cart c: carts){
            response = new CartResponse();
            response.setId(c.getId());
            response.setState(c.isState());
            response.setCreateDate(c.getCreateDate());
            response.setUserId(c.getUserId());
            responses.add(response);
        }
        return responses;
    }
}
