package com.utn.sprint_4.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class MovimientosMonetariosDTO {
    int nroPedido;
    int total;
    int totalCosto;
    int ingreso = total-totalCosto;


    public MovimientosMonetariosDTO(int nroPedido, int precioCompra, int totalventa) {
        this.nroPedido = nroPedido;
        this.total = total;
        this.totalCosto = totalCosto;
        this.ingreso = ingreso;
    }

    public MovimientosMonetariosDTO() {
    }
}
