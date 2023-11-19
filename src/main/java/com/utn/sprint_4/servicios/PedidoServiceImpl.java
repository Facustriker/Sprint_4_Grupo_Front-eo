package com.utn.sprint_4.servicios;

import com.utn.sprint_4.dtos.ListaPedidosClienteDTO;
import com.utn.sprint_4.dtos.ListaPedidosClienteFiltroDTO;
import com.utn.sprint_4.dtos.MovimientosMonetariosDTO;
import com.utn.sprint_4.dtos.RankingPersonasDTO;
import com.utn.sprint_4.entidades.Domicilio;
import com.utn.sprint_4.entidades.Pedido;
import com.utn.sprint_4.enumeraciones.EstadoPedido;
import com.utn.sprint_4.enumeraciones.FormaPago;
import com.utn.sprint_4.enumeraciones.TipoEnvio;
import com.utn.sprint_4.repositorios.BaseRepository;
import com.utn.sprint_4.repositorios.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl extends BaseServiceImpl<Pedido,Long> implements PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;


    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {

        super(baseRepository);
    }

    @Override
    public List<Pedido> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja) throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.searchNativo(fechaPedido, horaEstimadaFinalizacion, total, totalCosto, estado, tipoEnvio, formaPago, domicilioEntrega, fechaAlta, fechaModifciacion, fechaBaja);
            return pedidos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Pedido> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja, Pageable pageable) throws Exception {
        try {
            Page<Pedido> pedidos = pedidoRepository.searchNativo(fechaPedido, horaEstimadaFinalizacion, total, totalCosto, estado, tipoEnvio, formaPago, domicilioEntrega, fechaAlta, fechaModifciacion, fechaBaja, pageable);
            return pedidos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pedido> searchID(int filtro) throws Exception {
        try {
            List<Pedido> pedidos = pedidoRepository.searchNativoID(filtro);
            return pedidos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Pedido> searchIDPageable(int filtro, Pageable pageable) throws Exception {
        try {
            Page<Pedido> pedidos = pedidoRepository.searchNativoIDPageable(filtro, pageable);
            return pedidos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Pedido> searchPedidos(Number id) throws Exception {
        try{
            List<Pedido> pedidos = pedidoRepository.searchPedidos(id);
            return pedidos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Pedido> searchPedidos(Number id, Pageable pageable) throws Exception {
        try{
            Page<Pedido> pedidos = pedidoRepository.searchPedidos(id, pageable);
            return pedidos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

     //Lista Pedidos Cliente
    @Override
    public List<ListaPedidosClienteDTO> listaPedidosCliente(ListaPedidosClienteFiltroDTO listaPedidosClienteFiltroDTO) throws Exception {
        try{
            List<Pedido> pedidos = pedidoRepository.listaPedidosCliente(listaPedidosClienteFiltroDTO.getLegajo());
            List<ListaPedidosClienteDTO> listaPedidosDTO = new ArrayList<>();
            for (Pedido pedido: pedidos) {
                ListaPedidosClienteDTO auxDTO = new ListaPedidosClienteDTO();
                auxDTO.setFechaPedido(pedido.getFechaPedido());
                auxDTO.setNroPedido(pedido.getNroPedido());
                auxDTO.setTotal(pedido.getTotal());
                listaPedidosDTO.add(auxDTO);
            }
            return  listaPedidosDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }





    @Override
    public List<Pedido> searchNativoPedidosEstado(EstadoPedido estado, Pageable pageable) throws Exception {
        try{
            List<Pedido> pedidos = pedidoRepository.searchNativoPedidosEstado(estado, pageable);
            return pedidos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //Movimientos monetarios
    public int calcularIngreso(Pedido pedido) {
        int ingreso = pedido.getTotal() - pedido.getTotalCosto();
        return ingreso;
    }

    @Transactional
    public List<MovimientosMonetariosDTO> buscarMovimientosMonetarios (MovimientosMonetariosDTO movimientosMonetariosDTO) throws Exception {
        List<MovimientosMonetariosDTO> movimientosMonetarios = new ArrayList<>();

        try {
            List<Pedido> pedidos = pedidoRepository.findAll();

            for (Pedido pedido : pedidos) {
                MovimientosMonetariosDTO movimientoMonetario = new MovimientosMonetariosDTO();
                movimientoMonetario.setNroPedido(pedido.getId().intValue());
                movimientoMonetario.setTotal(pedido.getTotal());
                movimientoMonetario.setTotalCosto(pedido.getTotalCosto());

                // Calcula el Costo Total con la función calcularIngreso
                int ingreso = calcularIngreso(pedido);
                movimientoMonetario.setIngreso(ingreso);

                movimientosMonetarios.add(movimientoMonetario);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return movimientosMonetarios;
    }
    //@Override
    //@Transactional
    //public List<Pedido> PedidosEntreFechas (PedidosEntreFechasDTO pedidosEntreFechasDTO) throws Exception {
    //    try {
    //        List<Pedido> pedidosEntreFechas = pedidoRepository.pedidosEntreFechas(pedidosEntreFechasDTO.getFechaInicio(), pedidosEntreFechasDTO.getFechaFin());
    //        return pedidosEntreFechas;
    //    } catch (Exception e) {
    //        throw new Exception(e.getMessage());
    //    }
    //}
}
