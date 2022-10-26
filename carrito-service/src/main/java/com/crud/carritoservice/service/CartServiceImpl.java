package com.crud.carritoservice.service;

import com.crud.carritoservice.dto.CartRequest;
import com.crud.carritoservice.dto.CartResponse;
import com.crud.carritoservice.entity.Cart;
import com.crud.carritoservice.mapper.CartMapper;
import com.crud.carritoservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public CartResponse createCart(CartRequest request) {
        Cart cart = cartMapper.entityToDto(request);
        Cart cartCreate = cartRepository.save(cart);
        return cartMapper.dtoToEntity(cartCreate);
    }

    @Override
    public CartResponse getBy(Long id) {
        Cart cart = getCart(id);
        return cartMapper.dtoToEntity(cart);
    }

    @Override
    public List<CartResponse> getCartByUserId(Long userId) {
        if(cartRepository.findById(userId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"User no exist");
        }
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<CartResponse> responses = cartMapper.dtoToEntityList(carts);
        return responses;
    }

    private Cart getCart(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Cart no encontrado");
        }
        return cart.get();

    }
}
