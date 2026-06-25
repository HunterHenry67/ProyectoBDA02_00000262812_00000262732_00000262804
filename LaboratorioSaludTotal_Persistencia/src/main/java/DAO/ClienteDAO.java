/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Cliente;

/**
 *
 * @author BALAMRUSH
 */
public class ClienteDAO implements IClienteDAO{

    @Override
    public Cliente buscarClienteId(int id) {
        
        String sql = """
                     Select
                        idCliente,
                        nombre,
                        apellidoPaterno,
                        apellidoMaterno,
                        fechaNacimiento,
                        tipoSangre,
                        sexo
                     FROM Cliente
                     Where idCliente = ?
                     """;
        return null;
    }
    
}
