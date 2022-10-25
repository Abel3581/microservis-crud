package com.crud.carritoservice.service;

import com.crud.carritoservice.dto.CartRequest;
import com.crud.carritoservice.dto.CartResponse;

import java.util.List;

public interface CartService {
    CartResponse createCart(CartRequest request);

    CartResponse getBy(Long id);

    List<CartResponse> getCartByUserId(Long userId);
}
