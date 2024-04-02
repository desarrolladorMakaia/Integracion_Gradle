package com.manuel.proyectointegrador.model;

import javax.persistence.*;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPaquete;
    @Column(name = "tipo")
    private String tipoPaquete;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "valor")
    private double valorDeclarado;

    public Paquete() {
    }

    public Paquete(String tipoPaquete, Integer peso, double valorDeclarado) {
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(String tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }
}
