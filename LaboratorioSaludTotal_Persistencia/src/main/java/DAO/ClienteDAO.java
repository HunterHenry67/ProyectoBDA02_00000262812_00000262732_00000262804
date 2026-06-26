/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Cliente;
import Entidades.Sexo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author BALAMRUSH
 */
public class ClienteDAO implements IClienteDAO{
    private IConexionBD conexion;
    
    public ClienteDAO (){
        this.conexion = new ConexionBD();
    }

    @Override
    public Cliente buscarClienteId(int id) throws PersistenciaException{
        EntityManager em = conexion.conexionBD();
        
        try {
            
            Cliente cliente = em.find(Cliente.class, id);
            return cliente;
            
        } catch (Exception e) {
            throw new PersistenciaException ("Error al buscar cliente por ID en DAO ");
        }finally {
            if (em != null) {
                em.close();
            }
        }
        
        
    }

    @Override
    public List<Cliente> buscarClienteNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
    
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
            Root<Cliente> root = cq.from(Cliente.class);

            String textoBuscado = "%" + nombre + "%";

            javax.persistence.criteria.Predicate p1 = cb.like(root.get("nombre"), textoBuscado);
            javax.persistence.criteria.Predicate p2 = cb.like(root.get("apellidoPaterno"), textoBuscado);
            javax.persistence.criteria.Predicate p3 = cb.like(root.get("apellidoMaterno"), textoBuscado);

            cq.select(root).where(cb.or(p1, p2, p3));

            return em.createQuery(cq).getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por nombre en DAO");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Cliente> buscarClienteFechaNacimiento(LocalDateTime fecha) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
    
        try {
            LocalDateTime inicioDia = fecha.with(LocalTime.MIN); 
            LocalDateTime finDia = fecha.with(LocalTime.MAX);

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
            Root<Cliente> root = cq.from(Cliente.class);

            cq.select(root).where(cb.between(root.get("fechaNacimiento"), inicioDia, finDia));

            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar clientes por fecha de nacimiento en DAO");
        } finally {
            if (em != null) {
                em.close();
            }            
        }
    }

    @Override
    public List<Cliente> buscarClienteTipoSangre(String tipoSangre) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
    
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
            Root<Cliente> root = cq.from(Cliente.class);
            
            String tipoBuscado = "%" + tipoSangre + "%";
            cq.select(root).where(cb.like(root.get("tipoSangre"),tipoBuscado));
                   
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por tipoSangre en DAO");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Cliente> buscarClienteSexo(Sexo sexo) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);
            Root<Cliente> root = cq.from(Cliente.class);

            cq.select(root).where(cb.equal(root.get("sexo"), sexo));

            return em.createQuery(cq).getResultList();
        }catch (Exception e){
            throw new PersistenciaException ("Eror al obtener cliente por categoria sexo en DAO");
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Cliente> obtenerClientes() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
        
            CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);

            Root<Cliente> root = cq.from(Cliente.class);

            cq.select(root);

            return em.createQuery(cq).getResultList();
        }catch (Exception e){
            throw new PersistenciaException ("Eror al obtener cliente por categoria sexo en DAO");
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
}
