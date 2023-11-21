package com.utn.sprint_4.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import com.utn.sprint_4.JWT.JwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest

                                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/auth/register")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/auth/login")).hasAnyAuthority("ADMINISTRADOR", "COCINERO", "DELIVERY", "CLIENTE")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/pedidos/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO", "DELIVERY")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/personas/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/usuarios/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroArticuloInsumo/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/unidadMedida/**")).hasAnyAuthority("ADMINISTRADOR", "COCINERO")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/facturas/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/rubroArticuloManufacturado/**")).hasAnyAuthority("ADMINISTRADOR")
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demoAdmin/**")).hasAuthority("ADMINISTRADOR")
                                .requestMatchers(new AntPathRequestMatcher("/api/v1/demoUser/**")).hasAuthority("CLIENTE")
                )
                .cors(withDefaults())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //H2
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
