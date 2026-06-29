/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Muestra;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IMuestraDAO {
    Muestra agregarMuestra(Muestra muestra) throws PersistenciaException;
    Muestra buscarMuestraPorId(int idMuestra) throws PersistenciaException;
    List<Muestra> consultarTodasLasMuestras() throws PersistenciaException;    
    List<Muestra> buscarMuestrasPorPrueba(int idPrueba) throws PersistenciaException;

}
