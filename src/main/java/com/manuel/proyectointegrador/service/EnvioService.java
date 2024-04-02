package com.manuel.proyectointegrador.service;

import com.manuel.proyectointegrador.Mapper.EnvioMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    private EnvioRepository envioRepository;
    private ClienteRepository clienteRepository;
    private EmpleadoRepository empleadoRepository;
    private PaqueteRepository paqueteRepository;

    @Autowired
    public EnvioService(EnvioRepository envioRepository, ClienteRepository clienteRepository, EmpleadoRepository empleadoRepository, PaqueteRepository paqueteRepository) {
        this.envioRepository = envioRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.paqueteRepository = paqueteRepository;
    }

    public EnvioResponseDTO crearEnvio(EnvioDTO envioDTO){
        if(envioDTO.getCedulaCliente()==null){
            throw new ApiRequestException("Hace falta la cedula del cliente");
        }
        if(envioDTO.getCiudadDestino()==null){
            throw new ApiRequestException("Hace falta la ciudad destino");
        }
        if(envioDTO.getCiudadOrigen()==null){
            throw new ApiRequestException("Hace falta la ciudad origen");
        }
        if(envioDTO.getDireccionDestino()==null){
            throw new ApiRequestException("Hace falta la direccion destino");
        }
        if(envioDTO.getNombreRecibe()==null){
            throw new ApiRequestException("Hace falta el nombre de la persona quien recibe el paquete");
        }
        if(envioDTO.getNumRecibe()==null){
            throw new ApiRequestException("Hace falta el numero de la persona quien recibe");
        }
        if(envioDTO.getValorDeclaradoPaquete()==null){
            throw new ApiRequestException("Hace falta el valor declarado del paquete");
        }
        if(envioDTO.getPeso()==null){
            throw new ApiRequestException("Hace falta el peso del paquete");
        }
        Optional<Cliente> cliente = this.clienteRepository.findById(envioDTO.getCedulaCliente());
        if(!cliente.isPresent()){
            throw new ApiRequestException("El cliente con cedula "+envioDTO.getCedulaCliente()+" debe de estar registrado para poder enviar el paquete.");
        }
        Paquete paquete = new Paquete(asignarTipoPaquete(envioDTO.getPeso()),envioDTO.getPeso(),envioDTO.getValorDeclaradoPaquete());
        this.paqueteRepository.save(paquete);
        Envio envio = EnvioMapper.INSTANCE.envioDTOtoEnvio(envioDTO);
        envio.setEstadoEnvio("RECIBIDO");
        envio.setHoraEntrega(asignarHora());
        envio.setValorEnvio(asignarPrecioEnvio(paquete.getTipoPaquete()));
        envio.setPaquete(paquete);
        /*Envio envio = new Envio(
                cliente.get(),envioDTO.getCiudadOrigen(),envioDTO.getCiudadDestino(),envioDTO.getDireccionDestino(),
                envioDTO.getNombreRecibe(),envioDTO.getNumRecibe(),asignarHora(),"RECIBIDO",asignarPrecioEnvio(paquete.getTipoPaquete())
                ,paquete
        );*/
        this.envioRepository.save(envio);
        return new EnvioResponseDTO(envio.getNumeroGuia(),envio.getEstadoEnvio());
    }
    public EnvioDTO buscarEnvio(Integer guia){
        Optional<Envio> envio = this.envioRepository.findById(guia);
        if(!envio.isPresent()){
            throw new ApiRequestException("El numero guia "+guia+" no se encuentra registrado");
        }
        EnvioDTO envioDTO = EnvioMapper.INSTANCE.envioToEnvioDTO(envio.get());
        envioDTO.setCedulaCliente(envio.get().getCliente().getCedula());
        envioDTO.setValorDeclaradoPaquete(envio.get().getPaquete().getValorDeclarado());
        envioDTO.setPeso(envio.get().getPaquete().getPeso());
        return envioDTO;
    }
    public EnvioResponseDTO actualizarEstado(Integer numGuia, Integer cedulaEmpleado, String estado){
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedulaEmpleado);
        if(!empleado.isPresent()){
            throw new ApiRequestException("El empleado con cedula "+cedulaEmpleado+" no existe en nuestra compania");
        }
        String tipo = empleado.get().getTipoEmpleado();
        if(!tipo.equals("REPARTIDOR") && !tipo.equals("COORDINADOR")){
            throw new ApiRequestException("Este empleado con cedula: "+cedulaEmpleado+" no puede realizar el cambio solicitado");
        }
        List<String> estados = Arrays.asList("RECIBIDO","EN RUTA","ENTREGADO");
        if(!estados.contains(estado)){
            throw new ApiRequestException("El estado de envio: "+estado+" no es un estado valido");
        }
        Optional<Envio> envio = this.envioRepository.findById(numGuia);
        if(!envio.isPresent()){
            throw new ApiRequestException("El numero guia "+numGuia+" no se encuentra registrado");
        }
        String estadoEnvio = envio.get().getEstadoEnvio();
        if(estadoEnvio.equals("RECIBIDO")){
            if(estado.equals("EN RUTA")){
                envio.get().setEstadoEnvio(estado);
                this.envioRepository.save(envio.get());
                return new EnvioResponseDTO(numGuia,estado);
            }
            throw new ApiRequestException("No se puede cambiar de "+estadoEnvio+" a "+estado);
        } else if(estadoEnvio.equals("EN RUTA")){
            if(estado.equals("ENTREGADO")){
                envio.get().setEstadoEnvio(estado);
                return new EnvioResponseDTO(numGuia,estado);
            }
            throw new ApiRequestException("No se puede cambiar de "+estadoEnvio+" a "+estado);
        }
        throw new ApiRequestException("El envio ya ha sido entregado");
    }
    public List<EnvioDTO> filtrar(String estado, Integer cedulaEmpleado){
        Optional<Empleado> empleado = this.empleadoRepository.findById(cedulaEmpleado);
        if(!empleado.isPresent()){
            throw new ApiRequestException("El empleado con cedula "+cedulaEmpleado+" no existe en nuestra compania");
        }
        List<String> estados = Arrays.asList("RECIBIDO","EN RUTA","ENTREGADO");
        if(!estados.contains(estado)){
            throw new ApiRequestException("El estado de envio: "+estado+" no es un estado valido");
        }
        List<Envio> envios = this.envioRepository.findAll();
        envios = envios.stream()
                .filter(envio -> envio.getEstadoEnvio().equals(estado))
                .collect(Collectors.toList());
        List<EnvioDTO> enviosDTO = new ArrayList<>();
        for (Envio envio: envios) {
            EnvioDTO envioDTO = EnvioMapper.INSTANCE.envioToEnvioDTO(envio);
            envioDTO.setCedulaCliente(envio.getCliente().getCedula());
            envioDTO.setValorDeclaradoPaquete(envio.getPaquete().getValorDeclarado());
            envioDTO.setPeso(envio.getPaquete().getPeso());
            enviosDTO.add(envioDTO);
        }
        return enviosDTO;
    }
    public List<EnvioDTO> retornarEnvios(){
        List<Envio> envios = this.envioRepository.findAll();
        List<EnvioDTO> enviosDTO = new ArrayList<>();
        for (Envio envio: envios) {
            EnvioDTO envioDTO = EnvioMapper.INSTANCE.envioToEnvioDTO(envio);
            envioDTO.setCedulaCliente(envio.getCliente().getCedula());
            envioDTO.setValorDeclaradoPaquete(envio.getPaquete().getValorDeclarado());
            envioDTO.setPeso(envio.getPaquete().getPeso());
            enviosDTO.add(envioDTO);
        }
        return enviosDTO;
    }
    public String asignarTipoPaquete(Integer peso){
        if(peso<2){
            return "LIVIANO";
        } else if(peso>=2 && peso<5){
            return "MEDIANO";
        }
        return "GRANDE";
    }
    public Double asignarPrecioEnvio(String tipo){
        if(tipo.equals("GRANDE")){
            return 50000.0;
        } else if(tipo.equals("MEDIANO")){
            return 40000.0;
        }
        return 30000.0;
    }
    public String asignarHora(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
