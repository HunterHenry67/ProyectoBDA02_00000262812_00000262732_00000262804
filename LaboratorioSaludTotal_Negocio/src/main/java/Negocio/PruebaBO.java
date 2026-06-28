/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IPruebaDAO;
import DAO.PruebaDAO;
import DAO.PersistenciaException;
import DTO.PruebaDTO;
import Entidades.Cliente;
import Entidades.Doctor;
import Entidades.Prueba;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class PruebaBO implements IPruebaBO{
    
    private IPruebaDAO pruebaDAO;
    
    public PruebaBO() {
        this.pruebaDAO = new PruebaDAO();
    }

    @Override
    public PruebaDTO agregarPrueba(PruebaDTO dtoNuevo) throws NegocioException {
        try {
            if (dtoNuevo.getIdCliente() == null || dtoNuevo.getIdDoctor() == null) {
                throw new NegocioException("Error La prueba debe tener un cliente y un doctor");
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
            if (idPrueba<= 0) {
                throw new NegocioException("Error el id de prueba inválido");
            }

            Prueba pruebaEliminar = pruebaDAO.eliminarPrueba(idPrueba);
            if(pruebaEliminar == null){
                throw new NegocioException("Error, no se encontro la preuba a eliminar");
            }
            
            PruebaDTO dto = convertirADTO(pruebaEliminar);
            return dto;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar la prueba médica.", e);
        }
    }

    @Override
    public PruebaDTO buscarPruebaPorId(int idPrueba) throws NegocioException {
        try{
            if(idPrueba<=0){
                throw new NegocioException("Error, id invalido");
            }
            
            Prueba pruebaBuscada = pruebaDAO.buscarPruebaPorId(idPrueba);
            if(pruebaBuscada == null){
                throw new NegocioException("Error, no se encontro ninguna prueba con ese id");
            }
            
            PruebaDTO dto = convertirADTO(pruebaBuscada);
            return dto;
        }catch(Exception e){
            throw new NegocioException("Error al buscar prueba por id");
        }
    }

    @Override
    public List<PruebaDTO> consultarTodasLasPruebas() throws NegocioException {
        try {
            List<Prueba> listaEntidades = pruebaDAO.consultarTodasLasPruebas();
            
            List<PruebaDTO> listaDTOs = new ArrayList<>();
            for (Prueba entidad : listaEntidades) {
                PruebaDTO dto = convertirADTO(entidad);
                listaDTOs.add(dto);
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

        return entidad;
    }
}
