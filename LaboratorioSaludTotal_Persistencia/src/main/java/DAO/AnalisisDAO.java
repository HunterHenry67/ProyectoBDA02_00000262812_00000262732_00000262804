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
    public Analisis guardar(Analisis analisis) throws PersistenciaException {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(analisis);

            em.getTransaction().commit();

            return analisis;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al guardar el análisis: " + e.getMessage());

        } finally {
            em.close();
        }
    }

    @Override
    public Analisis actualizar(Analisis analisis) throws PersistenciaException {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Analisis analisisActualizado = em.merge(analisis);

            em.getTransaction().commit();

            return analisisActualizado;

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al actualizar el análisis: " + e.getMessage());

        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Integer idAnalisis) throws PersistenciaException {
        EntityManager em = emf.createEntityManager();
        
        try{
            
            em.getTransaction().begin();;
            Analisis analisisRemover = em.find(Analisis.class, idAnalisis);
            em.remove(analisisRemover);
            em.getTransaction().commit();
        
        } catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public Analisis consultarPorId(Integer idAnalisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Analisis> consultarTodos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Analisis> buscarPorNombre(String nombre) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
