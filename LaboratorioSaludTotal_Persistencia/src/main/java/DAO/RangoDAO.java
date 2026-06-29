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
 * Clase de acceso a datos para la entidad {@link Rango}.
 * métodos para gestionar el ciclo de vida y consultas específicas de los rangos
 * @author BALAMRUSH
 */
public class RangoDAO implements IRangoDAO {

    private IConexionBD conexion;

    public RangoDAO() {
        this.conexion = new ConexionBD();
    }

    /**
     * usa persist para registrar un nuevo objeto Rango en la base de datos
     * @param rango el objeto a guardar
     * @return el mimso objeto rango guardado
     * @throws PersistenciaException Si ocurre un error durante la transacción
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
     * Elimina un rango de la base de datos que coincida con el id
     * * @param idRango El identificador único del rango a eliminar
     * @return El objeto {@link Rango} eliminado, o null si no se encontró el ID
     * @throws PersistenciaException Si ocurre un error durante la eliminación o la transacción
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
     * Busca todos los rangos asociados a un parámetro específico mediante su ID
     * * @param idParametro El identificador del parámetro para filtrar los rangos
     * @return Una lista de objetos {@link Rango} relacionados con el parámetro
     * @throws PersistenciaException Si ocurre un error durante la ejecución de la consulta
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
     * Cuenta la cantidad de rangos asociados a un parámetro
     * * @param idParametro El identificador del parámetro
     * @param sexo Criterio de sexo 
     * @param edad Criterio de edad 
     * @return Un valor de tipo long con el conteo de rangos encontrados
     * @throws PersistenciaException Si ocurre un error al realizar el conteo
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
