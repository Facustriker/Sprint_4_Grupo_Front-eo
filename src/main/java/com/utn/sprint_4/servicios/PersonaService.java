package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.ListaClientesDTO;
import com.utn.sprint_4.dtos.ModificarEmpleadoDTO;
import com.utn.sprint_4.dtos.PersonaDTO;
import com.utn.sprint_4.dtos.RankingPersonasDTO;
import com.utn.sprint_4.entidades.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonaService extends BaseService<Persona, Long> {

    List<RankingPersonasDTO> rankingPersonas(Date fechaInicio, Date fechaFin) throws Exception;

    List<Persona> search(String nombre, String apellido, String telefono) throws Exception;

    PersonaDTO search(String user, String pass) throws Exception;

    Page<Persona> search(String nombre, String apellido, String telefono, Pageable pageable) throws Exception;

    public Persona modificarEmpleadoA(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception;

    List<ModificarEmpleadoDTO> ListaEmpleadosAdm(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception;

    List<Persona> buscarPorLegajo(String legajo) throws Exception;

    List<ListaClientesDTO> ListaClientesAdm(ListaClientesDTO listaClientesDTO) throws Exception;

    Persona getByEmail(String email) throws Exception;
}
