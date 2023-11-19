package com.utn.sprint_4.repositorios;

import com.utn.sprint_4.dtos.RankingPersonasDTO;
import com.utn.sprint_4.entidades.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{


    /*
    SELECT COUNT(Persona .id), SUM(Factura.totalVenta)
    FROM Persona LEFT JOIN Pedido ON Pedido.persona_id = Persona.id LEFT JOIN Factura ON Pedido.factura_id = Factura.id
    WHERE Factura.fecha_Facturacion BETWEEN "%2023-01-01 00:00:00%" AND "%2023-12-01 00:00:00%" GROUP BY Persona.id
    */



    @Query(value = "SELECT DISTINCT persona.* FROM persona " +
            "LEFT JOIN pedido ON pedido.persona_id = persona.id " +
            "WHERE pedido.fecha_alta BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<Persona> rankingPersonas(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin")Date fechaFin);


    @Query(value = "Select c FROM Persona c WHERE c.nombre LIKE %:nombre% OR c.apellido LIKE %:apellido% OR c.telefono LIKE %:telefono%")
    List<Persona> search(@Param("nombre")  String nombre, @Param("apellido")String apellido, @Param("telefono")String telefono);

    @Query(value = "Select c FROM Persona c WHERE c.nombre LIKE %:nombre% OR c.apellido LIKE %:apellido% OR c.telefono LIKE %:telefono%")
    Page<Persona> search(@Param("nombre")  String nombre, @Param("apellido")String apellido, @Param("telefono")String telefono, Pageable pageable);


    @Query(value = "Select * FROM Persona WHERE Persona.nombre LIKE %:nombre% AND Persona.apellido LIKE %:apellido% AND Persona.telefono LIKE %:telefono%",
            nativeQuery = true
    )
    List<Persona> searchNativo(@Param("nombre")  String nombre, @Param("apellido")String apellido, @Param("telefono")String telefono);

    @Query(value = "Select * FROM Persona WHERE Persona.nombre LIKE %:nombre% OR Persona.apellido LIKE %:apellido% OR Persona.telefono LIKE %:telefono%",
            countQuery = "SELECT count(*) FROM Persona",
            nativeQuery = true
    )
    Page<Persona> searchNativo(@Param("nombre")  String nombre, @Param("apellido")String apellido, @Param("telefono")String telefono, Pageable pageable);

    @Query(value = "SELECT * FROM PERSONA  WHERE PERSONA.ID_USUARIO=(SELECT ID FROM USUARIO WHERE USUARIO.USERNAME LIKE %:user% AND USUARIO.auth0Id LIKE %:pass%)",
            nativeQuery = true
    )
    Persona searchNativo(@Param("user") String user, @Param("pass") String pass);

        //Modificar Empleado COmo Administrador
    @Query(value = "Select * FROM Persona WHERE Persona.legajo LIKE %:legajo% ",
            nativeQuery = true
    )
    List<Persona> modificarEmpleadoA(@Param("legajo")  String legajo);

    //Listado de Empleados Administrador
    @Query(value = "SELECT * FROM Persona WHERE Persona.rol = 2 OR Persona.rol = 3 ",
            nativeQuery = true
    )
    List<Persona> ListaEmpleadosAdm();

    //Busacar persona por legajo
    @Query(value = "Select c FROM Persona c WHERE c.legajo LIKE %:legajo% ")
    List<Persona> buscarPorLegajo(@Param("legajo")  String legajo);

    //Listado de Clientes Administrador

    @Query(value = "Select * FROM Persona WHERE Persona.rol = 1 ",
            nativeQuery = true
    )
    List<Persona> ListaClientesAdm();


    Optional<Persona> findByEmail(String email);
}






