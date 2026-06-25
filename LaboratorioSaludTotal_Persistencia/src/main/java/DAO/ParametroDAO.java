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
    public Parametro registarParametro(Parametro nuevoParametro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(nuevoParametro);
            entityManager.getTransaction().commit();
            return nuevoParametro;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error al agregar el parámtero, hubo un rollback: ");
        }finally{
            entityManager.close();
        }
    }

    @Override
    public List<Parametro> listar(String filtro) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarParametro(Integer idParametro) throws PersistenciaException{
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            entityManager.getTransaction().begin();
            Parametro parametroEliminado = this.consultarParametroPorID(idParametro);
            entityManager.remove(parametroEliminado);
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar el parámetro, hubo un rollback: ");
        }finally{
            
        }
    }

    @Override
    public Parametro consultarParametroPorID(Integer idParametro) throws PersistenciaException{
        EntityManager entityManager = conexionBD.conexionBD();
        try{
            Parametro parametroEncontrado = entityManager.find(Parametro.class, idParametro);
            if(parametroEncontrado == null){
                throw new Exception("El parametro no existe");
            }
            return parametroEncontrado;
        }catch(Exception ex){
            throw new PersistenciaException("Error al consultar el paraemtro por ID:");
        }finally{
            entityManager.close();
        }
    }
    
}
