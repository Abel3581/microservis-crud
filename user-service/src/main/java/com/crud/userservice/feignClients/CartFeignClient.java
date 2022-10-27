package com.crud.userservice.feignClients;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "cart-service", path = "/cart")
public interface CartFeignClient {
    @PostMapping("/create")
    CartResponse createCart(@RequestBody CartRequest request);

    @GetMapping("/byUser/{userId}")
    public List<CartResponse> getCartByUserId(@PathVariable Long userId);

}
