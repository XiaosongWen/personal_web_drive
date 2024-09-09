//package com.xiaosong.api.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/api/v1/user/**") // Disable CSRF for the user API
//                )
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/user/**").permitAll()  // Allow access to login and index pages without authentication
//                        .anyRequest().authenticated()                    // All other requests require authentication
//                );
//        //        http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(authorize -> authorize
////                        .anyRequest().permitAll()  // Allow all requests without authentication
////                );
//        return http.build();
//    }
//}
