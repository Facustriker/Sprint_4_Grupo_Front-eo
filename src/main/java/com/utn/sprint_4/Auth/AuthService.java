package com.utn.sprint_4.Auth;

import com.utn.sprint_4.JWT.JwtService;
import com.utn.sprint_4.User.*;
import com.utn.sprint_4.entidades.Domicilio;
import com.utn.sprint_4.entidades.Persona;
import com.utn.sprint_4.entidades.Usuario;
import com.utn.sprint_4.enumeraciones.Rol;
import com.utn.sprint_4.repositorios.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PersonaRepository personaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = personaRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        Domicilio domicilio = Domicilio.builder()
                .calle(request.getDireccion())
                .localidad(request.getDepartamento())
                .build();

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .build();

        Persona persona = Persona.builder()
                .nombre(request.getFirstname())
                .apellido(request.getLastname())
                .telefono(request.getTelefono())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fechaNacimiento(request.getFechaNacimiento())
                .rol(Rol.CLIENTE)
                .build();


        persona.AgregarDomicilios(domicilio);
        persona.setUsuario(usuario);


        personaRepository.save(persona);

        return AuthResponse.builder()
                .token(jwtService.getToken(persona))
                .build();


    }
}
