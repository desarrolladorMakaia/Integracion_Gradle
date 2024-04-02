package com.manuel.proyectointegrador.Mapper;

import com.manuel.proyectointegrador.dto.EmpleadoDTO;
import com.manuel.proyectointegrador.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmpleadoMapper {

    EmpleadoMapper INSTANCE = Mappers.getMapper(EmpleadoMapper.class);

    EmpleadoDTO empleadoToEmpleadoDTO(Empleado empleado);

    Empleado empleadoDTOtoEmpleado(EmpleadoDTO empleadoDTO);
}
