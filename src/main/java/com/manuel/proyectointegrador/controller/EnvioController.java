package com.manuel.proyectointegrador.controller;

import com.manuel.proyectointegrador.dto.EnvioDTO;
import com.manuel.proyectointegrador.dto.EnvioResponseDTO;
import com.manuel.proyectointegrador.model.Envio;
import com.manuel.proyectointegrador.service.EnvioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")

public class EnvioController {
    EnvioService envioService;
    @Autowired
    public void EnvioController(EnvioService envioService){
        this.envioService = envioService;
    }


    @PostMapping("/envio")
    public EnvioResponseDTO crearEnvio(@RequestBody EnvioDTO envio){
        return this.envioService.crearEnvio(envio);
    }


    @GetMapping("/envio/{numeroGuia}")
    public EnvioDTO obtenerEnvio(@PathVariable("numeroGuia") int numeroGuia){
        return this.envioService.buscarEnvio(numeroGuia);
    }


    @PatchMapping("/envio")
    public EnvioResponseDTO actualizarEstadoEnvio(@RequestParam("guia") Integer numGuia, @RequestParam("estado") String estadoEnvio, @RequestParam("empleado") Integer cedulaEmpleado){
        return this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estadoEnvio);
    }


    @GetMapping("/envio")
    public List<EnvioDTO> filtrarEnvios(@RequestParam("estado") String estadoEnvio, @RequestParam("empleado") Integer cedulaEmpleado){
        //tener en cuenta las validaciones.
        return this.envioService.filtrar(estadoEnvio,cedulaEmpleado);
    }


    public List<EnvioDTO> envios(){
        return this.envioService.retornarEnvios();
    }

}
