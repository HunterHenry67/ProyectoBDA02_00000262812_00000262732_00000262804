/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.IParametroDAO;
import DAO.IPruebaDAO;
import DAO.IResultadoDAO;
import DTO.RegistrarResultadoDTO;
import DTO.ResultadoDTO;
import Entidades.Resultado;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class ResultadoBO implements IResultadoBO{

    private IResultadoDAO resultadoDAO;
    private IPruebaDAO pruebaDAO;
    private IParametroDAO parametroDAO;
    
    public ResultadoBO(IResultadoDAO resultadoDAO, IPruebaDAO pruebaDAO, IParametroDAO parametroDAO){
        this.resultadoDAO = resultadoDAO;
        this.pruebaDAO = pruebaDAO;
        this.parametroDAO = parametroDAO;
    }
    
    @Override
    public Resultado registrarResultado(RegistrarResultadoDTO dto) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resultado consultarResultadoPorID(Integer idResultado) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ResultadoDTO> consultarTablaPorPrueba(Integer idPrueba) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Resultado consultarResultadoPorPruebaParametro(Integer idPrueba, Integer idParametro) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean resultadoExiste(Integer idPrueba, Integer idParametro) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void validarRegistroResultado(RegistrarResultadoDTO registro) throws NegocioException{
        
    }
    
}
