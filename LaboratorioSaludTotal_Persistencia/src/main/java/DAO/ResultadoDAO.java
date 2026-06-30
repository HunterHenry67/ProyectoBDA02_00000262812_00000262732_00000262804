/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Resultado;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase de acceso a datos para la entidad {@link Resultado}
 * Gestiona las operaciones de persistencia y consultas mediante JPA para 
 * los resultados de los análisis clínicos.
 * * @author BALAMRUSH
 */
public class ResultadoDAO implements IResultadoDAO{

    private static final Logger LOGGER = Logger.getLogger(ResultadoDAO.class.getName());

    
    private IConexionBD conexionBD;
    
    public ResultadoDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    /**
     * Registra un nuevo resultado en la base de datos
     * * @param resultado El objeto {@link Resultado} a registrar
     * @return El objeto {@link Resultado} registrado
     * @throws PersistenciaException Si ocurre un error durante la transacción
     */
    @Override
    public Resultado registrarResultado(Resultado resultado) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(resultado);
            entityManager.getTransaction().commit();
            return resultado;
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al registrar el resultado: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }

    /**
     * Consulta un resultado específico por su id
     * * @param idResultado El ID del resultado a buscar
     * @return El objeto {@link Resultado} encontrado
     * @throws PersistenciaException Si el resultado no existe o hay un error de conexión
     */
    @Override
    public Resultado consultarResultadoPorID(Integer idResultado) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            Resultado resultadoEncontrado = entityManager.find(Resultado.class, idResultado);
            if(resultadoEncontrado == null){
                throw new PersistenciaException("El resultado que busca no existe.");
            }
            return resultadoEncontrado;
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar el resultado por ID: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }

    /**
     * Recupera una lista de resultados asociados a una prueba 
     * * @param idPrueba El ID de la prueba que se desean consultar
     * @return Lista de objetos {@link Resultado} encontrados
     * @throws PersistenciaException Si ocurre un error en la consulta
     */
    @Override
    public List<Resultado> consultarResultadoPorPrueba(Integer idPrueba) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Resultado> criteriaQuery = criteriaBuilder.createQuery(Resultado.class);
            Root<Resultado> ruta = criteriaQuery.from(Resultado.class);
            
            criteriaQuery.select(ruta)
                    .where(criteriaBuilder.equal(ruta.get("prueba").get("idPrueba"), idPrueba));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultra los resultados por pruebas: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }

    /**
     * Busca un resultado único filtrado por prueba y parámetro
     * * @param idPrueba El ID de la prueba
     * @param idParametro El ID del parámetro clínico
     * @return El objeto {@link Resultado} encontrado, o null si ocurre una excepción
     * @throws PersistenciaException Si ocurre un error durante la búsqueda
     */
    @Override
    public Resultado consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Resultado> criteriaQuery = criteriaBuilder.createQuery(Resultado.class);
            Root<Resultado> ruta = criteriaQuery.from(Resultado.class);
            
            criteriaQuery.select(ruta)
                    .where(
                            criteriaBuilder.and(criteriaBuilder.equal(ruta.get("prueba").get("idPrueba"), idPrueba),
                            criteriaBuilder.equal(ruta.get("parametro").get("idParametro"), idParametro)));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        }catch(NumberFormatException e){
            return null;
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al consultar el resultado por Prueba y Parámetro: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }
    
}
