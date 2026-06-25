/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ClienteDAO;
import DAO.IClienteDAO;
import Entidades.Cliente;

/**
 *
 * @author user
 */
public class ClienteBO implements IClienteBO{

    private IClienteDAO clienteDAO;
    
    @Override
    public Cliente buscarClienteId(int id) throws Exception {
        if (id <= 0){
            throw new Exception ("Errro en ClienteBO, ID invalido");
        }
        
        Cliente cliente = clienteDAO.buscarClienteId(id);
        if (cliente == null){
            throw new Exception ("Errro en ClienteBO, no se encontro ningun cliente con ese id");
        }
        return cliente;
    }
    
}
