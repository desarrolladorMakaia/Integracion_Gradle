package com.manuel.proyectointegrador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private Integer cedula;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "celular")
    private String celular;
    @Column(name = "correo")
    private String correo;
    @Column(name = "direccion")
    private String direccionResidencial;
    @Column(name = "ciudad")
    private String ciudad;

    public Cliente() {
    }

    public Cliente(Integer cedula, String nombre, String apellido, String celular, String correo, String direccionResidencial, String ciudad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.direccionResidencial = direccionResidencial;
        this.ciudad = ciudad;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correoElectronico) {
        this.correo = correoElectronico;
    }

    public String getDireccionResidencial() {
        return direccionResidencial;
    }

    public void setDireccionResidencial(String direccionResidencial) {
        this.direccionResidencial = direccionResidencial;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
