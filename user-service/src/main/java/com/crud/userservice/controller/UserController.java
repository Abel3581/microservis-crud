package com.crud.userservice.controller;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.dto.CartResponse;
import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.model.Cart;
import com.crud.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "cartCB", fallbackMethod = "fallbackGetCarts")
    @GetMapping("/carts/{userId}")
    public ResponseEntity<List<Cart>> getCarts(@PathVariable Long userId){
        List<Cart> carts = userService.getCarts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }
    private ResponseEntity<List<Cart>> fallbackGetCarts(@PathVariable Long userId, RuntimeException e){
        return new ResponseEntity("El usuario " + userId + " tiene los carts fuera de servicio", HttpStatus.OK);
    }
    @CircuitBreaker(name = "cartCB", fallbackMethod = "fallbackCreateCart")
    @PostMapping("/saveCart/{userId}")
    public ResponseEntity<CartResponse> createCart(@PathVariable("userId") Long userId, @RequestBody CartRequest request){
        CartResponse response = userService.createCart(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    private ResponseEntity<CartResponse> fallbackCreateCart(@PathVariable("userId") Long userId, @RequestBody CartRequest request, RuntimeException e){
        return new ResponseEntity("El usuario" + userId + " no puede crear carts el servicio esta caido", HttpStatus.OK);
    }
    @CircuitBreaker(name = "cartCB", fallbackMethod = "fallbackGetCartByUserId")
    @GetMapping("/getCarts/{userId}")
    public ResponseEntity<List<CartResponse>> getCartByUserId(@PathVariable Long userId){
        List<CartResponse> cartResponses = userService.getCartByUserId(userId);
        if(cartResponses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.OK).body(cartResponses);
    }
    private ResponseEntity<List<CartResponse>> fallbackGetCartByUserId(@PathVariable Long userId, RuntimeException e){
        return new ResponseEntity("El usuario " + userId + " no puede obtener los carts porque esta caido el servicio", HttpStatus.OK);
    }
    //Todo los metodos privados se usan para mostrar un mensaje cuando el microservicio esta caida en este caso el cart
}
