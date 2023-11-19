package com.utn.sprint_4.dtos;

import com.utn.sprint_4.enumeraciones.Rol;


public class ListaClientesDTO {
    private String apellido;
    private String email;
    private String nombre;

    private String telefono;
    private Rol rol;

    public ListaClientesDTO(String apellido, String email, Rol rol, String nombre, String telefono ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;

    }

    public ListaClientesDTO() {
    }

    public String getNombreDTO() {return nombre;}

    public String getApellidoDTO() {
        return apellido;
    }

    public String getTelefonoDTO() {
        return telefono;
    }

    public String getEmail() {return email;}

    public Rol getRolDTO() {
        return rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {this.email = email;}

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}

