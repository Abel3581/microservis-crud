package com.crud.userservice.feignClients;

import com.crud.userservice.dto.CartRequest;
import com.crud.userservice.dto.CartResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name = "carrito-service", url = "http://localhost:8002", path = "/cart")
public interface CartFeignClient {
    @PostMapping("/create")
    CartResponse createCart(@RequestBody CartRequest request);

}
