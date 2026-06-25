/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Analisis;
import Entidades.Muestra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author BALAMRUSH
 */
public class AnalisisDAO implements IAnalisisDAO {

    private final EntityManagerFactory emf;

    public AnalisisDAO() {
        this.emf = Persistence.createEntityManagerFactory("NombreDeTuUnidadPersistencia");
    }

    @Override
    public Analisis guardar(GuardarAnalisisDTO guardar) throws Exception {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(guardar);

            em.getTransaction().commit();

            return guardar;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new Exception("Error al guardar el análisis", e);
        } finally {
            em.close();
        }

    }

    @Override
    public Analisis actualizar(Object actualizar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Object eliminar) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Analisis consultarPorId(Integer idAnalisis) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Analisis> consultarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Analisis> buscarPorNombre(String nombre) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}