package com.utn.sprint_4.controladores;

import com.utn.sprint_4.dtos.ListaPedidosClienteFiltroDTO;
import com.utn.sprint_4.entidades.Domicilio;
import com.utn.sprint_4.entidades.Pedido;
import com.utn.sprint_4.enumeraciones.EstadoPedido;
import com.utn.sprint_4.enumeraciones.FormaPago;
import com.utn.sprint_4.enumeraciones.TipoEnvio;
import com.utn.sprint_4.servicios.PedidoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl>{

    @GetMapping("/search")
    public ResponseEntity<?> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(fechaPedido, horaEstimadaFinalizacion, total, totalCosto, estado, tipoEnvio, formaPago, domicilioEntrega, fechaAlta, fechaModifciacion, fechaBaja));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<?> search(Date fechaPedido, Date horaEstimadaFinalizacion, Number total, Number totalCosto, EstadoPedido estado, TipoEnvio tipoEnvio, FormaPago formaPago, Domicilio domicilioEntrega, Date fechaAlta, Date fechaModifciacion, Date fechaBaja, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(fechaPedido, horaEstimadaFinalizacion, total, totalCosto, estado, tipoEnvio, formaPago, domicilioEntrega, fechaAlta, fechaModifciacion, fechaBaja, pageable));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
    @GetMapping("/searchByID")
    public ResponseEntity<?> search(@RequestParam int filtro){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchID(filtro));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchByIDPaged")
    public ResponseEntity<?> search(@RequestParam int filtro, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchIDPageable(filtro, pageable));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchPedidos")
    public ResponseEntity<?> searchPedidos(@RequestParam Number id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPedidos(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/searchPagedPedidos")
    public ResponseEntity<?> searchPedidos(@RequestParam Number id, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchPedidos(id, pageable));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

//Lista Pedidos Cliente

    @GetMapping("/listaPedidosCliente")
    public ResponseEntity<?> listaPedidosCliente(ListaPedidosClienteFiltroDTO listaPedidosClienteFiltroDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.listaPedidosCliente(listaPedidosClienteFiltroDTO));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }



    @GetMapping("/listaPedidosEstado")
    public ResponseEntity<?> searchNativoPedidosEstado(@RequestParam EstadoPedido estado, Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchNativoPedidosEstado(estado, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
    @GetMapping("/movimientoMonetario")
    public ResponseEntity<?> buscarMovimientosMonetarios(@RequestBody MovimientosMonetariosDTO movimientosMonetariosDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarMovimientosMonetarios(movimientosMonetariosDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
    //@GetMapping("/pedidoEntreFechas")
    //public ResponseEntity<?> pedidosEntreFechas(@RequestBody PedidosEntreFechasDTO pedidosEntreFechasDTO){
    //    try{
    //        return ResponseEntity.status(HttpStatus.OK).body(servicio.PedidosEntreFechas(pedidosEntreFechasDTO));
    //    }catch (Exception e) {
    //        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
    //    }
    //}

}
