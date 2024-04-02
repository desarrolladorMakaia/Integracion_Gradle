package com.manuel.proyectointegrador.controller;

import com.manuel.proyectointegrador.dto.EmpleadoDTO;
import com.manuel.proyectointegrador.service.EmpleadoService;
import com.manuel.proyectointegrador.model.Empleado;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")

public class EmpleadoController {
    EmpleadoService empleadoService;


    @Autowired public void EmpleadoController(EmpleadoService empleadoService){
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleado")
    public EmpleadoDTO crearEmpleado(@RequestBody EmpleadoDTO empleado){
        this.empleadoService.crearEmpleado(empleado);
        return empleado;
    }

    @DeleteMapping("/empleado/{cedula}")
    public String eliminarEmpleado(@PathVariable("cedula") int cedula){
        this.eliminarEmpleado(cedula);
        return "Empleado eliminado";
    }

    @GetMapping("/empleado/{cedula}")
    public EmpleadoDTO obtenerEmpleado(@PathVariable("cedula") int cedula){
        return this.empleadoService.getEmpleadoPorCedula(cedula);
    }
}
