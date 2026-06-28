/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTO.DoctorDTO;
import Entidades.Sexo;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IDoctorBO {
    DoctorDTO consultarPorID(Integer idDoctor) throws NegocioException;
    List<DoctorDTO> consultarTodos() throws NegocioException;
    List<DoctorDTO> buscarPorNombres(String nombres) throws NegocioException;
    List<DoctorDTO> buscarPorSexo(Sexo sexo) throws NegocioException;
    
}
