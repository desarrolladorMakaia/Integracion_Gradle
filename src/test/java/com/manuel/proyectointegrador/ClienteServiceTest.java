package com.manuel.proyectointegrador;

import com.manuel.proyectointegrador.dto.ClienteDTO;
import com.manuel.proyectointegrador.exception.ApiRequestException;
import com.manuel.proyectointegrador.model.Cliente;
import com.manuel.proyectointegrador.repository.ClienteRepository;
import com.manuel.proyectointegrador.service.ClienteService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ClienteServiceTest {

    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(this.clienteRepository);
    }

    @Test
    public void crearCliente(){
        ClienteDTO clienteDTO =
                new ClienteDTO(
                        123,
                        "juan manuel",
                        "restrepo",
                        "302",
                        "example@hotmail.com",
                        "carrera10",
                        "Medellin"
                );
        ClienteDTO cliente = this.clienteService.crearCliente(clienteDTO);

        assertTrue(cliente.getCedula().equals(123));
        assertTrue(cliente.getNombre().equals("juan manuel"));
        assertTrue(cliente.getApellido().equals("restrepo"));
        assertTrue(cliente.getCelular().equals("302"));
        assertTrue(cliente.getCorreo().equals("example@hotmail.com"));
        assertTrue(cliente.getDireccionResidencial().equals("carrera10"));
        assertTrue(cliente.getCiudad().equals("Medellin"));
    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinNombre(){
        ClienteDTO clienteDTO =
                new ClienteDTO(
                        123,
                        null,
                        "restrepo",
                        "3024261812",
                        "example@hotmail.com",
                        "carrera10",
                        "Medellin"
                );

        this.clienteService.crearCliente(clienteDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinApellido(){
        ClienteDTO clienteDTO =
                new ClienteDTO(
                        123,
                        "juan manuel",
                        null,
                        "3024261812",
                        "example@hotmail.com",
                        "carrera10",
                        "Medellin"
                );

        this.clienteService.crearCliente(clienteDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearClienteSinCedula(){
        ClienteDTO clienteDTO =
                new ClienteDTO(
                        null,
                        "juan manuel",
                        "restrepo",
                        "3024261812",
                        "example@hotmail.com",
                        "carrera10",
                        "Medellin"
                );

        this.clienteService.crearCliente(clienteDTO);
    }
}
