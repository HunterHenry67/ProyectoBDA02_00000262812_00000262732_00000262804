/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Cliente;
import Entidades.Sexo;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author user
 */
public interface IClienteDAO {
    
    Cliente buscarClienteId (int id) throws PersistenciaException;
    List<Cliente> buscarClienteNombre (String nombre) throws PersistenciaException;
    List<Cliente> buscarClienteFechaNacimiento (LocalDateTime fecha) throws PersistenciaException;
    List<Cliente> buscarClienteTipoSangre (String tipoSangre) throws PersistenciaException;
    List<Cliente> buscarClienteSexo (Sexo sexo) throws PersistenciaException;
    List<Cliente> obtenerClientes () throws PersistenciaException;
}
