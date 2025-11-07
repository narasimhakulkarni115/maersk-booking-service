package com.maersk.booking.config;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;
import com.maersk.booking.dto.CheckAvailabilityRequest;  // Assuming CheckAvailabilityRequest is in the dto package
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;





@Configuration
@Profile("test") // Only active during test profile
@EnableWebFluxSecurity
public class TestSecurityConfig {
	
	  public TestSecurityConfig() {
	         System.out.println("TestSecurityConfig loaded for test profile!");
	     }
	     

	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // ✅ disable CSRF for APIs
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/**").permitAll()
	                .anyRequest().authenticated()
	            );

	        return http.build();
	    }
	}