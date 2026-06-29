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
import DTO.DoctorDTO;
import Entidades.Cliente;
import Entidades.Doctor;
import Entidades.Sexo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */

public class DoctorBO implements IDoctorBO {

    private IDoctorDAO doctorDAO;

    public DoctorBO() {
        this.doctorDAO = new DoctorDAO();
    }

    /**
     * 
     * @param idDoctor
     * @return
     * @throws NegocioException 
     */
    @Override
    public DoctorDTO consultarPorID(Integer idDoctor) throws NegocioException {
        try {
            if (idDoctor <= 0) {
                throw new NegocioException("Error en DoctorBO, ID invalido");
            }

            Doctor doctor = doctorDAO.consultarPorID(idDoctor);
            if (doctor == null) {
                throw new NegocioException("Error en DoctorBO, el id no puede ser nulo");
            }

            return convertirADTO(doctor);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con dao");
        }
    }

    /**
     * 
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<DoctorDTO> consultarTodos() throws NegocioException {
        try {
            List<Doctor> doctores = doctorDAO.consultarTodos();

            if (doctores == null) {
                throw new NegocioException("No fue posible obtener la lista de doctores.");
            }

            return convertirListaADTO(doctores);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar los doctores.", e);
        }
    }

    /**
     * 
     * @param nombres
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<DoctorDTO> buscarPorNombres(String nombres) throws NegocioException {
        try {
            List<Doctor> doctores = doctorDAO.buscarPorNombres(nombres);
            if (doctores == null) {
                throw new NegocioException("No fue posible obtener la lista de doctores con esos nombres.");
            }
            return convertirListaADTO(doctores);
        } catch (Exception e) {
            throw new NegocioException("Error al consultar por nombres.", e);
        }
    }

    /**
     * 
     * @param sexo
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<DoctorDTO> buscarPorSexo(Sexo sexo) throws NegocioException {
        try {
            List<Doctor> doctores = doctorDAO.buscarPorSexo(sexo);
            if (doctores == null) {
                throw new NegocioException("No fue posible obtener la lista de doctores por ese sexo.");
            }
            return convertirListaADTO(doctores);
        } catch (Exception e) {
            throw new NegocioException("Error al consultar la lista de doctores por sexo", e);
        }
    }

    /**
     * 
     * @param doctor
     * @return 
     */
    private DoctorDTO convertirADTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setIdDoctor(doctor.getIdDoctor());
        dto.setNombres(doctor.getNombres());
        dto.setApellidoPaterno(doctor.getApellidoPaterno());
        dto.setApellidoMaterno(doctor.getApellidoMaterno());

        if (doctor.getSexo() != null) {
            dto.setSexo(doctor.getSexo().name());
        }

        if (doctor.getListaPruebas() != null) {
            List<Integer> ids = doctor.getListaPruebas().stream()
                    .map(prueba -> prueba.getIdPrueba())
                    .toList();
            dto.setIdsPruebas(ids);
        }

        return dto;
    }

    /**
     * 
     * @param doctores
     * @return 
     */
    private List<DoctorDTO> convertirListaADTO(List<Doctor> doctores) {
        List<DoctorDTO> dtos = new ArrayList<>();
        for (Doctor doctor : doctores) {
            dtos.add(convertirADTO(doctor));
        }
        return dtos;
    }
}