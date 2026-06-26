/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ClienteDAO;
import DAO.IClienteDAO;
import DAO.PersistenciaException;
import Entidades.Cliente;
import Entidades.Sexo;
import enriquemadridalvarez.laboratoriosaludtotal_dto.ClienteDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ClienteBO implements IClienteBO{

    private IClienteDAO clienteDAO;
    
    public ClienteBO (){
        this.clienteDAO = new ClienteDAO();
    }
    
    @Override
    public ClienteDTO buscarClienteId(int id) throws NegocioException {
        
        try {
            
            if (id <= 0){
                throw new NegocioException ("Error en ClienteBO, ID invalido");
            }
            
            Cliente cliente = clienteDAO.buscarClienteId(id);
            if (cliente == null){
            throw new NegocioException ("Error en ClienteBO, no se encontro ningun cliente con ese id");
            }
            
            
            return convertirADTO(cliente);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con dao");

        }
        
    }

    @Override
    public List<ClienteDTO> buscarClienteNombre(String nombre) throws NegocioException {
        try {
            
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteNombre(nombre);
            
            if (clientes == null || clientes.isEmpty()){
                throw new NegocioException("No se encontraron clientes con ese nombre");
            } 
            
            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);
                
            }
            
            return clientesNuevos;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<ClienteDTO> buscarClienteFechaNacimiento(LocalDateTime fecha) throws NegocioException {
        try {
            
            if (fecha == null){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteFechaNacimiento(fecha);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con esa fecha de nacimiento");
            }
            
            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);
                
            }
            
            return clientesNuevos;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<ClienteDTO> buscarClienteTipoSangre(String tipoSangre) throws NegocioException {
        try {
            
            if (tipoSangre == null || tipoSangre.isEmpty()){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteTipoSangre(tipoSangre);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese tipo de sangre");
            }
            
            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);
                
            }
            
            return clientesNuevos;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<ClienteDTO> buscarClienteSexo(Sexo sexo) throws NegocioException {
        try {
            
            if (sexo == null){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteSexo(sexo);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese sexo");
            }
            
            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);
                
            }
            
            return clientesNuevos;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<ClienteDTO> ObtenerClientes() throws NegocioException {
        try {
            List<Cliente> listaEntidades = clienteDAO.obtenerClientes();

            List<ClienteDTO> listaDTOs = new ArrayList<>();

            for (Cliente pac : listaEntidades) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                listaDTOs.add(clienteDTO);
                
            }

            return listaDTOs;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }
    
    private ClienteDTO convertirADTO(Cliente entidadCliente) {
        ClienteDTO dto = new ClienteDTO();

        dto.setIdCliente(entidadCliente.getIdCliente());
        dto.setNombres(entidadCliente.getNombres());
        dto.setApellidoPaterno(entidadCliente.getApellidoPaterno());
        dto.setApellidoMaterno(entidadCliente.getApellidoMaterno());
        dto.setSexo(entidadCliente.getSexo().toString());
        dto.setFechaNacimiento(entidadCliente.getFechaNacimiento());
        dto.setTipoSangre(entidadCliente.getTipoSangre());

        return dto;
    }
    
}
