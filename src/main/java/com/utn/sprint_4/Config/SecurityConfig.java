package com.utn.sprint_4.Config;

import com.utn.sprint_4.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.apache.commons.lang3.BooleanUtils.and;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest

                                .requestMatchers(new AntPathRequestMatcher("/auth/register")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/auth/login")).hasAnyAuthority("ADMINISTRADOR", "COCINERO", "DELIVERY", "CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO", "DELIVERY")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/personas/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/usuarios/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroArticulo/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/unidadMedida/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/facturas/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/ArticuloManufacturado/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/ArticuloInsumo/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO")
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demoAdmin/**")).hasAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demoUser/**")).hasAuthority("CLIENTE")
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

}