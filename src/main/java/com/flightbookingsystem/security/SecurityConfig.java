package com.flightbookingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private String baseUrl = "/api/admin";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, this.baseUrl).permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + " /users/load/**").permitAll()
                        .requestMatchers(HttpMethod.POST, this.baseUrl+ "/users").permitAll()
                        .requestMatchers(HttpMethod.PATCH, this.baseUrl+ "/users//disable/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PATCH, this.baseUrl+ "/users//enable/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/users/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/users/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/flights").permitAll()
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/flights/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/add-flight").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/edit-flight/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/flights/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/planes").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/planes/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/add-plane").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/edit-plane/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/planes/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/promos").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/promos/**").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/add-promo").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/edit-promo/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/promos/**").hasAuthority("ROLE_admin")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/tickets").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/tickets/**").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/tickets").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/tickets/**").hasAnyAuthority("ROLE_admin", "ROLE_user")
                        .anyRequest().authenticated()
                )
                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
                            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
                            config.setAllowedHeaders(Arrays.asList("*"));
                            config.setAllowCredentials(true);
                            return config;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
