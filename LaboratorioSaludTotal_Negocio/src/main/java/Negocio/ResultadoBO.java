/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IParametroDAO;
import DAO.IPruebaDAO;
import DAO.IResultadoDAO;
import DAO.PersistenciaException;
import DTO.RegistrarResultadoDTO;
import DTO.ResultadoDTO;
import Entidades.Parametro;
import Entidades.Prueba;
import Entidades.Resultado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author BALAMRUSH
 */
public class ResultadoBO implements IResultadoBO {

    private final IResultadoDAO resultadoDAO;
    private final IPruebaDAO pruebaDAO;
    private final IParametroDAO parametroDAO;

    public ResultadoBO(IResultadoDAO resultadoDAO, IPruebaDAO pruebaDAO, IParametroDAO parametroDAO) {
        this.resultadoDAO = resultadoDAO;
        this.pruebaDAO = pruebaDAO;
        this.parametroDAO = parametroDAO;
    }

    /**
     * Valida y registra un nuevo resultado.
     *
     * @param dto datos del resultado que deseas registrar.
     * @return resultado registrado.
     * @throws NegocioException si los datos no son validos o ocurre algun error
     * al registrar.
     */
    @Override
    public Resultado registrarResultado(RegistrarResultadoDTO dto) throws NegocioException {
        validarRegistroResultado(dto);

        try {
            Prueba prueba = pruebaDAO.buscarPruebaPorId(dto.getIdPrueba());

            if (prueba == null) {
                throw new NegocioException("No existe la prueba seleccionada.");
            }

            Parametro parametro = parametroDAO.consultarParametroPorID(dto.getIdParametro());

            if (parametro == null) {
                throw new NegocioException("No existe el parámetro seleccionado.");
            }

            if (resultadoDAO.resultadoExiste(dto.getIdPrueba(), dto.getIdParametro())) {
                throw new NegocioException("Ya existe un resultado registrado para esta prueba y este parámetro.");
            }

            Resultado resultado = convertirAEntidad(dto, prueba, parametro);

            return resultadoDAO.registrarResultado(resultado);

        } catch (PersistenciaException | PersistenceException ex) {
            throw new NegocioException("Error al registrar el resultado: " + ex.getMessage());
        }
    }

    /**
     * Consulta un resultado mediante su identificador.
     *
     * @param idResultado identificador del resultado que deseas consultar.
     * @return resultado encontrado.
     * @throws NegocioException si el ID no es valido o ocurre algun error al
     * consultar.
     */
    @Override
    public Resultado consultarResultadoPorID(Integer idResultado) throws NegocioException {
        if (idResultado == null || idResultado <= 0) {
            throw new NegocioException("El ID del resultado no es válido.");
        }

        try {
            Resultado resultado = resultadoDAO.consultarResultadoPorID(idResultado);

            if (resultado == null) {
                throw new NegocioException("No se encontró el resultado solicitado.");
            }

            return resultado;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el resultado: " + ex.getMessage());
        }
    }

    /**
     * Consulta los resultados de una prueba para mostrarlos en tabla.
     *
     * @param idPrueba identificador de la prueba.
     * @return lista de resultados encontrados en formato DTO.
     * @throws NegocioException si el ID no es valido o ocurre algun error al
     * consultar.
     */
    @Override
    public List<ResultadoDTO> consultarTablaPorPrueba(Integer idPrueba) throws NegocioException {
        if (idPrueba == null || idPrueba <= 0) {
            throw new NegocioException("El ID de la prueba no es válido.");
        }

        try {
            Prueba prueba = pruebaDAO.buscarPruebaPorId(idPrueba);

            if (prueba == null) {
                throw new NegocioException("No existe la prueba seleccionada.");
            }

            List<Resultado> resultados = resultadoDAO.consultarResultadoPorPrueba(idPrueba);

            return convertirListaADTO(resultados);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener la tabla de resultados: " + ex.getMessage());
        }
    }

    /**
     * Consulta un resultado mediante la prueba y el parametro.
     *
     * @param idPrueba identificador de la prueba.
     * @param idParametro identificador del parametro.
     * @return resultado encontrado.
     * @throws NegocioException si algun identificador no es valido o ocurre
     * algun error al buscar.
     */
    @Override
    public Resultado consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws NegocioException {
        if (idPrueba == null || idPrueba <= 0) {
            throw new NegocioException("El ID de la prueba no es válido.");
        }

        if (idParametro == null || idParametro <= 0) {
            throw new NegocioException("El ID del parámetro no es válido.");
        }

        try {
            return resultadoDAO.consultarResultadoPorPruebaParametro(idPrueba, idParametro);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar el resultado específico: " + ex.getMessage());
        }
    }

    /**
     * Verifica si ya existe un resultado para una prueba y un parametro.
     *
     * @param idPrueba identificador de la prueba.
     * @param idParametro identificador del parametro.
     * @return true si el resultado ya existe, false en caso contrario.
     * @throws NegocioException si algun identificador no es valido o ocurre
     * algun error al verificar.
     */
    @Override
    public boolean resultadoExiste(Integer idPrueba, Integer idParametro) throws NegocioException {
        if (idPrueba == null || idPrueba <= 0) {
            throw new NegocioException("El ID de la prueba no es válido.");
        }

        if (idParametro == null || idParametro <= 0) {
            throw new NegocioException("El ID del parámetro no es válido.");
        }

        try {
            return resultadoDAO.resultadoExiste(idPrueba, idParametro);

        } catch (PersistenceException ex) {
            throw new NegocioException("Error al verificar si el resultado existe: " + ex.getMessage());
        }
    }

    /**
     * Valida los datos necesarios para registrar un resultado.
     *
     * @param registro datos del resultado que deseas validar.
     * @throws NegocioException si algun dato obligatorio no es valido.
     */
    private void validarRegistroResultado(RegistrarResultadoDTO registro) throws NegocioException {
        if (registro == null) {
            throw new NegocioException("Los datos de entrada no pueden ser nulos.");
        }

        if (registro.getIdPrueba() == null || registro.getIdPrueba() <= 0) {
            throw new NegocioException("Debe ingresar una prueba válida.");
        }

        if (registro.getIdParametro() == null || registro.getIdParametro() <= 0) {
            throw new NegocioException("Debe ingresar un parámetro válido.");
        }

        if (registro.getResultadoObtenido() == null) {
            throw new NegocioException("El resultado obtenido es obligatorio.");
        }

        if (registro.getObservacion() == null) {
            registro.setObservacion("");
        }
    }

    /**
     * Convierte un DTO de registro a entidad Resultado.
     *
     * @param dto datos del resultado que deseas convertir.
     * @param prueba prueba relacionada con el resultado.
     * @param parametro parametro relacionado con el resultado.
     * @return resultado convertido a entidad.
     */
    private Resultado convertirAEntidad(RegistrarResultadoDTO dto, Prueba prueba, Parametro parametro) {
        Resultado entidad = new Resultado();

        entidad.setResultadoObtenido(dto.getResultadoObtenido());

        if (dto.getObservacion() != null) {
            entidad.setObservacion(dto.getObservacion().trim());
        } else {
            entidad.setObservacion("");
        }

        entidad.setPrueba(prueba);
        entidad.setParametro(parametro);

        return entidad;
    }

    /**
     * Convierte una entidad Resultado a ResultadoDTO.
     *
     * @param entidad resultado que deseas convertir.
     * @return resultado convertido a DTO.
     */
    private ResultadoDTO convertirADTO(Resultado entidad) {
        ResultadoDTO dto = new ResultadoDTO();

        dto.setIdResultado(entidad.getIdResultado());
        dto.setResultadoObtenido(entidad.getResultadoObtenido());
        dto.setObservacion(entidad.getObservacion());

        if (entidad.getPrueba() != null) {
            dto.setIdPrueba(entidad.getPrueba().getIdPrueba());
        }

        if (entidad.getParametro() != null) {
            dto.setIdParametro(entidad.getParametro().getIdParametro());
        }

        return dto;
    }

    /**
     * Convierte una lista de resultados a una lista de DTO.
     *
     * @param resultados lista de resultados que deseas convertir.
     * @return lista de resultados convertidos a DTO.
     */
    private List<ResultadoDTO> convertirListaADTO(List<Resultado> resultados) {
        List<ResultadoDTO> listaDTO = new ArrayList<>();

        if (resultados == null) {
            return listaDTO;
        }

        for (Resultado resultado : resultados) {
            ResultadoDTO dto = convertirADTO(resultado);
            listaDTO.add(dto);
        }

        return listaDTO;
    }
}
