package com.utn.sprint_4.controladores;

import com.utn.sprint_4.dtos.ListaClientesDTO;
import com.utn.sprint_4.dtos.ModificarEmpleadoDTO;
import com.utn.sprint_4.entidades.Persona;
import com.utn.sprint_4.servicios.PersonaServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/personas")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{


    @GetMapping("/rankingPersonas")
    public ResponseEntity<?> rankingPersonas (Date fechaInicio, Date fechaFin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.rankingPersonas(fechaInicio, fechaFin));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search (String nombre, String apellido, String telefono){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(nombre, apellido, telefono));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search (String nombre, String apellido, String telefono, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(nombre, apellido, telefono, pageable));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));

        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> search (String user, String pass){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(user, pass));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }
    @PostMapping("/modificarEmpleadoA")
    public ResponseEntity<?> modificarEmpleadoA (@RequestBody ModificarEmpleadoDTO modificarEmpleadoDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificarEmpleadoA(modificarEmpleadoDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/ListaEmpleadosAdm")
    public ResponseEntity<?> ListaEmpleadosAdm(ModificarEmpleadoDTO modificarEmpleadoDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.ListaEmpleadosAdm(modificarEmpleadoDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/buscarPorLegajo")
    public ResponseEntity<?> buscarPorLegajo (String legajo){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorLegajo(legajo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/ListaClientesAdm")
    public ResponseEntity<?> ListaClientesAdm(ListaClientesDTO listaClientesDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.ListaClientesAdm(listaClientesDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

    @GetMapping("/encontrarPorEmail")
    public ResponseEntity<?> getByEmail(String email){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.getByEmail(email));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\""+e.getMessage()+"\"}"));
        }
    }

}
