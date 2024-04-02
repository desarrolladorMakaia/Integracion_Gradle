package com.manuel.proyectointegrador.model;

import javax.persistence.*;

@Entity
@Table(name = "envios")
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer numeroGuia;
    @OneToOne
    @JoinColumn(name = "cedula")
    private Cliente cliente;
    @Column(name = "origen")
    private String ciudadOrigen;
    @Column(name = "destino")
    private String ciudadDestino;
    @Column(name = "direccion")
    private String direccionDestino;
    @Column(name = "receptor")
    private String nombreRecibe;
    @Column(name = "celular")
    private String numRecibe;
    @Column(name = "hora")
    private String horaEntrega;
    @Column(name = "estado")
    private String estadoEnvio;
    @Column(name = "valor")
    private Double valorEnvio;
    @OneToOne
    @JoinColumn(name = "idPaquete")
    private Paquete paquete;

    public Envio() {
    }

    public Envio(Cliente cliente, String ciudadOrigen, String ciudadDestino, String direccionDestino, String nombreRecibe, String numeroRecibe, String horaEntrega, String estadoEnvio, Double valorEnvio, Paquete paquete) {
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.direccionDestino = direccionDestino;
        this.nombreRecibe = nombreRecibe;
        this.numRecibe = numeroRecibe;
        this.horaEntrega = horaEntrega;
        this.estadoEnvio = estadoEnvio;
        this.valorEnvio = valorEnvio;
        this.paquete = paquete;
    }

    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public void setNumRecibe(String numeroRecibe) {
        this.numRecibe = numeroRecibe;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Double getValorEnvio() {
        return valorEnvio;
    }

    public void setValorEnvio(Double valorEnvio) {
        this.valorEnvio = valorEnvio;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public String toString() {
        return "{\n" +
                "numeroGuia=" + numeroGuia +
                "\nestadoEnvio='" + estadoEnvio + "'\n" +
                '}';
    }
}
