package com.utn.sprint_4.entidades;


import com.fasterxml.jackson.annotation.*;
import com.utn.sprint_4.enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Factura extends Base {

    @NotNull
    @Column(name = "fechaFacturacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFacturacion;

    @Column(name = "paymentid")
    private Long paymentid;

    @Column(name = "merchantorder")
    private Long merchantorder;

    @Column(name = "preferenceid")
    private String preferenceid;

    @Column(name = "paymenttype")
    private String paymenttype;

    @NotNull
    @Column(name = "formapago")
    private FormaPago formapago;

    @NotNull
    @Column(name = "totalventa", precision = 10, scale = 2)
    private int totalventa;

    @NotNull
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaAlta;

    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaModificacion;

    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaBaja;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Builder.Default
    @JoinColumn(name = "factura_id")
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();

    public void AgregarDetalleFacturas(DetalleFactura d){
        detalleFacturas.add(d);
    }

}