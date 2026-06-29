/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Implementación de la interfaz {@link IConexionBD} encargada de gestionar
 * la conexión con la base de datos mediante JPA (Java Persistence API)
 * Proporciona el {@link EntityManager} necesario para las operaciones CRUD
 *
 * @author BALAMRUSH
 */
public class ConexionBD implements IConexionBD{

    public ConexionBD() {
    }

    /**
     * Crea y retorna una nueva instancia de {@link EntityManager} utilizando 
     * la unidad de persistencia definida en el proyecto.
     * * @return Una instancia activa de {@link EntityManager} para realizar consultas y transacciones.
     */
    @Override
    public EntityManager conexionBD() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("LaboratorioSaludPU");
        EntityManager entityManager = managerFactory.createEntityManager();
        return entityManager;
    }
    
}
