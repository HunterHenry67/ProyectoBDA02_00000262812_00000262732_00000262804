/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import Entidades.Analisis;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */

public interface IAnalisisDAO {

    public Analisis guardar(Analisis analisis) throws PersistenciaException;

    public Analisis actualizar(Analisis analisis) throws PersistenciaException;

    public void eliminar(Integer idAnalisis) throws PersistenciaException;

    public Analisis consultarPorId(Integer idAnalisis) throws PersistenciaException;

    public List<Analisis> consultarTodos() throws PersistenciaException;

    public List<Analisis> buscarPorNombre(String nombre) throws PersistenciaException;
    
    Integer contarParametros(Integer idAnalisis) throws PersistenciaException;
}