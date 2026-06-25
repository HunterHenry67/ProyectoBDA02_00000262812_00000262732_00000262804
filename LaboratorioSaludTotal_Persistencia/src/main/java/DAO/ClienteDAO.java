/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Cliente;
import javax.persistence.EntityManager;

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
    public Cliente buscarClienteId(int id) throws Exception{
        EntityManager em = conexion.conexionBD();
        
        try {
            Cliente cliente = em.find(Cliente.class, id);
            return cliente;
        } catch (Exception e) {
            throw new Exception ("Error al buscar cliente por ID en DAO " + e.getMessage());
        }finally {
            em.close();
        }
        
        
    }
    
}
