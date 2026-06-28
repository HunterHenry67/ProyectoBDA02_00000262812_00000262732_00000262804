/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IMuestraDAO;
import DAO.MuestraDAO;
import DAO.PersistenciaException;
import DTO.MuestraDTO;
import Entidades.Muestra;
import Entidades.Prueba;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class MuestraBO implements IMuestraBO{
    private IMuestraDAO muestraDAO;

    public MuestraBO() {
        this.muestraDAO = new MuestraDAO();
    }

    @Override
    public MuestraDTO agregarMuestra(MuestraDTO muestraNuevo) throws NegocioException {
        try {
            Muestra entidadNueva = convertirAEntidad(muestraNuevo);
            Muestra entidadGuardada = muestraDAO.agregarMuestra(entidadNueva);
            
            return convertirADTO(entidadGuardada);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar la muestra en el sistema");
        }
    }

    @Override
    public MuestraDTO buscarMuestraPorId(int idMuestra) throws NegocioException {
        try {
            if (idMuestra <= 0) {
                throw new NegocioException("Error, id de muestra inválido");
            }
            
            Muestra muestraEncontrada = muestraDAO.buscarMuestraPorId(idMuestra);
            MuestraDTO dto = convertirADTO(muestraEncontrada);
            return dto;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar la muestra por id");
        }
    }

    @Override
    public List<MuestraDTO> consultarTodasLasMuestras() throws NegocioException {
        try {
            List<Muestra> listaEntidades = muestraDAO.consultarTodasLasMuestras();
            List<MuestraDTO> listaDTOs = new ArrayList<>();
            
            for (Muestra entidad : listaEntidades) {
                listaDTOs.add(convertirADTO(entidad));
            }
            return listaDTOs;
            
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener el catálogo de muestras");
        }
    }

    
   private MuestraDTO convertirADTO(Muestra entidad) {
        MuestraDTO dto = new MuestraDTO();
        
        dto.setIdMuestra(entidad.getIdMuestra());
        dto.setNombre(entidad.getNombre());
        return dto;
    }

    private Muestra convertirAEntidad(MuestraDTO dto) {
        Muestra entidad = new Muestra();
        
        if (dto.getIdMuestra() != null) {
            entidad.setIdMuestra(dto.getIdMuestra());
        }
        
        entidad.setNombre(dto.getNombre());
        return entidad;
    }
    
}
