/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ConexionBD;
import DAO.DoctorDAO;
import DAO.IConexionBD;
import DAO.IDoctorDAO;
import DAO.PersistenciaException;
import Entidades.Cliente;
import Entidades.Doctor;
import Entidades.Sexo;
import java.util.List;

/**
 *
 * @author Andre
 */
public class DoctorBO implements IDoctorBO {

    private IDoctorDAO doctorDAO;
    
    public DoctorBO(){
        this.doctorDAO = new DoctorDAO();
    }
    
    @Override
    public Doctor consultarPorID(Integer idDoctor) throws NegocioException {
        try {
            if (idDoctor <= 0){
                throw new NegocioException ("Error en DoctorBO, ID invalido");
            }
            
            Doctor doctor = doctorDAO.consultarPorID(idDoctor);
            if (doctor == null){
            throw new NegocioException ("Error en DoctorBO, el id no puede ser nulo");
            }
            
            
            return doctor;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException ("Error al conectar negocio con dao");

        }

    }

    @Override
    public List<Doctor> consultarTodos() throws NegocioException {
        try {
            List<Doctor> doctores = doctorDAO.consultarTodos();

            if (doctores == null) {
                throw new NegocioException("No fue posible obtener la lista de doctores.");
            }

            return doctores;

        } catch (PersistenciaException e) {

            throw new NegocioException("Error al consultar los doctores.", e);
        }
    }

    @Override
    public List<Doctor> buscarPorNombres(String nombres) throws NegocioException {
        try{
            List<Doctor> doctores = doctorDAO.buscarPorNombres(nombres);
            if(doctores==null){
                throw new NegocioException("No fue posible obtener la lista de doctores con esos nombres.");
            }
            return doctores;
        }catch(Exception e){
            throw new NegocioException("Error al consultar por nombres.",e);
        }
    }

    @Override
    public List<Doctor> buscarPorSexo(Sexo sexo) throws NegocioException {
        try{
            List<Doctor> doctores = doctorDAO.buscarPorSexo(sexo);
            if(doctores==null){
                throw new NegocioException("No fue posible obtener la lista de doctores por ese sexo.");
            }
            return doctores;
        }catch(Exception e){
            throw new NegocioException("Error al consultar la lista de doctores por sexo",e);
        }
    }  
}
