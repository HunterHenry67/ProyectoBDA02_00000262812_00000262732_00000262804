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
 *
 * @author BALAMRUSH
 */
public class ResultadoDAO implements IResultadoDAO{

    private static final Logger LOGGER = Logger.getLogger(ResultadoDAO.class.getName());

    
    private IConexionBD conexionBD;
    
    public ResultadoDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    /**
     * 
     * @param resultado
     * @return
     * @throws PersistenciaException 
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
     * 
     * @param idResultado
     * @return
     * @throws PersistenciaException 
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
     * 
     * @param idPrueba
     * @return
     * @throws PersistenciaException 
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
     * 
     * @param idPrueba
     * @param idParametro
     * @return
     * @throws PersistenciaException 
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

    /**
     * 
     * @param idPrueba
     * @param idParametro
     * @return
     * @throws PersistenceException 
     */
    @Override
    public boolean resultadoExiste(Integer idPrueba, Integer idParametro) throws PersistenceException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Resultado> ruta = criteriaQuery.from(Resultado.class);
            
            criteriaQuery.select(criteriaBuilder.count(ruta))
                    .where(criteriaBuilder.and(
                            criteriaBuilder.equal(ruta.get("prueba").get("idPrueba"), idPrueba),
                            criteriaBuilder.equal(ruta.get("parametro").get("idParametro"), idParametro)));
            Long total = entityManager.createQuery(criteriaQuery).getSingleResult();
            return total > 0;
        }catch(Exception ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenceException("Error al saber si el resultado ya fue registrado: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }
    
}
