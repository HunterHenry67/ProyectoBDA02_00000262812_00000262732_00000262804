/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Muestra;
import Entidades.Resultado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author user
 */
public class MuestraDAO implements IMuestraDAO{
    private IConexionBD conexion;
    
    public MuestraDAO(){
        this.conexion = new ConexionBD();
    }

    @Override
    public Muestra agregarMuestra(Muestra muestra) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try{
            em.getTransaction().begin();
            em.persist(muestra);
            em.getTransaction().commit();
            return muestra;
        }catch(Exception  e){
            throw new PersistenciaException("Error al agregar muestra en DAO");
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Muestra buscarMuestraPorId(int idMuestra) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try{
            Muestra muestraEncontrada = em.find(Muestra.class, idMuestra);
            if (muestraEncontrada == null){
                throw new PersistenciaException("Error no se encontro muestra por ese id");
            }
            return muestraEncontrada;
        }catch(Exception e){
            throw new PersistenciaException("Error en buscar muestra por id en dao");
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Muestra> consultarTodasLasMuestras() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try{
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Muestra> criteriaQuery = criteriaBuilder.createQuery(Muestra.class);
            Root<Muestra> ruta = criteriaQuery.from(Muestra.class);
            
            criteriaQuery.select(ruta);
            return em.createQuery(criteriaQuery).getResultList();
            
        }catch(Exception e){
            throw new PersistenciaException("Error al obtener las muestras");
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Muestra> buscarMuestrasPorPrueba(int idPrueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Muestra> cq = cb.createQuery(Muestra.class);
            Root<Muestra> root = cq.from(Muestra.class);

            cq.select(root).where(cb.equal(root.get("prueba").get("idPrueba"), idPrueba));

            return em.createQuery(cq).getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error en DAO al buscar las muestras de la prueba con ID: ");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    
}
