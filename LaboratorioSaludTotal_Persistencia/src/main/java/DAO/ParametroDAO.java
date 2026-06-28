/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Parametro;
import Entidades.Rango;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author BALAMRUSH
 */
public class ParametroDAO implements IParametroDAO {

    private static final Logger LOGGER = Logger.getLogger(ParametroDAO.class.getName());

    private final IConexionBD conexionBD;

    public ParametroDAO() {
        this.conexionBD = new ConexionBD();
    }

    @Override
    public Parametro registarParametro(Parametro nuevoParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoParametro);
            entityManager.getTransaction().commit();
            return nuevoParametro;
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al agregar el parámtero, hubo un rollback: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> listarTodos() throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
            Root<Parametro> parametro = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(parametro).orderBy(criteriaBuilder.asc(parametro.get("ordenReporte")));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar todos los parametros: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void eliminarParametro(Integer idParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            entityManager.getTransaction().begin();
            Parametro parametroEliminado = entityManager.find(Parametro.class, idParametro);
            if(parametroEliminado == null){
                throw new PersistenciaException("El parámetro que se desea eliminar no existe.");
            }
            entityManager.remove(parametroEliminado);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el parámetro, hubo un rollback: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Parametro consultarParametroPorID(Integer idParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            Parametro parametroEncontrado = entityManager.find(Parametro.class, idParametro);
            if (parametroEncontrado == null) {
                throw new Exception("El parametro no existe");
            }
            return parametroEncontrado;
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar el paraemtro por ID: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> consultarParametroPorNombre(String nombre) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
            Root<Parametro> parametro = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(parametro)
                    .where(criteriaBuilder
                            .like(criteriaBuilder.lower(parametro.get("nombre")), "%" + nombre.toLowerCase() + "%"));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar los parámetros por Nombre: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> consultarParametroPorOrden(Integer orden) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
            Root<Parametro> parametro = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(parametro)
                    .where(criteriaBuilder.equal(parametro.get("ordenReporte"), orden));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar los parámetros por ID: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> consultarParametroPorUnidadMedidad(String unidadMedida) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
            Root<Parametro> parametro = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(parametro).
                    where(criteriaBuilder.like(criteriaBuilder.lower(parametro.get("unidadMedida")), "%" + unidadMedida.toLowerCase() + "%"));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar el parámetro por unidad de medidad: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> consultarParametroPorCantidadRango(Integer rangos) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Parametro> criteriaQuery = criteriaBuilder.createQuery(Parametro.class);
            Root<Parametro> parametro = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(parametro)
                    .where(criteriaBuilder.equal(criteriaBuilder.size(parametro.get("listaRangos")), rangos
                    ));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar los parámetros con cantidad de rango.");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Integer contarRangos(Integer idParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Rango> rango = criteriaQuery.from(Rango.class);
            criteriaQuery.select(criteriaBuilder.count(rango))
                    .where(criteriaBuilder.equal(rango.get("parametro").get("idParametro"), idParametro));
            Long totalRangos = entityManager.createQuery(criteriaQuery).getSingleResult();
            return totalRangos.intValue();
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al contarlos rangos del parámetro: "+ex.getMessage());                          
        }finally{
            entityManager.close();
        }
    }

}
