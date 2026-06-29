/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Rango;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IRangoDAO {
    Rango agregarRango(Rango rango) throws PersistenciaException;
    Rango eliminarRango(int idRango) throws PersistenciaException;
    List<Rango> buscarRangosPorParametro(int idParametro) throws PersistenciaException;
    long obtenerRangosPorParametro(int idParametro, String sexo, int edad) throws PersistenciaException;
}