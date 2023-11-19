package com.utn.sprint_4.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.utn.sprint_4.enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario extends Base {

    @NotNull
    @Column(unique = true)
    private String auth0Id;

    @NotNull
    @Column(name = "username", unique = true, nullable = false)
    private String username;

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


}
