package com.crud.userservice.controller;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.dto.CartResponse;
import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.model.Cart;
import com.crud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
            UserResponse response = userService.create(userRequest);
            return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getBy(@PathVariable Long id){
        UserResponse response = userService.getBy(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/carts/{userId}")
    public ResponseEntity<List<Cart>> getCarts(@PathVariable Long userId){
        List<Cart> carts = userService.getCarts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }

    @PostMapping("/saveCart/{userId}")
    public ResponseEntity<CartResponse> createCart(@PathVariable("userId") Long userId, @RequestBody CartRequest request){
        CartResponse response = userService.createCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getCarts/{userId}")
    public ResponseEntity<List<CartResponse>> getCartByUserId(@PathVariable Long userId){
        List<CartResponse> cartResponses = userService.getCartByUserId(userId);
        if(cartResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }

}
