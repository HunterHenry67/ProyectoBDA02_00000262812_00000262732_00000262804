/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import DTO.MuestraDTO;
import java.util.List;

/**
 *
 * @author user
 */
public interface IMuestraBO {
    MuestraDTO agregarMuestra(MuestraDTO muestraNuevo) throws NegocioException;
    MuestraDTO buscarMuestraPorId(int idMuestra) throws NegocioException;
    List<MuestraDTO> consultarTodasLasMuestras() throws NegocioException;
}
