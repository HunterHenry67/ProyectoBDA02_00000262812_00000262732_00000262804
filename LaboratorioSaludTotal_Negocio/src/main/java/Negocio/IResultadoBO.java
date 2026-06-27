/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTO.RegistrarResultadoDTO;
import DTO.ResultadoDTO;
import Entidades.Resultado;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IResultadoBO {
    Resultado registrarResultado(RegistrarResultadoDTO dto) throws NegocioException;

    Resultado consultarResultadoPorID(Integer idResultado) throws NegocioException;

    List<ResultadoDTO> consultarTablaPorPrueba(Integer idPrueba) throws NegocioException;

    Resultado consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws NegocioException;

    boolean resultadoExiste(Integer idPrueba, Integer idParametro) throws NegocioException;
}
