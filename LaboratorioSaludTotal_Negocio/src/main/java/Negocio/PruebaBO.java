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

    /**
     * Valida y agrega una nueva prueba medica.
     *
     * @param dtoNuevo datos de la prueba que deseas agregar.
     * @return prueba agregada en formato DTO.
     * @throws NegocioException si la prueba no tiene cliente, doctor o ocurre
     * algun error al registrar.
     */
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

    /**
     * Valida y elimina la prueba seleccionada mediante su identificador.
     *
     * @param idPrueba identificador de la prueba que deseas eliminar.
     * @return prueba eliminada en formato DTO.
     * @throws NegocioException si el identificador no es valido, no se
     * encuentra la prueba o ocurre algun error al eliminar.
     */
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

    /**
     * Busca una prueba mediante su identificador.
     *
     * @param idPrueba identificador de la prueba que deseas buscar.
     * @return prueba encontrada en formato DTO.
     * @throws NegocioException si el identificador no es valido, no se
     * encuentra la prueba o ocurre algun error al buscar.
     */
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

    /**
     * Consulta todas las pruebas registradas.
     *
     * @return lista de pruebas encontradas en formato DTO.
     * @throws NegocioException si ocurre algun error al consultar las pruebas.
     */
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

    /**
     * Convierte una entidad Prueba a PruebaDTO.
     *
     * @param entidad prueba que deseas convertir.
     * @return prueba convertida a DTO.
     */
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

    /**
     * Convierte un PruebaDTO a entidad Prueba.
     *
     * @param dto prueba que deseas convertir.
     * @return entidad prueba creada desde el DTO.
     */
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
