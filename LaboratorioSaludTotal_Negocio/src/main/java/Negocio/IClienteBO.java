/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Entidades.Cliente;
import Entidades.Sexo;
import enriquemadridalvarez.laboratoriosaludtotal_dto.ClienteDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author user
 */
public interface IClienteBO {
    ClienteDTO buscarClienteId (int id) throws NegocioException;
    List<ClienteDTO> buscarClienteNombre (String nombre) throws NegocioException;
    List<ClienteDTO> buscarClienteFechaNacimiento (LocalDateTime fecha) throws NegocioException;
    List<ClienteDTO> buscarClienteTipoSangre (String tipoSangre) throws NegocioException;
    List<ClienteDTO> buscarClienteSexo (Sexo sexo) throws NegocioException;
    List<ClienteDTO> ObtenerClientes () throws NegocioException;
}
