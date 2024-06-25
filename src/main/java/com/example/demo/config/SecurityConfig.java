package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  /*@Bean
  InMemoryUserDetailsManager userDetailsService() {
    UserDetails admin = User
        .withUsername("admin")
        .password(passwordEncoder().encode("admin1234"))
        .roles("ADMIN")
        .build();
    UserDetails student = User
        .withUsername("student")
        .password(passwordEncoder().encode("student5678"))
        .roles("USER")
        .build();
    return new InMemoryUserDetailsManager(admin, student);
  }*/

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login //  フォーム認証を使う
        .permitAll())
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/css/**").permitAll() // CSSファイルは認証不要で使えるようにする
            .requestMatchers("/").permitAll() //  トップページは認証不要
            .requestMatchers("/signup").hasRole("ADMIN")
            .anyRequest().authenticated() //  他のURLはログイン後アクセス可能
        );

    return http.build();
  }
  
}