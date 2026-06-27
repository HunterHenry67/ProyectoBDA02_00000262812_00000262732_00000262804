/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTO.PruebaDTO;
import java.util.List;

/**
 *
 * @author user
 */
public interface IPruebaBO {
    PruebaDTO agregarPrueba(PruebaDTO dtoNuevo) throws NegocioException;
    PruebaDTO eliminarPrueba(int idPrueba) throws NegocioException;
    PruebaDTO buscarPruebaPorId(int idPrueba) throws NegocioException;
    List<PruebaDTO> consultarTodasLasPruebas() throws NegocioException;
}
