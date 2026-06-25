/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author BALAMRUSH
 */
public class ParametroDAO implements IParametroDAO{
    
    private final IConexionBD conexionBD;
    
    public ParametroDAO(IConexionBD conexionBD){
        this.conexionBD =  conexionBD;
    }

    @Override
    public Parametro registarParametro(Parametro nuevoParametro) throws PersistenceException {
        EntityManager crearConexionBD = conexionBD.conexionBD();
        try{
            crearConexionBD.getTransaction().begin();
            crearConexionBD.persist(nuevoParametro);
            crearConexionBD.getTransaction().commit();
            return nuevoParametro;
        }catch(Exception ex){
            crearConexionBD.getTransaction().rollback();
            throw new PersistenceException("Error al agregar el parámtero: ");
        }finally{
            crearConexionBD.close();
        }
    }

    @Override
    public List<Parametro> listar(String filtro) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarParametro(Integer idParametro) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Parametro consultarParametroPorID(Integer idParametro) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
