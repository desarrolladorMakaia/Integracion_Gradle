package com.manuel.proyectointegrador.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    private int cedula;
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
    @Column(name = "antiguedad")
    private Integer antiguedad;
    @Column(name = "rh")
    private String rh;
    @Column(name = "tipo")
    private String tipoEmpleado;

    public Empleado() {
    }

    public Empleado(int cedula, String nombre, String apellido, String celular, String correo, String direccionResidencial, String ciudad, Integer antiguedad, String rh, String tipoEmpleado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.direccionResidencial = direccionResidencial;
        this.ciudad = ciudad;
        this.antiguedad = antiguedad;
        this.rh = rh;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
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

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }
}
