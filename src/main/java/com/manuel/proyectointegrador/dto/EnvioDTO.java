package com.manuel.proyectointegrador.dto;

import com.manuel.proyectointegrador.model.Cliente;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "Envio")
public class EnvioDTO {
    private Integer cedulaCliente;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String direccionDestino;
    private String nombreRecibe;
    private String numRecibe;
    private Double valorDeclaradoPaquete;
    private Integer peso;
    private Double valorEnvio;
    private String estadoEnvio;
    private Integer numGuia;

    public EnvioDTO() {
    }

    public EnvioDTO(Integer cedulaCliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombreRecibe, String numRecibe, Double valorDeclaradoPaquete, Integer peso) {
        this.cedulaCliente = cedulaCliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombreRecibe = nombreRecibe;
        this.numRecibe = numRecibe;
        this.valorDeclaradoPaquete = valorDeclaradoPaquete;
        this.peso = peso;
    }

    public EnvioDTO(Integer cedulaCliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombreRecibe, String numRecibe, Double valorDeclaradoPaquete, Integer peso, Double valorEnvio, String estadoEnvio, Integer numGuia) {
        this.cedulaCliente = cedulaCliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombreRecibe = nombreRecibe;
        this.numRecibe = numRecibe;
        this.valorDeclaradoPaquete = valorDeclaradoPaquete;
        this.peso = peso;
        this.valorEnvio = valorEnvio;
        this.estadoEnvio = estadoEnvio;
        this.numGuia = numGuia;
    }

    public Integer getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(Integer cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getNombreRecibe() {
        return nombreRecibe;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public String getNumRecibe() {
        return numRecibe;
    }

    public void setNumRecibe(String numRecibe) {
        this.numRecibe = numRecibe;
    }

    public Double getValorDeclaradoPaquete() {
        return valorDeclaradoPaquete;
    }

    public void setValorDeclaradoPaquete(Double valorDeclaradoPaquete) {
        this.valorDeclaradoPaquete = valorDeclaradoPaquete;
    }

    public Double getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(Double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }
}
