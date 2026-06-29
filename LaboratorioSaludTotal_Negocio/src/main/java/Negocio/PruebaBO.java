/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IPruebaDAO;
import DAO.PersistenciaException;
import DAO.PruebaDAO;
import DTO.AnalisisDTO;
import DTO.PruebaDTO;
import Entidades.Analisis;
import Entidades.Cliente;
import Entidades.Doctor;
import Entidades.Prueba;
import java.util.ArrayList;
import java.util.List;

public class PruebaBO implements IPruebaBO {

    private IPruebaDAO pruebaDAO;

    public PruebaBO() {
        this.pruebaDAO = new PruebaDAO();
    }

    @Override
    public PruebaDTO agregarPrueba(PruebaDTO dto) throws NegocioException {
        try {
            Prueba prueba = convertirAEntidad(dto);
            Prueba pruebaGuardada = pruebaDAO.agregarPrueba(prueba);
            return convertirADTO(pruebaGuardada);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al agregar prueba: " + ex.getMessage());
        }
    }

    @Override
    public PruebaDTO buscarPruebaPorId(int idPrueba) throws NegocioException {
        try {
            Prueba prueba = pruebaDAO.buscarPruebaPorId(idPrueba);
            return convertirADTO(prueba);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar prueba: " + ex.getMessage());
        }
    }

    @Override
    public PruebaDTO eliminarPrueba(int idPrueba) throws NegocioException {
        try {
            Prueba prueba = pruebaDAO.eliminarPrueba(idPrueba);
            return convertirADTO(prueba);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar prueba: " + ex.getMessage());
        }
    }

    @Override
    public List<PruebaDTO> consultarTodasLasPruebas() throws NegocioException {
        try {
            List<Prueba> pruebas = pruebaDAO.consultarTodasLasPruebas();
            List<PruebaDTO> pruebasDTO = new ArrayList<>();

            for (Prueba prueba : pruebas) {
                pruebasDTO.add(convertirADTO(prueba));
            }

            return pruebasDTO;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar pruebas: " + ex.getMessage());
        }
    }

    private Prueba convertirAEntidad(PruebaDTO dto) {
        Prueba entidad = new Prueba();

        entidad.setIdPrueba(dto.getIdPrueba());
        entidad.setFechaHora(dto.getFechaHora());

        if (dto.getIdCliente() != null) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(dto.getIdCliente());
            entidad.setCliente(cliente);
        }

        if (dto.getIdDoctor() != null) {
            Doctor doctor = new Doctor();
            doctor.setIdDoctor(dto.getIdDoctor());
            entidad.setDoctor(doctor);
        }

        if (dto.getAnalisisAgregados() != null && !dto.getAnalisisAgregados().isEmpty()) {
            List<Analisis> listaAnalisis = new ArrayList<>();

            for (AnalisisDTO analisisDTO : dto.getAnalisisAgregados()) {
                Analisis analisis = new Analisis();
                analisis.setIdAnalisis(analisisDTO.getIdAnalisis());
                listaAnalisis.add(analisis);
            }

            entidad.setAnalisis(listaAnalisis);
        }

        return entidad;
    }

    private PruebaDTO convertirADTO(Prueba entidad) {
        PruebaDTO dto = new PruebaDTO();

        dto.setIdPrueba(entidad.getIdPrueba());
        dto.setFechaHora(entidad.getFechaHora());

        if (entidad.getCliente() != null) {
            dto.setIdCliente(entidad.getCliente().getIdCliente());
            dto.setNombreCliente(entidad.getCliente().getNombres());
        }

        if (entidad.getDoctor() != null) {
            dto.setIdDoctor(entidad.getDoctor().getIdDoctor());
            dto.setNombreDoctor(entidad.getDoctor().getNombres());
        }

        if (entidad.getAnalisis() != null) {
            List<AnalisisDTO> listaAnalisisDTO = new ArrayList<>();

            for (Analisis analisis : entidad.getAnalisis()) {
                AnalisisDTO analisisDTO = new AnalisisDTO();
                analisisDTO.setIdAnalisis(analisis.getIdAnalisis());
                analisisDTO.setNombre(analisis.getNombre());

                listaAnalisisDTO.add(analisisDTO);
            }

            dto.setAnalisisAgregados(listaAnalisisDTO);
        }

        return dto;
    }
}