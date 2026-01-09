package com.example.kiosk.config;

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
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/images/**",
                                                                "/api/auth/**",
                                                                "/h2-console/**")
                                                .permitAll()
                                                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Admin API access
                                                                                                   // only
                                                .requestMatchers("/admin.html").authenticated() // Allow all members to
                                                                                                // access admin page
                                                                                                // (for menu config)
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/index.html")
                                                .loginProcessingUrl("/api/auth/login")
                                                .defaultSuccessUrl("/kiosk.html", true)
                                                .failureUrl("/index.html?error=true")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/api/auth/logout")
                                                .logoutSuccessUrl("/"));

                return http.build();
        }
}
