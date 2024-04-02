package com.manuel.proyectointegrador.Mapper;

import com.manuel.proyectointegrador.dto.PaqueteDTO;
import com.manuel.proyectointegrador.model.Paquete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaqueteMapper {

    PaqueteMapper INSTANCE = Mappers.getMapper(PaqueteMapper.class);

    PaqueteDTO paqueteToPaqueteDTO(Paquete paquete);

    @Mapping(target = "idPaquete", ignore = true)
    @Mapping(target = "tipoPaquete", ignore = true)
    Paquete paqueteDTOtoPaquete(PaqueteDTO paqueteDTO);
}
