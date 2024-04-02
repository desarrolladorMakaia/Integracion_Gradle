package com.manuel.proyectointegrador.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Empleado")
public class EmpleadoDTO {

    private Integer cedula;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String direccionResidencial;
    private String ciudad;
    private Integer antiguedad;
    private String rh;
    private String tipoEmpleado;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(Integer cedula, String nombre, String apellido, String celular, String correo, String direccionResidencial, String ciudad, Integer antiguedad, String rh, String tipoEmpleado) {
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

    public void setCorreo(String correo) {
        this.correo = correo;
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
