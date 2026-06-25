/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Cliente;
import Entidades.Sexo;
import java.time.LocalDateTime;
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
        this.conexion = conexion;
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
            em.close();
        }
        
        
    }

    @Override
    public List<Cliente> buscarClienteNombre(String nombre) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();
    
        try {
            String jpql = "SELECT c FROM Cliente c WHERE c.nombre LIKE :paramNombre";

            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);

            query.setParameter("paramNombre", "%" + nombre + "%");

            return query.getResultList();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por nombre en DAO");
        } finally {
            if (em != null) {
                em.close();
            }
    }

    @Override
    public List<Cliente> buscarClienteFechaNacimiento(LocalDateTime fecha) throws PersistenciaException {
    }

    @Override
    public List<Cliente> buscarClienteTipoSangre(String tipoSangre) throws PersistenciaException {
    }

    @Override
    public List<Cliente> buscarClienteSexo(Sexo sexo) throws PersistenciaException {
    }
    
    }
    
}
