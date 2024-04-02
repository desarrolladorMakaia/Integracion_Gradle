package com.manuel.proyectointegrador.Mapper;

import com.manuel.proyectointegrador.dto.ClienteDTO;
import com.manuel.proyectointegrador.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteToClienteDTO(Cliente cliente);

    Cliente clienteDTOtoCliente(ClienteDTO clienteDTO);

}
