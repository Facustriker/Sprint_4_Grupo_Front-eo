package com.utn.sprint_4.entidades;

import com.fasterxml.jackson.annotation.*;
import com.utn.sprint_4.enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persona")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona extends Base implements UserDetails {

    @Column(name = "nombre")
    @Temporal(TemporalType.TIMESTAMP)
    private String nombre;

    @Column(name = "apellido")
    @Temporal(TemporalType.TIMESTAMP)
    private String apellido;

    @Column(name = "rol")
    @Temporal(TemporalType.TIMESTAMP)
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "email")
    @Temporal(TemporalType.TIMESTAMP)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefono")
    @Temporal(TemporalType.TIMESTAMP)
    private String telefono;

    @NotNull
    @Column(name = "legajo", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String legajo;

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

    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    //Relacion Persona -1-------1-> Usuario
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    //Relacion Persona -1-------n-> Pedido
    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @JoinColumn(name = "persona_id")
    private List<Pedido> pedidos = new ArrayList<>();

    //Relacion Persona -1-----n->Domicilio
    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @JoinColumn(name = "persona_id")
    private List<Domicilio> domicilios = new ArrayList<>();

    public void AgregarPedidos(Pedido p){
        pedidos.add(p);
    }

    public void AgregarDomicilios(Domicilio d){
        domicilios.add(d);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetails orElseThrow() {
        return null;
    }
}
