package com.crud.gatewayservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http){
        //Cualquier request debe estar autenticado
        http.authorizeExchange().anyExchange().authenticated()
                .and()
                .oauth2Client(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}
