/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Entidades.Doctor;
import Entidades.Sexo;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public interface IDoctorDAO {
    Doctor consultarPorID(Integer idDoctor) throws PersistenciaException;
    List<Doctor> consultarTodos() throws PersistenciaException;
    List<Doctor> buscarPorNombres(String nombres) throws PersistenciaException;
    List<Doctor> buscarPorSexo(Sexo sexo) throws PersistenciaException;
    
}
