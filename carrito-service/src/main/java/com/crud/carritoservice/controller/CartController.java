package com.crud.carritoservice.controller;

import com.crud.carritoservice.dto.CartRequest;
import com.crud.carritoservice.dto.CartResponse;
import com.crud.carritoservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<CartResponse> createCart(@RequestBody CartRequest request){
        CartResponse response = cartService.createCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getBy(@PathVariable Long id){
        CartResponse response = cartService.getBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<CartResponse>> getCartByUserId(@PathVariable Long userId){
        List<CartResponse> cartResponses = cartService.getCartByUserId(userId);
        if(cartResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }
}
