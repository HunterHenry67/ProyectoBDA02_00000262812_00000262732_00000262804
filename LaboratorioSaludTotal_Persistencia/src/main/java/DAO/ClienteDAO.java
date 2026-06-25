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
            String jpql = """
                            SELECT c 
                            FROM Cliente c 
                            WHERE c.nombre LIKE :textoBuscado 
                                OR c.apellidoPaterno LIKE :textoBuscado 
                                OR c.apellidoMaterno LIKE :textoBuscado
                          """;
              
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);

            query.setParameter("textoBuscado", "%" + nombre + "%");

            return query.getResultList();

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
            String jpql = """
                          SELECT c 
                          FROM Cliente c 
                          WHERE c.fechaNacimiento BETWEEN :paramInicio AND :paramFin            
                          """;
                    
                    
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("paramInicio", inicioDia);
            query.setParameter("paramFin", finDia);

            return query.getResultList();
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
            String jpql = """
                          SELECT c 
                          FROM Cliente c 
                          WHERE c.tipoSangre LIKE :tipoSangre
                          """;
                    
                    

            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);

            query.setParameter("tipoSangre", "%" + tipoSangre + "%");

            return query.getResultList();

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
            String jpql = """
                          Select c
                          From Cliente c
                          Where c.sexo = :sexo
                          """;
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("sexo", sexo);
            
            return query.getResultList();
        }catch (Exception e){
            throw new PersistenciaException ("Eror al obtener cliente por categoria sexo en DAO");
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Cliente> ObtenerClientes() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
        
        try{
            String jpql = """
                          Select c
                          From Cliente c
                          """;
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            return query.getResultList();
        }catch (Exception e){
            throw new PersistenciaException ("Eror al obtener cliente por categoria sexo en DAO");
        }finally{
            if (em != null) {
                em.close();
            }
        }
    }
    
}
