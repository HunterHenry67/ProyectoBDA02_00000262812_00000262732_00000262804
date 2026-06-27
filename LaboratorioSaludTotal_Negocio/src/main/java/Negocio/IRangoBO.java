/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTO.RangoDTO;
import Entidades.Rango;
import java.util.List;

/**
 *
 * @author user
 */
public interface IRangoBO {
    RangoDTO agregarRango(Rango rango) throws NegocioException;
    RangoDTO eliminarRango(int idRango) throws NegocioException;
    List<RangoDTO> buscarRangosPorParametro(int idParametro) throws NegocioException;
    long obtenerRangosPorParametro(int idParametro, String sexo, int edad) throws NegocioException;
}
