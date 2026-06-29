/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Prueba;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IPruebaDAO {
    Prueba agregarPrueba(Prueba prueba) throws PersistenciaException;
    Prueba eliminarPrueba(int idPrueba) throws PersistenciaException; 
    Prueba buscarPruebaPorId(int idPrueba) throws PersistenciaException;
    List<Prueba> consultarTodasLasPruebas() throws PersistenciaException;
}
