package com.manuel.proyectointegrador;

import com.manuel.proyectointegrador.dto.EnvioDTO;
import com.manuel.proyectointegrador.dto.EnvioResponseDTO;
import com.manuel.proyectointegrador.exception.ApiRequestException;
import com.manuel.proyectointegrador.model.Cliente;
import com.manuel.proyectointegrador.model.Empleado;
import com.manuel.proyectointegrador.model.Envio;
import com.manuel.proyectointegrador.model.Paquete;
import com.manuel.proyectointegrador.repository.ClienteRepository;
import com.manuel.proyectointegrador.repository.EmpleadoRepository;
import com.manuel.proyectointegrador.repository.EnvioRepository;
import com.manuel.proyectointegrador.repository.PaqueteRepository;
import com.manuel.proyectointegrador.service.EnvioService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnvioServiceTest {

    EnvioRepository envioRepository;
    ClienteRepository clienteRepository;
    EmpleadoRepository empleadoRepository;
    PaqueteRepository paqueteRepository;
    EnvioService envioService;

    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.empleadoRepository = mock(EmpleadoRepository.class);
        this.envioRepository = mock(EnvioRepository.class);
        this.paqueteRepository = mock(PaqueteRepository.class);

        this.envioService = new EnvioService(this.envioRepository,this.clienteRepository,this.empleadoRepository,this.paqueteRepository);

    }

    @Test
    public void crearEnvio(){
        EnvioDTO envioDTO = new EnvioDTO(
                123,
                "medellin",
                "bogota",
                "carrera80",
                "Juan Manuel",
                "3000000",
                999.0,
                2
        );
        when(clienteRepository.findById(123)).thenReturn(Optional.of(new Cliente()));

        EnvioResponseDTO respuesta = this.envioService.crearEnvio(envioDTO);

        assertTrue(respuesta.getEstadoEnvio().equals("RECIBIDO"));
    }
    @Test(expected = ApiRequestException.class)
    public void crearEnvioSinUnDato(){
        EnvioDTO envioDTO = new EnvioDTO(
                123,
                null,
                "bogota",
                "carrera80",
                "Juan Manuel",
                "3000000",
                999.0,
                2
        );
        when(clienteRepository.findById(any())).thenReturn(Optional.of(new Cliente()));

        this.envioService.crearEnvio(envioDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearEnvioSinCliente(){
        EnvioDTO envioDTO = new EnvioDTO(
                123,
                "medellin",
                "bogota",
                "carrera80",
                "Juan Manuel",
                "3000000",
                999.0,
                2
        );
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());

        this.envioService.crearEnvio(envioDTO);
    }

    @Test
    public void buscarEnvio(){
        Integer numGuia = 2;
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "estado",
                999.0,
                paquete
            );
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        EnvioDTO envioDTO = this.envioService.buscarEnvio(numGuia);

        assertTrue(envioDTO.getCedulaCliente().equals(123));
        assertTrue(envioDTO.getCiudadOrigen().equals("medellin"));
        assertTrue(envioDTO.getCiudadDestino().equals("bogota"));
        assertTrue(envioDTO.getNombreRecibe().equals("juan"));
        assertTrue(envioDTO.getNumRecibe().equals("333"));
        assertTrue(envioDTO.getValorDeclaradoPaquete().equals(999.0));
        assertTrue(envioDTO.getPeso().equals(1));

    }

    @Test(expected = ApiRequestException.class)
    public void buscarEnvioNoExistente(){
        Integer numGuia = 1;
        when(envioRepository.findById(any())).thenReturn(Optional.empty());
        this.envioService.buscarEnvio(numGuia);
    }

    @Test
    public void actualizarEstado(){
        Integer numGuia = 1;
        Integer cedulaEmpleado = 123;
        String estado = "EN RUTA";
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Empleado empleado = new Empleado(
                123,
                "juan manuel",
                "restrepo",
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "RECIBIDO",
                999.0,
                paquete
        );
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepository.findById(any())).thenReturn(Optional.of(empleado));
        EnvioResponseDTO respuesta = this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estado);

        assertTrue(respuesta.getEstadoEnvio().equals("EN RUTA"));

    }
    @Test(expected = ApiRequestException.class)
    public void actualizarEstadoEntregado(){
        Integer numGuia = 1;
        Integer cedulaEmpleado = 123;
        String estado = "EN RUTA";
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Empleado empleado = new Empleado(
                123,
                "juan manuel",
                "restrepo",
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "ENTREGADO",
                999.0,
                paquete
        );
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepository.findById(any())).thenReturn(Optional.of(empleado));

        this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estado);
    }
    @Test(expected = ApiRequestException.class)
    public void actualizarEstadoGuiaNoExistente(){
        Integer numGuia = 1;
        Integer cedulaEmpleado = 123;
        String estado = "EN RUTA";
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Empleado empleado = new Empleado(
                123,
                "juan manuel",
                "restrepo",
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );
        when(envioRepository.findById(any())).thenReturn(Optional.empty());
        when(empleadoRepository.findById(any())).thenReturn(Optional.of(empleado));

        this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estado);
    }
    @Test(expected = ApiRequestException.class)
    public void actualizarEmpleadoNoValido(){
        Integer numGuia = 1;
        Integer cedulaEmpleado = 123;
        String estado = "EN RUTA";
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Empleado empleado = new Empleado(
                123,
                "juan manuel",
                "restrepo",
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "no valido"
        );
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "RECIBIDO",
                999.0,
                paquete
        );
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepository.findById(any())).thenReturn(Optional.of(empleado));

        this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estado);
    }
    @Test(expected = ApiRequestException.class)
    public void actualizarEmpleadoNoExistente(){
        Integer numGuia = 1;
        Integer cedulaEmpleado = 123;
        String estado = "EN RUTA";
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "RECIBIDO",
                999.0,
                paquete
        );
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepository.findById(any())).thenReturn(Optional.empty());

        this.envioService.actualizarEstado(numGuia,cedulaEmpleado,estado);
    }
    @Test
    public void filtrarEnvios(){
        String estado = "RECIBIDO";
        Integer cedulaEmpleado = 123;
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "RECIBIDO",
                999.0,
                paquete
        );
        List<Envio> envios = new ArrayList<>();
        envios.add(envio);
        envios.add(envio);
        envios.add(envio);
        when(empleadoRepository.findById(any())).thenReturn(Optional.of(new Empleado()));
        when(envioRepository.findAll()).thenReturn(envios);

        List<EnvioDTO> enviosDTO =  this.envioService.filtrar(estado,cedulaEmpleado);

        assertTrue(!enviosDTO.isEmpty());
    }
    @Test(expected = ApiRequestException.class)
    public void filtrarEmpleadoNoExiste(){
        String estado = "RECIBIDO";
        Integer cedulaEmpleado = 123;
        Cliente cliente = new Cliente();
        cliente.setCedula(123);
        Paquete paquete = new Paquete();
        paquete.setValorDeclarado(999.0);
        paquete.setPeso(1);
        Envio envio = new Envio(
                cliente,
                "medellin",
                "bogota",
                "direccion",
                "juan",
                "333",
                "hora",
                "RECIBIDO",
                999.0,
                paquete
        );
        List<Envio> envios = new ArrayList<>();
        envios.add(envio);
        envios.add(envio);
        envios.add(envio);
        when(empleadoRepository.findById(any())).thenReturn(Optional.empty());
        when(envioRepository.findAll()).thenReturn(envios);

        this.envioService.filtrar(estado,cedulaEmpleado);
    }
}
