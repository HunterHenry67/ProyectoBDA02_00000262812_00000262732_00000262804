/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.AnalisisDAO;
import DAO.IAnalisisDAO;
import DAO.IParametroDAO;
import DAO.IRangoDAO;
import DAO.ParametroDAO;
import DAO.PersistenciaException;
import DAO.RangoDAO;
import DTO.ActualizarAnalisisDTO;
import DTO.AnalisisDTO;
import DTO.EliminarAnalisisDTO;
import DTO.GuardarAnalisisDTO;
import DTO.RangoDTO;
import DTO.RegistrarParametroDTO;
import Entidades.Analisis;
import Entidades.Muestra;
import Entidades.Parametro;
import Entidades.Rango;
import Entidades.Sexo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Andre
 */
public class AnalisisBO implements IAnalisisBO {

    private final IAnalisisDAO analisisDAO;
    private final IParametroDAO parametroDAO;
    private final IRangoDAO rangoDAO;

    public AnalisisBO() {
        this.analisisDAO = new AnalisisDAO();
        this.parametroDAO = new ParametroDAO();
        this.rangoDAO = new RangoDAO();
    }

    @Override
    public Analisis guardarAnalisis(GuardarAnalisisDTO guardarAnalisis) throws NegocioException {
        try {
            validarGuardar(guardarAnalisis);
            Analisis analisis = new Analisis();
            analisis.setNombre(guardarAnalisis.getNombre().trim());
            analisis.setNota(guardarAnalisis.getNota().trim());
            Muestra muestra = new Muestra();
            muestra.setIdMuestra(guardarAnalisis.getIdMuestra());
            analisis.setMuestra(muestra);
            Analisis analisisGuardado = analisisDAO.guardar(analisis);
            for (RegistrarParametroDTO parametroDTO : guardarAnalisis.getParametros()) {
                Parametro parametro = new Parametro();
                parametro.setNombre(parametroDTO.getNombre());
                parametro.setOrdenReporte(parametroDTO.getOrdenReporte());
                parametro.setNotaDescriptiva(parametroDTO.getNotaDescriptiva());
                parametro.setUnidadMedida(parametroDTO.getUnidadMedida());
                parametro.setAnalisis(analisisGuardado);
                Parametro parametroGuardado = parametroDAO.registarParametro(parametro);
                for (RangoDTO rangoDTO : parametroDTO.getRangos()) {
                    Rango rango = new Rango();
                    rango.setSexo(convertirSexo(rangoDTO.getSexo()));
                    rango.setEdadInicial(rangoDTO.getEdadInicial());
                    rango.setEdadFinal(rangoDTO.getEdadFinal());
                    rango.setRangoIncial(rangoDTO.getRangoInicial());
                    rango.setRangoFinal(rangoDTO.getRangoFinal());
                    rango.setParametro(parametroGuardado);
                    rangoDAO.agregarRango(rango);
                }
            }
            return analisisGuardado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al guardar el análisis: " + ex.getMessage());
        }
    }

    @Override
    public Analisis actualizarAnalisis(ActualizarAnalisisDTO actualizarAnalisis) throws NegocioException {
        try {
            validarActualizar(actualizarAnalisis);

            Analisis analisis = analisisDAO.consultarPorId(actualizarAnalisis.getIdAnalisis());

            if (analisis == null) {
                throw new NegocioException("No existe el análisis que se desea actualizar.");
            }

            analisis.setNombre(actualizarAnalisis.getNombre().trim());
            analisis.setNota(actualizarAnalisis.getNota().trim());

            Muestra muestra = new Muestra();
            muestra.setIdMuestra(actualizarAnalisis.getIdMuestra());

            analisis.setMuestra(muestra);

            return analisisDAO.actualizar(analisis);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar el análisis: " + ex.getMessage());
        }
    }

    @Override
    public void eliminarAnalisis(EliminarAnalisisDTO eliminarAnalisis) throws NegocioException {
        try {
            validarEliminar(eliminarAnalisis);

            Analisis analisis = analisisDAO.consultarPorId(eliminarAnalisis.getIdAnalisis());

            if (analisis == null) {
                throw new NegocioException("No existe el análisis que se desea eliminar.");
            }

            analisisDAO.eliminar(eliminarAnalisis.getIdAnalisis());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el análisis: " + ex.getMessage());
        }
    }

    @Override
    public Analisis consultarPorId(Integer idAnalisis) throws NegocioException {
        try {
            if (idAnalisis == null || idAnalisis <= 0) {
                throw new NegocioException("El ID del análisis no es válido.");
            }

            Analisis analisis = analisisDAO.consultarPorId(idAnalisis);

            if (analisis == null) {
                throw new NegocioException("No se encontró el análisis.");
            }

            return analisis;

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar el análisis: " + ex.getMessage());
        }
    }

    @Override
    public List<AnalisisDTO> consultarTodos() throws NegocioException {
        try {
            List<Analisis> listaAnalisis = analisisDAO.consultarTodos();
            return convertirATablaDTO(listaAnalisis);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar todos los análisis: " + ex.getMessage());
        }
    }

    @Override
    public List<AnalisisDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return consultarTodos();
            }
            List<Analisis> listaAnalisis = analisisDAO.buscarPorNombre(nombre.trim());
            return convertirATablaDTO(listaAnalisis);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar análisis por nombre: " + ex.getMessage());
        }
    }

    private void validarGuardar(GuardarAnalisisDTO guardarAnalisis) throws NegocioException {
        if (guardarAnalisis == null) {
            throw new NegocioException("Favor de ingresar datos válidos.");
        }

        if (guardarAnalisis.getNombre() == null || guardarAnalisis.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del análisis es obligatorio.");
        }

        if (guardarAnalisis.getNota() == null || guardarAnalisis.getNota().trim().isEmpty()) {
            throw new NegocioException("La nota del análisis es obligatoria.");
        }

        if (guardarAnalisis.getIdMuestra() == null || guardarAnalisis.getIdMuestra() <= 0) {
            throw new NegocioException("Debe seleccionar un tipo de muestra válido.");
        }
        List<Integer> ordenesUsados = new ArrayList<>();
        for (RegistrarParametroDTO parametro : guardarAnalisis.getParametros()) {
            if (ordenesUsados.contains(parametro.getOrdenReporte())) {
                throw new NegocioException( "No puedes repetir el orden de reporte.");
            }
            ordenesUsados.add(parametro.getOrdenReporte());
        }
    }

    private void validarActualizar(ActualizarAnalisisDTO actualizarAnalisis) throws NegocioException {
        if (actualizarAnalisis == null) {
            throw new NegocioException("Favor de ingresar datos válidos.");
        }

        if (actualizarAnalisis.getIdAnalisis() == null || actualizarAnalisis.getIdAnalisis() <= 0) {
            throw new NegocioException("El ID del análisis no es válido.");
        }

        if (actualizarAnalisis.getNombre() == null || actualizarAnalisis.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del análisis es obligatorio.");
        }

        if (actualizarAnalisis.getNota() == null || actualizarAnalisis.getNota().trim().isEmpty()) {
            throw new NegocioException("La nota del análisis es obligatoria.");
        }

        if (actualizarAnalisis.getIdMuestra() == null || actualizarAnalisis.getIdMuestra() <= 0) {
            throw new NegocioException("Debe seleccionar un tipo de muestra válido.");
        }
    }

    private void validarEliminar(EliminarAnalisisDTO eliminarAnalisis) throws NegocioException {
        if (eliminarAnalisis == null) {
            throw new NegocioException("Favor de ingresar datos válidos.");
        }

        if (eliminarAnalisis.getIdAnalisis() == null || eliminarAnalisis.getIdAnalisis() <= 0) {
            throw new NegocioException("El ID del análisis no es válido.");
        }
    }

    @Override
    public Integer contarParametro(Integer idAnalisis) throws NegocioException {
        try {
            if (idAnalisis == null || idAnalisis <= 0) {
                throw new NegocioException("No se encontró el id del Analisis.");
            }

            Integer conteoParametro = analisisDAO.contarParametros(idAnalisis);
            return conteoParametro;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al contar los parámetros: " + ex.getMessage());
        }
    }

    private List<AnalisisDTO> convertirATablaDTO(List<Analisis> listaAnalisis) throws NegocioException {
        try {
            List<AnalisisDTO> listaDTO = new ArrayList<>();
            for (Analisis analisis : listaAnalisis) {
                AnalisisDTO dto = new AnalisisDTO();
                dto.setIdAnalisis(analisis.getIdAnalisis());
                dto.setNombre(analisis.getNombre());
                if (analisis.getMuestra() != null) {
                    dto.setTipoMuestra(analisis.getMuestra().getNombre());
                } else {
                    dto.setTipoMuestra("N/A");
                }
                Integer cantidadParametros = analisisDAO.contarParametros(analisis.getIdAnalisis());
                dto.setCantidadParametros(cantidadParametros);
                listaDTO.add(dto);
            }
            return listaDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conevrtir entidad análisis a DTO: " + ex.getMessage());
        }
    }

    @Override
    public List<AnalisisDTO> buscarPorTipoMuestra(String tipoMuestra) throws NegocioException {
        try {
            if (tipoMuestra == null || tipoMuestra.isEmpty()) {
                return consultarTodos();
            }
            List<Analisis> analisis = analisisDAO.buscarPorTipoMuestra(tipoMuestra.trim());
            return convertirATablaDTO(analisis);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar el análisis por tipo de muestra: " + ex.getMessage());
        }
    }

    @Override
    public List<AnalisisDTO> buscarPorCantidadParametro(Integer cantidad) throws NegocioException {
        try {
            if (cantidad == null || cantidad < 0) {
                throw new NegocioException("Error al buscar el analisis por parámetro.");
            }
            List<Analisis> analisis = analisisDAO.buscarPorCantidadParametro(cantidad);
            return convertirATablaDTO(analisis);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar el análisis por cantidad de parámetro: " + ex.getMessage());
        }
    }

    private Sexo convertirSexo(String sexo) throws NegocioException {
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new NegocioException("El sexo del rango no es válido.");
        }
        String sexoNormalizado = sexo.trim().toUpperCase();
        switch (sexoNormalizado) {
            case "MASCULINO":
                return Sexo.MASCULINO;

            case "FEMENINO":
                return Sexo.FEMENINO;
            default:
                throw new NegocioException("Sexo no válido: " + sexo);
        }
    }
}
