/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.ParametroDTO;
import DTO.RegistrarParametroDTO;
import Entidades.Parametro;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IParametroBO {
    
    Parametro registarParametro(RegistrarParametroDTO registrarParametro) throws NegocioException;

    List<ParametroDTO> listarTodos() throws NegocioException;

    void eliminarParametro(Integer idParametro) throws NegocioException;

    Parametro consultarParametroPorID(Integer idParametro) throws NegocioException;
    
    List<ParametroDTO> consultarParametroPorNombre(String nombre) throws NegocioException;
    
    List<ParametroDTO> consultarParametroPorOrden(Integer orden) throws NegocioException;
    
    List<ParametroDTO> consultarParametroPorUnidadMedidad(String unidadMedida) throws NegocioException;
    
    List<ParametroDTO> consultarParametroPorCantidadRango(Integer rangos) throws NegocioException;
    
    Integer contarRangos(Integer idParametro) throws NegocioException;
    
    List<ParametroDTO> listarPorPrueba(Integer idPrueba) throws NegocioException;
}
