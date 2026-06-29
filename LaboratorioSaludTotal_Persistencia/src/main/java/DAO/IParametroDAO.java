/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Parametro;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IParametroDAO {

    Parametro registarParametro(Parametro nuevoParametro) throws PersistenciaException;

    List<Parametro> listarTodos() throws PersistenciaException;

    void eliminarParametro(Integer idParametro) throws PersistenciaException;

    Parametro consultarParametroPorID(Integer idParametro) throws PersistenciaException;
    
    List<Parametro> consultarParametroPorNombre(String nombre) throws PersistenciaException;
    
    List<Parametro> consultarParametroPorOrden(Integer orden) throws PersistenciaException;
    
    List<Parametro> consultarParametroPorUnidadMedidad(String unidadMedida) throws PersistenciaException;
    
    List<Parametro> consultarParametroPorCantidadRango(Integer rangos) throws PersistenciaException;
    
    Integer contarRangos(Integer idParametro) throws PersistenciaException;
    
    List<Parametro> listarPorPrueba(Integer idPrueba) throws PersistenciaException;
}
