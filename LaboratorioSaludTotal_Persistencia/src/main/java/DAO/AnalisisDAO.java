/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Analisis;
import Entidades.Parametro;
import Entidades.Resultado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * @author BALAMRUSH
 */
public class AnalisisDAO implements IAnalisisDAO {

    private IConexionBD conexion;

    public AnalisisDAO() {
        this.conexion = new ConexionBD();
    }

    /**
     * Guarda un nuevo análisis en la base de datos.
     *
     * @param analisis entidad que se guarda en la base de datos.
     * @return el análisis guardado.
     * @throws PersistenciaException si ocurre algún error al guardar el
     * análisis.
     */
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

    /**
     * Actualiza la información del análisis seleccionado.
     *
     * @param analisis entidad que se desea actualizar.
     * @return el análisis actualizado.
     * @throws PersistenciaException si ocurre algún error al guardar el
     * análisis.
     */
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

    /**
     * Elimina el análisis seleccionado mediante el identificador.
     *
     * @param idAnalisis identificador del análisis que deseas eliminar.
     * @throws PersistenciaException si ocurre algún error al eliminar el
     * análisis.
     */
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

    /**
     * Consulta un análisis por su identificador.
     *
     * @param idAnalisis identificador de analisis buscado.
     * @return analisis encontrado.
     * @throws PersistenciaException si existe algún error al consultar el
     * análisis por su identificador.
     */
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

    /**
     * Consulta todos los analisis encontrados en la base de datos.
     *
     * @return una lista con todos los analisis encontrados.
     * @throws PersistenciaException si existe algún error al consultar todos
     * los analisis.
     */
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

    /**
     * Busca los analisis que coincidan con el nombre recibido.
     *
     * @param nombre utilizado para filtrar los analisis.
     * @return una lista con los analisis filtrados.
     * @throws PersistenciaException si existe algún error al consultar los
     * analisis por nombre.
     */
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

    /**
     * Cuenta la cantidad de parámetros asociados al analisis.
     *
     * @param idAnalisis identificador del analisis.
     * @return cantidad de parametros asociados.
     * @throws PersistenciaException si existe algún error al contra los
     * parametros.
     */
    @Override
    public Integer contarParametros(Integer idAnalisis) throws PersistenciaException {
        EntityManager entityManager = conexion.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Parametro> ruta = criteriaQuery.from(Parametro.class);
            criteriaQuery.select(criteriaBuilder.count(ruta))
                    .where(criteriaBuilder.equal(ruta.get("analisis").get("idAnalisis"), idAnalisis));
            Long total = entityManager.createQuery(criteriaQuery).getSingleResult();
            return total.intValue();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al contar los parámetros: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    /**
     * Busca el analisis por el tipo de prueba asociado.
     * @param tipoMuestra nombre de tipo de muestra asociado al analisis.
     * @return lista con todos los analisis filtrados.
     * @throws PersistenciaException si ocurre un error al buscar el analisis por tipo de muestra.
     */
    @Override
    public List<Analisis> buscarPorTipoMuestra(String tipoMuestra) throws PersistenciaException {
        EntityManager entityManager = conexion.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Analisis> criteriaQuery = criteriaBuilder.createQuery(Analisis.class);
            Root<Analisis> ruta = criteriaQuery.from(Analisis.class);
            criteriaQuery.select(ruta)
                    .where(criteriaBuilder
                            .like(criteriaBuilder.lower(ruta.get("muestra").get("nombre")), "%" + tipoMuestra.toLowerCase() + "%"));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar por tipo de muestra: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    /**
     * Busca analisis de acuerdo con la cantidad de parametros registrados.
     * @param cantidad de parámetros del analisis.
     * @return lista con todos los analisis filtrados.
     * @throws PersistenciaException si ocurre un error al buscar el analisis
     * por cantidad y parametro.
     */
    @Override
    public List<Analisis> buscarPorCantidadParametro(Integer cantidad) throws PersistenciaException {
        EntityManager entityManager = conexion.conexionBD();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Analisis> criteriaQuery = criteriaBuilder.createQuery(Analisis.class);
            Root<Analisis> rutaAnalisis = criteriaQuery.from(Analisis.class);
            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<Parametro> rutaParametro = subquery.from(Parametro.class);

            subquery.select(criteriaBuilder
                    .count(rutaParametro))
                    .where(criteriaBuilder
                            .equal(rutaParametro.get("analisis").get("idAnalisis"), rutaAnalisis.get("idAnalisis")));
            criteriaQuery.select(rutaAnalisis)
                    .where(criteriaBuilder.equal(
                            subquery,
                            cantidad.longValue()
                    ));
            return entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al buscar el analisis por cantidad de parámetro: " + ex.getMessage());
        } finally {
            entityManager.close();
        }
    }

    /**
     * 
     * @param idPrueba 
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public String obtenerNombreAnalisisPorPrueba(Integer idPrueba) throws PersistenciaException {
        EntityManager em = null;

        try {
            em = conexion.conexionBD();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<String> cq = cb.createQuery(String.class);

            Root<Resultado> resultado = cq.from(Resultado.class);

            Join<Resultado, Parametro> parametro = resultado.join("parametro");
            Join<Parametro, Analisis> analisis = parametro.join("analisis");

            cq.select(analisis.get("nombre")).distinct(true);

            cq.where(
                    cb.equal(
                            resultado.get("prueba").get("idPrueba"),
                            idPrueba
                    )
            );

            List<String> nombres = em.createQuery(cq).getResultList();

            if (nombres == null || nombres.isEmpty()) {
                return "Sin resultados";
            }

            return String.join(", ", nombres);

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener análisis de la prueba" + e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
