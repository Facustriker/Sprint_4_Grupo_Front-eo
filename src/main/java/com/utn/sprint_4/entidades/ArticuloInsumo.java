package com.utn.sprint_4.entidades;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "articulo_insumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticuloInsumo extends Base {

    @NotNull
    @Column(name = "denominacion")
    private String denominacion;

    @Column(name = "url_imagen")
    private String urlImagen;

    @NotNull
    @Column(name = "precio_compra", precision = 10, scale = 2)
    private int precioCompra;

    @NotNull
    @Column(name = "stock_actual", precision = 10, scale = 2)
    private int stockActual;

    @NotNull
    @Column(name = "stock_minimo", precision = 10, scale = 2)
    private int stockMinimo;

    @NotNull
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaModificacion;

    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date fechaBaja;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rubroArticulo_id")
    private RubroArticuloInsumo rubroArticuloInsumo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unidadDeMedida_id")
    private UnidadMedida unidadMedida;


   
}
