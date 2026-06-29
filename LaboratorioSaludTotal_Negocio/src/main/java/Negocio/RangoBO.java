/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IRangoDAO;
import DAO.RangoDAO;
import DTO.RangoDTO;
import Entidades.Rango;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class RangoBO implements IRangoBO {

    private IRangoDAO rangoDAO;

    public RangoBO() {
        this.rangoDAO = new RangoDAO();
    }

    /**
     * Valida y agrega un nuevo rango.
     *
     * @param rango rango que deseas agregar.
     * @return rango agregado en formato DTO.
     * @throws NegocioException si los datos no son validos o ocurre algun error
     * al agregar.
     */
    @Override
    public RangoDTO agregarRango(Rango rango) throws NegocioException {
        try {
            if (rango == null) {
                throw new NegocioException("Error al afregar Rango en DAO");
            }
            Rango rangoDevolucion = rangoDAO.agregarRango(rango);

            return convertirADTO(rangoDevolucion);
        } catch (Exception e) {
            throw new NegocioException("Error al agregar rango en Negocio");
        }
    }

    /**
     * Valida y elimina el rango seleccionado.
     *
     * @param idRango identificador del rango que deseas eliminar.
     * @return rango eliminado en formato DTO.
     * @throws NegocioException si el identificador no es valido o ocurre algun
     * error al eliminar.
     */
    @Override
    public RangoDTO eliminarRango(int idRango) throws NegocioException {
        try {
            if (idRango <= 0) {
                throw new NegocioException("Error, ingrese id valido");
            }
            Rango rng = rangoDAO.eliminarRango(idRango);

            return convertirADTO(rng);
        } catch (Exception e) {
            throw new NegocioException("Error al eliminar rango en Bo");
        }
    }

    /**
     * Busca los rangos que pertenecen a un parametro.
     *
     * @param idParametro identificador del parametro.
     * @return lista de rangos encontrados.
     * @throws NegocioException si el identificador no es valido o ocurre algun
     * error al buscar.
     */
    @Override
    public List<RangoDTO> buscarRangosPorParametro(int idParametro) throws NegocioException {
        try {
            if (idParametro <= 0) {
                throw new NegocioException("Error, ingrese un id de parametro valido");
            }
            List<Rango> listaRng = rangoDAO.buscarRangosPorParametro(idParametro);
            List<RangoDTO> listaDTO = new ArrayList<>();

            for (Rango rg : listaRng) {
                RangoDTO dto = convertirADTO(rg);
                listaDTO.add(dto);
            }
            return listaDTO;
        } catch (Exception e) {
            throw new NegocioException("Error en buscar rangos por parametro en BO");
        }
    }

    /**
     * Cuenta los rangos que pertenecen a un parametro.
     *
     * @param idParametro identificador del parametro.
     * @param sexo sexo recibido para la busqueda.
     * @param edad edad recibida para la busqueda.
     * @return cantidad de rangos encontrados.
     * @throws NegocioException si el identificador no es valido o ocurre algun
     * error al contar.
     */
    @Override
    public long obtenerRangosPorParametro(int idParametro, String sexo, int edad) throws NegocioException {
        try {
            if (idParametro <= 0) {
                throw new NegocioException("Error: El ID del parámetro debe ser mayor a cero.");
            }

            long totalRangos = rangoDAO.obtenerRangosPorParametro(idParametro, sexo, edad);

            return totalRangos;

        } catch (Exception e) {
            throw new NegocioException("Error en Negocio al contar los rangos del parámetro");
        }
    }

    /**
     * Convierte una entidad Rango a DTO.
     *
     * @param entidadRango rango que deseas convertir.
     * @return rango convertido a DTO.
     */
    private RangoDTO convertirADTO(Rango entidadRango) {
        RangoDTO dto = new RangoDTO();

        dto.setIdRango(entidadRango.getIdRango());
        dto.setEdadInicial(entidadRango.getEdadInicial());
        dto.setEdadFinal(entidadRango.getEdadFinal());

        dto.setRangoInicial(entidadRango.getRangoIncial());
        dto.setRangoFinal(entidadRango.getRangoFinal());
        if (entidadRango.getSexo() != null) {
            dto.setSexo(entidadRango.getSexo().toString());
        }

        return dto;
    }

}
