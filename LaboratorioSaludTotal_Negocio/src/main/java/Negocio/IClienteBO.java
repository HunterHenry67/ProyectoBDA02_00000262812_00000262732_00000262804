/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Negocio;

import Entidades.Cliente;

/**
 *
 * @author user
 */
public interface IClienteBO {
    Cliente buscarClienteId (int id) throws Exception;
}
