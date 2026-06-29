/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Rango;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author BALAMRUSH
 */
public class RangoDAO implements IRangoDAO {

    private IConexionBD conexion;

    public RangoDAO() {
        this.conexion = new ConexionBD();
    }

    /**
     *
     * @param rango
     * @return
     * @throws PersistenciaException
     */
    @Override
    public Rango agregarRango(Rango rango) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            em.getTransaction().begin();
            em.persist(rango);
            em.getTransaction().commit();
            return rango;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error en DAO al agregar rango");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 
     * @param idRango
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Rango eliminarRango(int idRango) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            em.getTransaction().begin();
            Rango rangoAEliminar = em.find(Rango.class, idRango);
            if (rangoAEliminar != null) {
                em.remove(rangoAEliminar);
                em.getTransaction().commit();

                return rangoAEliminar;
            } else {
                em.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error en DAO al eliminar el rango ");
        } finally {
            if (em != null) {
                em.close();
            }

        }
    }

    /**
     * 
     * @param idParametro
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public List<Rango> buscarRangosPorParametro(int idParametro) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<Rango> cq = cb.createQuery(Rango.class);

            Root<Rango> root = cq.from(Rango.class);

            javax.persistence.criteria.Predicate condicion = cb.equal(root.get("parametro").get("idParametro"), idParametro);

            cq.select(root).where(condicion);

            return em.createQuery(cq).getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error en DAO al buscar los rangos del parámetro ");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 
     * @param idParametro
     * @param sexo
     * @param edad
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public long obtenerRangosPorParametro(int idParametro, String sexo, int edad) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Rango> root = cq.from(Rango.class);
            javax.persistence.criteria.Predicate condicion = cb.equal(root.get("parametro").get("idParametro"), idParametro);
            cq.select(cb.count(root)).where(condicion);

            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error en DAO al contar los rangos ");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
