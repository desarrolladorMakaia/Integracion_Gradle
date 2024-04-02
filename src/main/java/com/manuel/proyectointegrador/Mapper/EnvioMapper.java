package com.manuel.proyectointegrador.Mapper;

import com.manuel.proyectointegrador.dto.EnvioDTO;
import com.manuel.proyectointegrador.model.Envio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnvioMapper {

    EnvioMapper INSTANCE = Mappers.getMapper(EnvioMapper.class);


    @Mapping(target = "cedulaCliente", ignore = true)
    @Mapping(target = "peso", ignore = true)
    @Mapping(target = "valorDeclaradoPaquete", ignore = true)
    EnvioDTO envioToEnvioDTO(Envio envio);

    @Mapping(target = "numeroGuia", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "horaEntrega", ignore = true)
    @Mapping(target = "paquete", ignore = true)
    Envio envioDTOtoEnvio(EnvioDTO envioDTO);
}
