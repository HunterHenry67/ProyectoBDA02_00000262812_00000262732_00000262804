/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAO.ClienteDAO;
import DAO.IClienteDAO;
import DAO.PersistenciaException;
import Entidades.Cliente;
import Entidades.Sexo;
import DTO.ClienteDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ClienteBO implements IClienteBO {

    private IClienteDAO clienteDAO;

    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Busca un cliente mediante su identificador.
     *
     * @param id identificador del cliente que deseas buscar.
     * @return cliente encontrado en formato DTO.
     * @throws NegocioException si el ID no es valido o ocurre algun error al
     * buscar.
     */
    @Override
    public ClienteDTO buscarClienteId(int id) throws NegocioException {

        try {

            if (id <= 0) {
                throw new NegocioException("Error en ClienteBO, ID invalido");
            }

            Cliente cliente = clienteDAO.buscarClienteId(id);
            if (cliente == null) {
                throw new NegocioException("Error en ClienteBO, no se encontro ningun cliente con ese id");
            }

            return convertirADTO(cliente);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con dao");

        }

    }

    /**
     * Busca clientes por medio del nombre o apellidos.
     *
     * @param nombre nombre o apellido del cliente que deseas buscar.
     * @return lista de clientes encontrados en formato DTO.
     * @throws NegocioException si el nombre no es valido o ocurre algun error
     * al buscar.
     */
    @Override
    public List<ClienteDTO> buscarClienteNombre(String nombre) throws NegocioException {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }

            List<Cliente> clientes = clienteDAO.buscarClienteNombre(nombre);

            if (clientes == null || clientes.isEmpty()) {
                throw new NegocioException("No se encontraron clientes con ese nombre");
            }

            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);

            }

            return clientesNuevos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con Dao");
        }
    }

    /**
     * Busca clientes por medio de la fecha de nacimiento.
     *
     * @param fecha fecha de nacimiento que deseas buscar.
     * @return lista de clientes encontrados en formato DTO.
     * @throws NegocioException si la fecha no es valida o ocurre algun error al
     * buscar.
     */
    @Override
    public List<ClienteDTO> buscarClienteFechaNacimiento(LocalDateTime fecha) throws NegocioException {
        try {

            if (fecha == null) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }

            List<Cliente> clientes = clienteDAO.buscarClienteFechaNacimiento(fecha);

            if (clientes.isEmpty() || clientes == null) {
                throw new NegocioException("No se encontraron clientes con esa fecha de nacimiento");
            }

            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);

            }

            return clientesNuevos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con Dao");
        }
    }

    /**
     * Busca clientes por medio del tipo de sangre.
     *
     * @param tipoSangre tipo de sangre que deseas buscar.
     * @return lista de clientes encontrados en formato DTO.
     * @throws NegocioException si el tipo de sangre no es valido o ocurre algun
     * error al buscar.
     */
    @Override
    public List<ClienteDTO> buscarClienteTipoSangre(String tipoSangre) throws NegocioException {
        try {

            if (tipoSangre == null || tipoSangre.isEmpty()) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }

            List<Cliente> clientes = clienteDAO.buscarClienteTipoSangre(tipoSangre);

            if (clientes.isEmpty() || clientes == null) {
                throw new NegocioException("No se encontraron clientes con ese tipo de sangre");
            }

            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);

            }

            return clientesNuevos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con Dao");
        }
    }

    /**
     * Busca clientes por medio del sexo.
     *
     * @param sexo sexo del cliente que deseas buscar.
     * @return lista de clientes encontrados en formato DTO.
     * @throws NegocioException si el sexo no es valido o ocurre algun error al
     * buscar.
     */
    @Override
    public List<ClienteDTO> buscarClienteSexo(Sexo sexo) throws NegocioException {
        try {

            if (sexo == null) {
                throw new NegocioException("ingrese datos válidos o correctos");
            }

            List<Cliente> clientes = clienteDAO.buscarClienteSexo(sexo);

            if (clientes.isEmpty() || clientes == null) {
                throw new NegocioException("No se encontraron clientes con ese sexo");
            }

            List<ClienteDTO> clientesNuevos = new ArrayList<>();
            for (Cliente pac : clientes) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                clientesNuevos.add(clienteDTO);

            }

            return clientesNuevos;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con Dao");
        }
    }

    /**
     * Obtiene todos los clientes registrados.
     *
     * @return lista de clientes encontrados en formato DTO.
     * @throws NegocioException si ocurre algun error al obtener los clientes.
     */
    @Override
    public List<ClienteDTO> ObtenerClientes() throws NegocioException {
        try {
            List<Cliente> listaEntidades = clienteDAO.obtenerClientes();

            List<ClienteDTO> listaDTOs = new ArrayList<>();

            for (Cliente pac : listaEntidades) {
                ClienteDTO clienteDTO = convertirADTO(pac);
                listaDTOs.add(clienteDTO);

            }

            return listaDTOs;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al conectar negocio con Dao");
        }
    }

    /**
     * Convierte una entidad Cliente a ClienteDTO.
     *
     * @param entidadCliente cliente que deseas convertir.
     * @return cliente convertido a DTO.
     */
    private ClienteDTO convertirADTO(Cliente entidadCliente) {
        ClienteDTO dto = new ClienteDTO();

        dto.setIdCliente(entidadCliente.getIdCliente());
        dto.setNombres(entidadCliente.getNombres());
        dto.setApellidoPaterno(entidadCliente.getApellidoPaterno());
        dto.setApellidoMaterno(entidadCliente.getApellidoMaterno());
        dto.setSexo(entidadCliente.getSexo().toString());
        dto.setFechaNacimiento(entidadCliente.getFechaNacimiento());
        dto.setTipoSangre(entidadCliente.getTipoSangre());

        return dto;
    }

}
