package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.ListaPedidosClienteDTO;
import com.utn.sprint_4.dtos.ListaPedidosClienteFiltroDTO;
import com.utn.sprint_4.dtos.RankingPersonasDTO;
import com.utn.sprint_4.entidades.Domicilio;
import com.utn.sprint_4.entidades.Pedido;
import com.utn.sprint_4.enumeraciones.EstadoPedido;
import com.utn.sprint_4.enumeraciones.FormaPago;
import com.utn.sprint_4.enumeraciones.TipoEnvio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface PedidoService extends BaseService<Pedido,Long>{

    List<Pedido> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja) throws Exception;
    Page<Pedido> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja, Pageable pageable) throws Exception;
    List<Pedido> searchID(int filtro) throws Exception;
    Page<Pedido> searchIDPageable(int filtro, Pageable pageable) throws Exception;

    List<Pedido> searchPedidos(Number id) throws Exception;

    Page<Pedido> searchPedidos(Number id, Pageable pageable) throws Exception;

    List<ListaPedidosClienteDTO> listaPedidosCliente(ListaPedidosClienteFiltroDTO listaPedidosClienteFiltroDTO) throws Exception;

    List<Pedido> searchNativoPedidosEstado(EstadoPedido estado, Pageable pageable) throws Exception;

    List<MovimientosMonetariosDTO> buscarMovimientosMonetarios(MovimientosMonetariosDTO movimientosMonetariosDTO) throws Exception;
List<Pedido> PedidosEntreFechas(PedidosEntreFechasDTO pedidosEntreFechasDTO) throws Exception;

}
