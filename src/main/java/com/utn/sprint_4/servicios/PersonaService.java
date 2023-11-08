package com.utn.sprint_4.servicios;

import com.utn.sprint_4.entidades.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonaService extends BaseService<Persona, Long> {

    List<Persona> search(String nombre, String apellido, String email, String telefono) throws Exception;

    Page<Persona> search(String nombre, String apellido, String email, String telefono, Pageable pageable) throws Exception;



}
