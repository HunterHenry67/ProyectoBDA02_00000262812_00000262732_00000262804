/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Resultado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author BALAMRUSH
 */
public class ResultadoDAO implements IResultadoDAO{

    private IConexionBD conexionBD;
    
    public ResultadoDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public Resultado registrarResultado(Resultado resultado) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(resultado);
            entityManager.getTransaction().commit();
            return resultado;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al registrar el resultado: "+ex.getMessage());
        }finally{
            entityManager.close();
        }
    }

    @Override
    public Resultado consultarResultadoPorID(Integer idResultado) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Resultado> consultarResultadoPorPrueba() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean resultadoExiste(Integer idPrueba, Integer idParametro) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
