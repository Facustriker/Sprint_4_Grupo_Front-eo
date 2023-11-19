package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.ListaClientesDTO;
import com.utn.sprint_4.dtos.ModificarEmpleadoDTO;
import com.utn.sprint_4.dtos.PersonaDTO;
import com.utn.sprint_4.dtos.RankingPersonasDTO;
import com.utn.sprint_4.entidades.DetallePedido;
import com.utn.sprint_4.entidades.Pedido;
import com.utn.sprint_4.entidades.Persona;
import com.utn.sprint_4.repositorios.BaseRepository;
import com.utn.sprint_4.repositorios.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
        super(baseRepository);
        this.personaRepository = personaRepository;
    }

    @Override
    public List<RankingPersonasDTO> rankingPersonas(Date fechaInicio, Date fechaFin) throws Exception {
        try {
            List<Persona> personasFiltradas = personaRepository.rankingPersonas(fechaInicio, fechaFin);
            List<RankingPersonasDTO> rankingPersonas = new ArrayList<>();

            int totalSum = 0; // Variable para la suma de totales
            int cantidadPedidosSum = 0; // Variable para la suma de cantidades de pedidos

            for (Persona persona : personasFiltradas) {
                RankingPersonasDTO dtoAux = new RankingPersonasDTO();
                dtoAux.setPersonaNombre(persona.getNombre());

                for (Pedido pedido : persona.getPedidos()) {
                    totalSum += pedido.getTotal(); // Sumar al total
                    for (DetallePedido detallePedido : pedido.getDetallesPedidos()) {
                        cantidadPedidosSum += detallePedido.getCantidad(); // Sumar a la cantidad de pedidos
                    }
                }

                dtoAux.setTotal(totalSum); // Asignar la suma de totales al DTO
                dtoAux.setCantidadPedidos(cantidadPedidosSum); // Asignar la suma de cantidades de pedidos al DTO

                rankingPersonas.add(dtoAux);
            }

            return rankingPersonas;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @Override
    public List<Persona> search(String nombre, String apellido, String telefono) throws Exception {
        try{
            List<Persona> personas = personaRepository.searchNativo(nombre, apellido, telefono);
            return personas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Persona> search(String nombre, String apellido, String telefono, Pageable pageable) throws Exception {
        try{
            Page<Persona> clientes = personaRepository.searchNativo(nombre, apellido, telefono, pageable);
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PersonaDTO search(String user, String pass) throws Exception {
        try{
            Persona persona = personaRepository.searchNativo(user, pass);
            PersonaDTO personaDTO = new PersonaDTO(persona.getNombre(), persona.getApellido());
            return personaDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Persona> buscarPorLegajo(String legajo) throws Exception {
        try{
            List<Persona> personas = personaRepository.buscarPorLegajo(legajo);
            return personas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    //Modificar Empleado como Administrador
    @Override
    public Persona modificarEmpleadoA(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception {
        try{
            List<Persona> persona = personaRepository.buscarPorLegajo(modificarEmpleadoDTO.getLegajo());
            if (persona.isEmpty()) throw new Exception("no se encontro el empleado");
            Persona entityUpdate = new Persona();
            entityUpdate.setApellido(modificarEmpleadoDTO.getApellidoDTO());
            entityUpdate.setRol(modificarEmpleadoDTO.getRolDTO());
            entityUpdate.setNombre(modificarEmpleadoDTO.getNombreDTO());
            entityUpdate.setTelefono(modificarEmpleadoDTO.getTelefonoDTO());
            entityUpdate.setLegajo(modificarEmpleadoDTO.getLegajo());
            personaRepository.save(entityUpdate);
            return entityUpdate;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // Ver lista de Empleados como Administrador
    @Override
    public List<ModificarEmpleadoDTO> ListaEmpleadosAdm(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception {
        try{
            List<Persona> personas = personaRepository.ListaEmpleadosAdm();
            List<ModificarEmpleadoDTO> empleadosDTO = new ArrayList<>();
            for (Persona persona: personas) {
                ModificarEmpleadoDTO auxDTO = new ModificarEmpleadoDTO();
                auxDTO.setNombre(persona.getNombre());
                auxDTO.setApellido(persona.getApellido());
                auxDTO.setTelefono(persona.getTelefono());
                auxDTO.setLegajo(persona.getLegajo());
                auxDTO.setRol(persona.getRol());
                empleadosDTO.add(auxDTO);
            }
            return  empleadosDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    // Ver lista de Clientes como Administrador
    @Override
    public List<ListaClientesDTO> ListaClientesAdm(ListaClientesDTO listaClientesDTO) throws Exception {
        try {
            List<Persona> personas = personaRepository.ListaClientesAdm();
            List<ListaClientesDTO> clienteDTO = new ArrayList<>();
            for (Persona persona : personas) {
                ListaClientesDTO auxDTO = new ListaClientesDTO();
                auxDTO.setApellido(persona.getApellido());
                auxDTO.setNombre(persona.getNombre());
                auxDTO.setEmail(persona.getEmail());
                auxDTO.setRol(persona.getRol());
                auxDTO.setTelefono(persona.getTelefono());
                clienteDTO.add(auxDTO);
            }
            return clienteDTO;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Persona getByEmail(String email) throws Exception {
        try {
            Persona p = personaRepository.findByEmail(email).get();
            return p;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




}
