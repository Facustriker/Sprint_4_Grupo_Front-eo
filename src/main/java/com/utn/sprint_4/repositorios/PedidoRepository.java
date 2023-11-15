package com.utn.sprint_4.repositorios;

import com.utn.sprint_4.entidades.Domicilio;
import com.utn.sprint_4.entidades.Pedido;
import com.utn.sprint_4.enumeraciones.EstadoPedido;
import com.utn.sprint_4.enumeraciones.FormaPago;
import com.utn.sprint_4.enumeraciones.TipoEnvio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    @Query(value = "SELECT * FROM pedido WHERE pedido.fechaPedido LIKE %:fechaPedido% OR pedido.horaEstimadaFinalizacion LIKE %:horaEstimadaFinalizacion% OR pedido.total LIKE %:total% OR pedido.totalCosto LIKE %:totalCosto% OR pedido.estado LIKE %:estado% OR pedido.tipoEnvio " +
            "LIKE %:tipoEnvio% OR pedido.formaPago LIKE %:formaPago% OR pedido.domicilioEntrega LIKE %:domicilioEntrega% OR pedido.fechaAlta LIKE %:fechaAlta% OR pedido.fechaModificacion LIKE %:fechaModificacion% OR pedido.fechaBaja %:fechaBaja% ",
            nativeQuery = true)
    List<Pedido> searchNativo(@Param("fechaPedido") Date fechaPedido, @Param("horaEstimadaFinalizacion") Date horaEstimadaFinalizacion, @Param("total") Number total, @Param("totalCosto") Number totalCosto, @Param("estado") EstadoPedido estado, @Param("tipoEnvio") TipoEnvio tipoEnvio,
                              @Param("formaPago") FormaPago formaPago, @Param("domicilioEntrega") Domicilio domicilioEntrega, @Param("fechaAlta") Date fechaAlta, @Param("fechaModificacion") Date fechaModificacion, @Param("fechaBaja") Date fechaBaja);

    @Query(value = "SELECT * FROM pedido WHERE pedido.fechaPedido LIKE %:fechaPedido% OR pedido.horaEstimadaFinalizacion LIKE %:horaEstimadaFinalizacion% OR pedido.total LIKE %:total% OR pedido.totalCosto LIKE %:totalCosto% OR pedido.estado LIKE %:estado% OR pedido.tipoEnvio " +
            "LIKE %:tipoEnvio% OR pedido.formaPago LIKE %:formaPago% OR pedido.domicilioEntrega LIKE %:domicilioEntrega% OR pedido.fechaAlta LIKE %:fechaAlta% OR pedido.fechaModificacion LIKE %:fechaModificacion% OR pedido.fechaBaja %:fechaBaja%",
            nativeQuery = true)
    Page<Pedido> searchNativo(@Param("fechaPedido") Date fechaPedido, @Param("horaEstimadaFinalizacion") Date horaEstimadaFinalizacion, @Param("total") Number total, @Param("totalCosto") Number totalCosto, @Param("estado") EstadoPedido estado, @Param("tipoEnvio") TipoEnvio tipoEnvio,
                              @Param("formaPago") FormaPago formaPago, @Param("domicilioEntrega") Domicilio domicilioEntrega, @Param("fechaAlta") Date fechaAlta, @Param("fechaModificacion") Date fechaModificacion, @Param("fechaBaja") Date fechaBaja, Pageable pageable);

    @Query(value = "Select * FROM pedido WHERE pedido.persona_id  =:id",
            nativeQuery = true)
    List<Pedido> searchPedidos(@Param("id") Number id);

    @Query(value = "Select * FROM pedido WHERE pedido.persona_id  =:id",
            countQuery = "SELECT count(*) FROM pedido",
            nativeQuery = true)
    Page<Pedido> searchPedidos(@Param("id") Number id, Pageable pageable);


    @Query(value = "SELECT * FROM pedido WHERE pedido.Id LIKE %:filtro% ",
                nativeQuery = true)
        List<Pedido> searchNativoID(@Param("filtro") int filtro);

        @Query(value = "SELECT * FROM pedido WHERE pedido.Id LIKE %:filtro% ",
                countQuery = "SELECT count(*) FROM pedido",
                nativeQuery = true)
        Page<Pedido> searchNativoIDPageable(@Param("filtro") int filtro, Pageable pageable);

//ListaPedidosCliente
    @Query(value = "SELECT DISTINCT pedido.* FROM pedido " +
            "LEFT JOIN persona WHERE persona.legajo LIKE %:legajo% "
            , nativeQuery = true)
    List<Pedido> listaPedidosCliente(@Param("legajo") String legajo);


    @Query(value = "SELECT * FROM pedido WHERE pedido.estado LIKE %:estado% ",
            nativeQuery = true)
    List<Pedido> searchNativoPedidosEstado(@Param("estado") EstadoPedido estado, Pageable pageable);

    //@Query(value = "SELECT * FROM pedido WHERE pedi")

    //@Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :fechaInicio AND :fechaFin")
    //List<Pedido> pedidosEntreFechas(@Param("fechaInicio") java.util.Date fechaInicio, @Param("fechaFin") java.util.Date fechaFin);


}
