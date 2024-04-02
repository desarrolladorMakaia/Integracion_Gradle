package com.manuel.proyectointegrador.controller;

import com.manuel.proyectointegrador.dto.ClienteDTO;
import com.manuel.proyectointegrador.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.manuel.proyectointegrador.model.Cliente;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/v1")
@Api(tags = "Cliente", description = "Controller Cliente")
public class ClienteController {
    ClienteService clienteService;
    @Autowired
    public void ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }



    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Registrar un cliente" , notes = "Se recibe por el body un objeto de clienteDTO y este se registra en la base de datos.")
    @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Accion exitosa "),
           @ApiResponse(code = 400, message = "Bad Request, Algo ingresaste mal verifica la informacion"),
           @ApiResponse(code = 201, message = "Creado exitosamente"),
           @ApiResponse(code = 301, message = "Credenciales erroneas o permisos no otorgados"),
           @ApiResponse(code = 500, message = "Error inesperado del sistema." )
    })
    @PostMapping("/cliente")
    public ClienteDTO crearCliente(@RequestBody ClienteDTO cliente){
        return this.clienteService.crearCliente(cliente);
    }



    @ApiIgnore
    @PreAuthorize("hasRole('WRITE')")
    @ApiOperation(value = "Eliminar un cliente" , notes = "Se recibe por la url la cedula del cliente y se elimina este de de la base de datos ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se elimino de manera correcta el cliente "),
            @ApiResponse(code = 400, message = "Bad Request, Algo ingresaste mal verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema." )
    })
    @DeleteMapping("/cliente/{cedula}")
    public String eliminarCliente(@PathVariable("cedula") int cedula){
        this.clienteService.eliminarCliente(cedula);
        return "Se elimino correctamente";
    }





    @PreAuthorize("hasRole('READ')")
    @ApiOperation(value = "Obtener un cliente por cedula" , notes = "Se recibe por la url la cedula del cliente y se devuelve la informacion del cliente en caso de estar registrado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se obtuvo de manera exitosa el cliente "),
            @ApiResponse(code = 400, message = "Bad Request, Algo ingresaste mal verifica la informacion"),
            @ApiResponse(code = 500, message = "Error inesperado del sistema." )
    })
    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable("cedula") int cedula){

        ClienteDTO clientedto = this.clienteService.obtenerCliente(cedula);
        return new ResponseEntity<ClienteDTO>(clientedto,HttpStatus.OK);
    }

}
