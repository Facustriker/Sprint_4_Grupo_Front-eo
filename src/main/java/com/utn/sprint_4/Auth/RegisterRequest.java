package com.utn.sprint_4.Auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String firstname;
    String lastname;
    String username;
    String telefono;
    String password;
    String email;
    String direccion; //'Calle' en Domicilio
    String departamento; //'Localidad' en Domicilio
    String fechaNacimiento;
}
