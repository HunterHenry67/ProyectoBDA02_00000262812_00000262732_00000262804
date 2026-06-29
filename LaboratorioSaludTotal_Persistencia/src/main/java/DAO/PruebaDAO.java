/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Prueba;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
<<<<<<< HEAD
 *
 * @author user
 */
public class PruebaDAO implements IPruebaDAO{
=======
 * Clase de acceso a datos para la entidad {@link Prueba}.
 * Gestiona el registro, eliminación y consulta de pruebas clínicas, asegurando la 
 * integridad de las relaciones con las entidades {@link Cliente}, {@link Doctor} y {@link Analisis}.
 * * @author user
 */
public class PruebaDAO implements IPruebaDAO {

>>>>>>> 747d87a6fda93f810baee67280232714a04eac71
    private IConexionBD conexion;
    
    public PruebaDAO(){
        this.conexion = new ConexionBD();
    }

    /**
<<<<<<< HEAD
     * 
     * @param prueba
     * @return
     * @throws PersistenciaException 
=======
     * Persiste una nueva prueba en la base de datos
     * Antes de guardar, verifica la existencia de las entidades relacionadas (Cliente, Doctor, Analisis)
     * en el sistema para evitar errores de persistencia
     * * @param prueba El objeto {@link Prueba} a guardar
     * @return El objeto {@link Prueba} persistido con su ID generado
     * @throws PersistenciaException Si alguna entidad relacionada no existe o ocurre un error en la transacción
>>>>>>> 747d87a6fda93f810baee67280232714a04eac71
     */
    @Override
    public Prueba agregarPrueba(Prueba prueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            em.getTransaction().begin();
            em.persist(prueba);
            em.getTransaction().commit();
            return prueba;
            
        }catch(Exception e){
            throw new PersistenciaException("Error al agregar prueba en DAO");
        }finally{
            if(em != null){
                em.close();
            }
        }
    }

    /**
<<<<<<< HEAD
     * 
     * @param idPrueba
     * @return
     * @throws PersistenciaException 
=======
     * Elimina una prueba existente mediante su id
     * * @param idPrueba El ID de la prueba a eliminar
     * @return La prueba eliminada
     * @throws PersistenciaException Si la prueba no existe o hay error en la transacción
>>>>>>> 747d87a6fda93f810baee67280232714a04eac71
     */
    @Override
    public Prueba eliminarPrueba(int idPrueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            em.getTransaction().begin();
            Prueba pruebaEliminada = em.find(Prueba.class, idPrueba);
            em.remove(pruebaEliminada);
            em.getTransaction().commit();
            return pruebaEliminada;
        
        }catch(Exception e){
            throw new PersistenciaException("Error al eliminar prueba en Dao");
        }finally{
            if(em != null){
                em.close();
            }
        }
    }

    /**
<<<<<<< HEAD
     * 
     * @param idPrueba
     * @return
     * @throws PersistenciaException 
=======
     * Busca una prueba específica por su ID
     * * @param idPrueba El identificador de la prueba
     * @return La prueba encontrada
     * @throws PersistenciaException Si la prueba no se encuentra o hay error en la consulta
>>>>>>> 747d87a6fda93f810baee67280232714a04eac71
     */
    @Override
    public Prueba buscarPruebaPorId(int idPrueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            Prueba pruebaBuscada = em.find(Prueba.class, idPrueba);
            if(pruebaBuscada == null){
                throw new PersistenciaException("Prueba buscada no existe");
            }
            return pruebaBuscada;
        
        }catch(Exception e){
            throw new PersistenciaException("Error al eliminar prueba en Dao");
        }finally{
            if(em != null){
                em.close();
            }
        }
    }

    /**
<<<<<<< HEAD
     * 
     * @return
     * @throws PersistenciaException 
=======
     * Consulta todas las pruebas registradas en el sistema
     * * @return Lista de objetos {@link Prueba}
     * @throws PersistenciaException Si ocurre un error al ejecutar la consulta
>>>>>>> 747d87a6fda93f810baee67280232714a04eac71
     */
    @Override
    public List<Prueba> consultarTodasLasPruebas() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Prueba> cq = cb.createQuery(Prueba.class);
            Root<Prueba> root = cq.from(Prueba.class);
            
            cq.select(root);
            return em.createQuery(cq).getResultList();
        }catch(Exception e){
            throw new PersistenciaException("Error al traer todas las consultas en DAO");
        }finally{
            if(em != null){
                em.close();
            }
        }
    }
    
}
