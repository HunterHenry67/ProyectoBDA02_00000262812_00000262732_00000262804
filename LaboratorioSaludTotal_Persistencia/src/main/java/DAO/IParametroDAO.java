/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Parametro;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author BALAMRUSH
 */
public interface IParametroDAO {

    Parametro registarParametro(Parametro nuevoParametro) ;

    List<Parametro> listar(String filtro) throws PersistenceException;

    void eliminarParametro(Integer idParametro) throws PersistenceException;

    Parametro consultarParametroPorID(Integer idParametro) throws PersistenceException;
}
