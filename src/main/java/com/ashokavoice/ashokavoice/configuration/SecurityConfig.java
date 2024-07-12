package com.ashokavoice.ashokavoice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ashokavoice.ashokavoice.configuration.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authConfig -> {
                authConfig
                .requestMatchers(HttpMethod.POST, "/register" ).permitAll()
                .requestMatchers(HttpMethod.GET,"/register/confirmar").permitAll()
                .requestMatchers(HttpMethod.GET,"/confirmar/token/{tokenConfirmacion}").permitAll()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .requestMatchers(HttpMethod.POST, "/login/authenticate" ).permitAll()
                .requestMatchers(HttpMethod.PUT, "users/editarPerfil/{idUsuario}" ).permitAll()
                .requestMatchers(HttpMethod.PUT, "users/editarContrasena/{idUsuario}" ).permitAll()
                .requestMatchers(HttpMethod.PUT, "users/fotoPerfil/{idUsuario}" ).permitAll()
                .requestMatchers(HttpMethod.DELETE, "users/eliminarCuenta/{idUsuario}" ).permitAll()
                .requestMatchers(HttpMethod.POST, "/logros" ).permitAll()
                .requestMatchers(HttpMethod.GET, "/logros/all/{userId}" ).permitAll()
                .requestMatchers(HttpMethod.GET, "/logros/feed/{userId}" ).permitAll()
                .requestMatchers(HttpMethod.PUT, "/logros/{id_logro}" ).permitAll()
                .requestMatchers(HttpMethod.DELETE, "/logros/{id_logro}" ).permitAll()
                .requestMatchers(HttpMethod.PUT, "/logros/ocultar/{id_logro}" ).permitAll()
                .requestMatchers(HttpMethod.POST,"/likes/agregar").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/likes/eliminar").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/comments").permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/comments/editar/{id_comentario}").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/api/comments/eliminar/{id_comentario}").permitAll()
                .requestMatchers(HttpMethod.GET,"/extra/download/{userId}").permitAll()
                .anyRequest().authenticated();
            });
        return http.build();
    }
}
