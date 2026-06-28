/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import DTO.ActualizarAnalisisDTO;
import DTO.AnalisisDTO;
import DTO.EliminarAnalisisDTO;
import DTO.GuardarAnalisisDTO;
import Entidades.Analisis;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IAnalisisBO {
    public Analisis guardarAnalisis(GuardarAnalisisDTO guardarAnalisis) throws NegocioException;
    public Analisis actualizarAnalisis(ActualizarAnalisisDTO actualizarAnalisis) throws NegocioException;
    public void eliminarAnalisis(EliminarAnalisisDTO eliminarAnalisis) throws NegocioException;
    public Analisis consultarPorId(Integer idAnalisis) throws NegocioException;
    public List<AnalisisDTO> consultarTodos() throws NegocioException;
    public List<AnalisisDTO> buscarPorNombre(String nombre) throws NegocioException; 
    public Integer contarParametro(Integer idAnalisis) throws NegocioException;
    List<AnalisisDTO> buscarPorTipoMuestra(String tipoMuestra) throws NegocioException;
    List<AnalisisDTO> buscarPorCantidadParametro(Integer cantidad) throws NegocioException; 
}
