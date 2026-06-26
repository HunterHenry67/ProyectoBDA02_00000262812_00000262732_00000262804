/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Analisis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author BALAMRUSH
 */
public class AnalisisDAO implements IAnalisisDAO {

    private IConexionBD conexion;

    public AnalisisDAO() {
        this.conexion = new ConexionBD();
    }

    @Override
    public Analisis guardar(Analisis analisis) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
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

        EntityManager em = conexion.conexionBD();

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
        EntityManager em = conexion.conexionBD();
        try {
            em.getTransaction().begin();
            Analisis analisisRemover = em.find(Analisis.class, idAnalisis);
            em.remove(analisisRemover);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el análisis: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public Analisis consultarPorId(Integer idAnalisis) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Analisis> cq = cb.createQuery(Analisis.class);
            Root<Analisis> root = cq.from(Analisis.class);
            cq.select(root)
                    .where(cb.equal(root.get("idAnalisis"), idAnalisis));
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el análisis: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Analisis> consultarTodos() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Analisis> cq = cb.createQuery(Analisis.class);
            Root<Analisis> root = cq.from(Analisis.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar los análisis: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @Override
    public List<Analisis> buscarPorNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Analisis> cq = cb.createQuery(Analisis.class);
            Root<Analisis> root = cq.from(Analisis.class);
            cq.select(root).where(cb.like(root.get("nombre"), "%" + nombre + "%"));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar análisis: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
