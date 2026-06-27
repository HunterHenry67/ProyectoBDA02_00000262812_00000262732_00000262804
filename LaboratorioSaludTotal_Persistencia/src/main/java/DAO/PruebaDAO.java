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
 *
 * @author user
 */
public class PruebaDAO implements IPruebaDAO{
    private IConexionBD conexion;
    
    public PruebaDAO(){
        this.conexion = new ConexionBD();
    }

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
