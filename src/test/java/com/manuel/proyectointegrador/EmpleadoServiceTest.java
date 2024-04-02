package com.manuel.proyectointegrador;

import com.manuel.proyectointegrador.dto.EmpleadoDTO;
import com.manuel.proyectointegrador.exception.ApiRequestException;
import com.manuel.proyectointegrador.repository.EmpleadoRepository;
import com.manuel.proyectointegrador.service.EmpleadoService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class EmpleadoServiceTest {

    EmpleadoRepository empleadoRepository;
    EmpleadoService empleadoService;

    @Before
    public void setUp(){
        this.empleadoRepository = mock(EmpleadoRepository.class);
        this.empleadoService = new EmpleadoService(empleadoRepository);
    }

    @Test
    public void crearEmpleado(){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(
                123,
                "juan manuel",
                "restrepo",
                "302",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );
        EmpleadoDTO empleado = this.empleadoService.crearEmpleado(empleadoDTO);

        assertTrue(empleado.getCedula().equals(123));
        assertTrue(empleado.getNombre().equals("juan manuel"));
        assertTrue(empleado.getApellido().equals("restrepo"));
        assertTrue(empleado.getCelular().equals("302"));
        assertTrue(empleado.getCorreo().equals("example@hotmail.com"));
        assertTrue(empleado.getDireccionResidencial().equals("carrera10"));
        assertTrue(empleado.getCiudad().equals("Medellin"));
        assertTrue(empleado.getAntiguedad().equals(12));
        assertTrue(empleado.getRh().equals("o+"));
        assertTrue(empleado.getTipoEmpleado().equals("COORDINADOR"));
    }
    @Test(expected = ApiRequestException.class)
    public void crearEmpleadoSinNombre(){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(
                123,
                null,
                "restrepo",
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );

        this.empleadoService.crearEmpleado(empleadoDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearEmpleadoSinApellido(){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(
                123,
                "juan manuel",
                null,
                "3024261812",
                "example@hotmail.com",
                "carrera10",
                "Medellin",
                12,
                "o+",
                "COORDINADOR"
        );

        this.empleadoService.crearEmpleado(empleadoDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crearEmpleadoSinCedula(){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(
                null,
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

        this.empleadoService.crearEmpleado(empleadoDTO);
    }
}
