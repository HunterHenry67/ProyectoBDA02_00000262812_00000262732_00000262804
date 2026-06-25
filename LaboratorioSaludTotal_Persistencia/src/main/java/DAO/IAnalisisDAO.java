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

    public Analisis guardar(GuardarAnalisisDTO guardar) throws Exception;

    public Analisis actualizar(ActualizarAnalisisDTO actualizar) throws Exception;

    public void eliminar(EliminarAnalisisDTO eliminar) throws Exception;

    public Analisis consultarPorId(Integer idAnalisis) throws Exception;

    public List<Analisis> consultarTodos() throws Exception;

    public List<Analisis> buscarPorNombre(String nombre) throws Exception;
}