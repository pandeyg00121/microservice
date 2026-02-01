package com.gateway.microservice.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        Think of SecurityFilterChain as: The firewall of your API Gateway
//        HttpSecurity httpSecurity : This is Spring’s DSL for: auth rules ,JWT handling ,CSRF ,sessions
        return httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())  //Core security rule : Every single API call must be authenticated & No public endpoints
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();   // Creates the security filter chain.
//        lINE 17 is the most important line. It tells Spring:
//          This app is NOT login UI
//          It just validates JWT tokens.
//          Tokens come from OAuth2 provider (Keycloak)
//  Spring will: Read JWT from Authorization: Bearer <token> - Verify signature - Check expiry - Decode user info
    }
}
