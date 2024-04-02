package com.manuel.proyectointegrador.dto;

public class PaqueteDTO {
    private Integer peso;
    private double valorDeclarado;

    public PaqueteDTO() {
    }

    public PaqueteDTO(Integer peso, double valorDeclarado) {
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
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
