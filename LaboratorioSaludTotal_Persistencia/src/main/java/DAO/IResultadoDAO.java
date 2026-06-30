/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Resultado;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author BALAMRUSH
 */
public interface IResultadoDAO {

    Resultado registrarResultado(Resultado resultado) throws PersistenciaException;

    Resultado consultarResultadoPorID(Integer idResultado) throws PersistenciaException;

    List<Resultado> consultarResultadoPorPrueba(Integer idPrueba) throws PersistenciaException;

    Resultado consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws PersistenciaException;
}
