package com.manuel.proyectointegrador.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "EnvioResponse")
public class EnvioResponseDTO {

    private Integer numeroGuia;
    private String estadoEnvio;

    public EnvioResponseDTO(Integer numeroGuia, String estadoEnvio) {
        this.numeroGuia = numeroGuia;
        this.estadoEnvio = estadoEnvio;
    }

    public Integer getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(Integer numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }
}
