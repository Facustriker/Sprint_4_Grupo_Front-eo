package com.utn.sprint_4.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PedidosEntreFechasDTO {
    Date fechaInicio;
    Date fechaFin;

    public PedidosEntreFechasDTO(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public PedidosEntreFechasDTO() {
    }
}
