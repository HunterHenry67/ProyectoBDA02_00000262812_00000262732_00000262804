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
    public PruebaDTO agregarPrueba(PruebaDTO dtoNuevo) throws NegocioException {
        try {
            if (dtoNuevo.getIdCliente() == null || dtoNuevo.getIdDoctor() == null) {
                throw new NegocioException("Error: la prueba debe tener un cliente y un doctor.");
            }

            if (dtoNuevo.getAnalisisAgregados() == null || dtoNuevo.getAnalisisAgregados().isEmpty()) {
                throw new NegocioException("Error: la prueba debe tener al menos un análisis.");
            }

            Prueba entidadNueva = convertirAEntidad(dtoNuevo);
            Prueba entidadGuardada = pruebaDAO.agregarPrueba(entidadNueva);

            return convertirADTO(entidadGuardada);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la prueba médica.", e);
        }
    }

    @Override
    public PruebaDTO eliminarPrueba(int idPrueba) throws NegocioException {
        try {
            if (idPrueba <= 0) {
                throw new NegocioException("Error: id de prueba inválido.");
            }

            Prueba pruebaEliminar = pruebaDAO.eliminarPrueba(idPrueba);

            if (pruebaEliminar == null) {
                throw new NegocioException("Error: no se encontró la prueba a eliminar.");
            }

            return convertirADTO(pruebaEliminar);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al eliminar la prueba médica.", e);
        }
    }

    @Override
    public PruebaDTO buscarPruebaPorId(int idPrueba) throws NegocioException {
        try {
            if (idPrueba <= 0) {
                throw new NegocioException("Error: id inválido.");
            }

            Prueba pruebaBuscada = pruebaDAO.buscarPruebaPorId(idPrueba);

            if (pruebaBuscada == null) {
                throw new NegocioException("Error: no se encontró ninguna prueba con ese id.");
            }

            return convertirADTO(pruebaBuscada);

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar prueba por id.", e);
        }
    }

    @Override
    public List<PruebaDTO> consultarTodasLasPruebas() throws NegocioException {
        try {
            List<Prueba> listaEntidades = pruebaDAO.consultarTodasLasPruebas();
            List<PruebaDTO> listaDTOs = new ArrayList<>();

            for (Prueba entidad : listaEntidades) {
                listaDTOs.add(convertirADTO(entidad));
            }

            return listaDTOs;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al consultar las pruebas.", e);
        }
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

                if (analisis.getMuestra() != null) {
                    analisisDTO.setTipoMuestra(analisis.getMuestra().getNombre());
                }

                listaAnalisisDTO.add(analisisDTO);
            }

            dto.setAnalisisAgregados(listaAnalisisDTO);
        }

        return dto;
    }

    private Prueba convertirAEntidad(PruebaDTO dto) {
        Prueba entidad = new Prueba();

        entidad.setIdPrueba(dto.getIdPrueba());
        entidad.setFechaHora(dto.getFechaHora());

        if (dto.getIdCliente() != null) {
            Cliente clienteRelacionado = new Cliente();
            clienteRelacionado.setIdCliente(dto.getIdCliente());
            entidad.setCliente(clienteRelacionado);
        }

        if (dto.getIdDoctor() != null) {
            Doctor doctorRelacionado = new Doctor();
            doctorRelacionado.setIdDoctor(dto.getIdDoctor());
            entidad.setDoctor(doctorRelacionado);
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
}