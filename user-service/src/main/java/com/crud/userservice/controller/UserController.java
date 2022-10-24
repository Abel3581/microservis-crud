package com.crud.userservice.controller;

import com.crud.userservice.dto.UserRequest;
import com.crud.userservice.dto.UserResponse;
import com.crud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
