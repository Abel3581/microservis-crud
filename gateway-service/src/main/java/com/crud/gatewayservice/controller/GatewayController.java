package com.crud.gatewayservice.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

    //devuelve el id de la cookie de inicio de session
    @GetMapping("/")
    public Mono<String> index(WebSession session){
        return Mono.just(session.getId());
    }
    //obtener token
    @GetMapping("/token")
    public Mono<String> getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client){
        return Mono.just(client.getAccessToken().getTokenValue());
    }
}
