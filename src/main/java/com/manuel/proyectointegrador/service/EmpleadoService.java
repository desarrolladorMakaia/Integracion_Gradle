package com.manuel.proyectointegrador.service;

import com.manuel.proyectointegrador.Mapper.EmpleadoMapper;
import com.manuel.proyectointegrador.dto.EmpleadoDTO;
import com.manuel.proyectointegrador.exception.ApiRequestException;
import com.manuel.proyectointegrador.model.Empleado;
import com.manuel.proyectointegrador.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO){
        if(empleadoDTO.getNombre()==null){
            throw new ApiRequestException("Se requiere el nombre");
        } else if(empleadoDTO.getApellido()==null){
            throw new ApiRequestException("Se requiere el apellido");
        } else if(empleadoDTO.getCedula()==null){
            throw new ApiRequestException("Se requiere una identificacion valida");
        }
        List<Integer> cedulasEmpleados = new ArrayList<>();
        this.empleadoRepository.findAll()
                .stream()
                .forEach(empleado1 -> cedulasEmpleados.add(empleado1.getCedula()));
        if(cedulasEmpleados.contains(empleadoDTO.getCedula())){
            throw new ApiRequestException("La cedula "+empleadoDTO.getCedula()+" ya se encuentra registrada");
        }

        Empleado empleado = EmpleadoMapper.INSTANCE.empleadoDTOtoEmpleado(empleadoDTO);
        empleado.setAntiguedad(empleadoDTO.getAntiguedad());
        this.empleadoRepository.save(empleado);
        return empleadoDTO;
    }

    public EmpleadoDTO getEmpleadoPorCedula(Integer cedula){
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedula);
        if(!empleado.isPresent()){
            throw new ApiRequestException("La cedula "+cedula+" no se encuentra registrada");
        }
        EmpleadoDTO empleadoDTO = EmpleadoMapper.INSTANCE.empleadoToEmpleadoDTO(empleado.get());
        return empleadoDTO;
    }

    public void eliminarEmpleado(Integer cedula){
        this.empleadoRepository.deleteById(cedula);
    }
}
