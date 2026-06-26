/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DAO.PersistenciaException;
import Entidades.Doctor;
import Entidades.Sexo;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IDoctorBO {
    Doctor consultarPorID(Integer idDoctor) throws PersistenciaException;
    List<Doctor> consultarTodos() throws PersistenciaException;
    List<Doctor> buscarPorNombres(String nombres) throws PersistenciaException;
    List<Doctor> buscarPorSexo(Sexo sexo) throws PersistenciaException;
    
}
