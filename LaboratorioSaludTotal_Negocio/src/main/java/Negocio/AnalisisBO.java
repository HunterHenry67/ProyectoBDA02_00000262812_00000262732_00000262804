/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.AnalisisDAO;
import DAO.ConexionBD;
import DAO.IAnalisisDAO;
import DAO.IConexionBD;
import DAO.PersistenciaException;
import Entidades.Analisis;
import Entidades.Doctor;
import java.util.List;

/**
 *
 * @author Andre
 */
public class AnalisisBO implements IAnalisisBO {

    private IAnalisisDAO analisisDAO;

    public AnalisisBO() {
        this.analisisDAO = new AnalisisDAO();
    }

    @Override
    public Analisis guardar(Analisis analisis) throws NegocioException {
        try {
            if (analisis == null) {
                throw new NegocioException("El análisis no puede ser nulo.");
            }

            if (analisis.getNombre() == null || analisis.getNombre().isBlank()) {
                throw new NegocioException("El nombre del análisis es obligatorio.");
            }

            return analisisDAO.guardar(analisis);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al guardar el análisis.", e);
        }
    }

    @Override
    public Analisis actualizar(Analisis analisis) throws NegocioException {
        try {
            if (analisis == null) {
                throw new NegocioException("El análisis no puede ser nulo.");
            }

            if (analisis.getIdAnalisis() == null || analisis.getIdAnalisis() <= 0) {
                throw new NegocioException("ID de análisis inválido.");
            }

            if (analisis.getNombre() == null || analisis.getNombre().isBlank()) {
                throw new NegocioException("El nombre del análisis es obligatorio.");
            }

            return analisisDAO.actualizar(analisis);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el análisis.", e);
        }
    }

    @Override
    public void eliminar(Integer idAnalisis) throws NegocioException {
        try {
            if (idAnalisis == null || idAnalisis <= 0) {
                throw new NegocioException("ID de análisis inválido.");
            }

            analisisDAO.eliminar(idAnalisis);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar el análisis.", e);
        }
    }

    @Override
    public Analisis consultarPorId(Integer idAnalisis) throws NegocioException {
        try {
            if (idAnalisis == null || idAnalisis <= 0) {
                throw new NegocioException("ID de análisis inválido.");
            }

            Analisis analisis = analisisDAO.consultarPorId(idAnalisis);

            if (analisis == null) {
                throw new NegocioException("No se encontró el análisis.");
            }

            return analisis;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar el análisis.", e);
        }
    }

    @Override
    public List<Analisis> consultarTodos() throws NegocioException {
        try {
            return analisisDAO.consultarTodos();

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar los análisis.", e);
        }
    }

    @Override
    public List<Analisis> buscarPorNombre(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.isBlank()) {
                throw new NegocioException("El nombre de búsqueda es obligatorio.");
            }

            return analisisDAO.buscarPorNombre(nombre);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar análisis por nombre.", e);
        }
    }
}