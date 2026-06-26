/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import Entidades.Analisis;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IAnalisisBO {
    public Analisis guardar(Analisis analisis) throws NegocioException;
    public Analisis actualizar(Analisis analisis) throws NegocioException;
    public void eliminar(Integer idAnalisis) throws NegocioException;
    public Analisis consultarPorId(Integer idAnalisis) throws NegocioException;
    public List<Analisis> consultarTodos() throws NegocioException;
    public List<Analisis> buscarPorNombre(String nombre) throws NegocioException; 
}
