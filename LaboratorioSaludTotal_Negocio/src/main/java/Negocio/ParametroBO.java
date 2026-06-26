/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IAnalisisDAO;
import DAO.IParametroDAO;
import DAO.PersistenciaException;
import DTO.ParametroDTO;
import DTO.RegistrarParametroDTO;
import Entidades.Analisis;
import Entidades.Parametro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class ParametroBO implements IParametroBO {

    private IParametroDAO parametroDAO;
    private IAnalisisDAO analisisDAO;

    public ParametroBO(IParametroDAO parametroDAO, IAnalisisDAO analisisDAO) {
        this.parametroDAO = parametroDAO;
        this.analisisDAO = analisisDAO;
    }

    @Override
    public Parametro registarParametro(RegistrarParametroDTO registrarParametro) throws NegocioException {
        try{
            this.validarGuardado(registrarParametro);
            Analisis analisis = analisisDAO.consultarPorId(registrarParametro.getIdAnalisis());
            if(analisis == null){
                throw new NegocioException("No existe el analisis que se seleccionó.");
            }
            Parametro parametro = new Parametro();
            parametro.setNombre(registrarParametro.getNombre());
            parametro.setOrdenReporte(registrarParametro.getOrdenReporte());
            parametro.setNotaDescriptiva(registrarParametro.getNotaDescriptiva());
            parametro.setUnidadMedida(registrarParametro.getUnidadMedida());
            parametro.setAnalisis(analisis);           
            return parametroDAO.registarParametro(parametro);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al registrar el parámetro: "+ex.getMessage());
        }
    }

    @Override
    public List<ParametroDTO> listarTodos() throws NegocioException {
        try{
            List<Parametro> parametros = parametroDAO.listarTodos();
            return conversionADTO(parametros);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al lista todos los parámetros.");
        }
    }

    @Override
    public void eliminarParametro(Integer idParametro) throws NegocioException {
        try{
            if(idParametro == null || idParametro <= 0){
                throw new NegocioException("El ID del parametro no es válido.");
            }
            parametroDAO.eliminarParametro(idParametro);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al eliminar el parámetro: "+ex.getMessage());
        }
    }

    @Override
    public Parametro consultarParametroPorID(Integer idParametro) throws NegocioException {
        try{
            if(idParametro == null || idParametro <= 0){
                throw new NegocioException("El ID del parámetro no es válido.");
            }
            return parametroDAO.consultarParametroPorID(idParametro);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al consultar el parámetro por ID: "+ex.getMessage());
        }
    }

    @Override
    public List<ParametroDTO> consultarParametroPorNombre(String nombre) throws NegocioException {
        try{
            if(nombre == null || nombre.trim().isEmpty()){
                return listarTodos();
            }
            List<Parametro> parametros = parametroDAO.consultarParametroPorNombre(nombre.trim());
            return conversionADTO(parametros);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al consultar el parámetro por nombre: "+ex.getMessage());
        }
    }

    @Override
    public List<ParametroDTO> consultarParametroPorOrden(Integer orden) throws NegocioException {
        try{
            if(orden == null || orden  <= 0){
                throw new NegocioException("El orden debe ser mayor a 0.");
            }
            List<Parametro> parametros = parametroDAO.consultarParametroPorOrden(orden);
            return conversionADTO(parametros);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al buscar el parametro por orden: "+ex.getMessage());
        }
    }

    @Override
    public List<ParametroDTO> consultarParametroPorUnidadMedidad(String unidadMedida) throws NegocioException {
        try{
            if(unidadMedida == null || unidadMedida.trim().isEmpty()){
                throw new NegocioException("La unidad de medidad es obligatoria.");
            }
            List<Parametro> parametros = parametroDAO.consultarParametroPorUnidadMedidad(unidadMedida);
            return conversionADTO(parametros);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al consultar el parámetro por unidad de medidad: "+ex.getMessage());
        }
    }

    @Override
    public List<ParametroDTO> consultarParametroPorCantidadRango(Integer rangos) throws NegocioException {
        try{
            if(rangos == 0 || rangos < 0){
                throw new NegocioException("La cantidad del rango no es válida.");
            }
            List<Parametro> parametros = parametroDAO.consultarParametroPorCantidadRango(rangos);
            return conversionADTO(parametros);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al consultar el parámetro por rango: "+ex.getMessage());
        }
    }

    @Override
    public Integer contarRangos(Integer idParametro) throws NegocioException {
        try{
            if(idParametro == null || idParametro <= 0){
                throw new NegocioException("El ID del parámetro no es válido.");
            }
            return parametroDAO.contarRangos(idParametro);
        }catch(PersistenciaException ex){
            throw new NegocioException("Error al contar los rangos: "+ex.getMessage());
        }
    }

    private void validarGuardado(RegistrarParametroDTO registro) throws NegocioException {
        if (registro == null) {
            throw new NegocioException("Favor de ingresar datos válidos.");
        }
        if (registro.getNombre() == null || registro.getNombre().isEmpty()) {
            throw new NegocioException("El nombre es obligatorio.");
        }
        if (registro.getNotaDescriptiva() == null || registro.getNotaDescriptiva().isEmpty()) {
            throw new NegocioException("La nota descriptiva es obligatoria.");
        }
        if (registro.getUnidadMedida() == null || registro.getUnidadMedida().isEmpty()) {
            throw new NegocioException("La unidad de medida es obligatoria.");
        }
        if (registro.getOrdenReporte() == null || registro.getOrdenReporte() <= 0) {
            throw new NegocioException("El orden debe de ser mayor a 0.");
        }
        if (registro.getIdAnalisis() == null || registro.getIdAnalisis() <= 0) {
            throw new NegocioException("Favor de ingresar un análisis válido.");
        }
    }

    private List<ParametroDTO> conversionADTO(List<Parametro> parametros) throws NegocioException {
        try {
            List<ParametroDTO> listaParametrosDTO = new ArrayList<>();
            for (Parametro parametro : parametros) {
                ParametroDTO parametroDTO = new ParametroDTO();
                parametroDTO.setIdParametro(parametro.getIdParametro());
                parametroDTO.setNombre(parametroDTO.getNombre());
                parametroDTO.setOrdenReporte(parametro.getOrdenReporte());
                parametroDTO.setNotaDescriptiva(parametro.getNotaDescriptiva());
                parametroDTO.setUnidadMedida(parametro.getUnidadMedida());
                Integer cantidadRangos = parametroDAO.contarRangos(parametro.getIdParametro());
                parametroDTO.setCantidadRangos(cantidadRangos);
                listaParametrosDTO.add(parametroDTO);
            }
            return listaParametrosDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al convertir el paraemtro a DTO: " + ex.getMessage());
        }
    }

}
