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
    public Cliente buscarClienteId(int id) throws NegocioException {
        
        try {
            Cliente cliente;
            
            if (id <= 0){
                throw new NegocioException ("Error en ClienteBO, ID invalido");
            }
            
            cliente = clienteDAO.buscarClienteId(id);
            
            if (cliente == null){
            throw new NegocioException ("Error en ClienteBO, no se encontro ningun cliente con ese id");
            }
            return cliente;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con dao");

        }
        
    }

    @Override
    public List<Cliente> buscarClienteNombre(String nombre) throws NegocioException {
        try {
            
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteNombre(nombre);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese nombre");
            }
            
            return clientes;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<Cliente> buscarClienteFechaNacimiento(LocalDateTime fecha) throws NegocioException {
        try {
            
            if (fecha == null){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteFechaNacimiento(fecha);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con esa fecha de nacimiento");
            }
            
            return clientes;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<Cliente> buscarClienteTipoSangre(String tipoSangre) throws NegocioException {
        try {
            
            if (tipoSangre.isEmpty() || tipoSangre == null){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteTipoSangre(tipoSangre);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese tipo de sangre");
            }
            
            return clientes;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<Cliente> buscarClienteSexo(Sexo sexo) throws NegocioException {
        try {
            
            if (sexo == null){
                throw new NegocioException("ingrese datos válidos o correctos");
            }
            
            List<Cliente> clientes = clienteDAO.buscarClienteSexo(sexo);
            
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese sexo");
            }
            
            return clientes;
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }

    @Override
    public List<Cliente> ObtenerClientes() throws NegocioException {
        try{
            List<Cliente> clientes = clienteDAO.ObtenerClientes();
        
            if (clientes.isEmpty() || clientes == null){
                throw new NegocioException("No se encontraron clientes con ese sexo");
            }
            
            return clientes;
            
        }catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con Dao");
        }
    }
    
}
