/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author BALAMRUSH
 */
public class ConexionBD implements IConexionBD{

    @Override
    public EntityManager conexionBD() {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("LaboratorioSaludPU");
        EntityManager entityManager = managerFactory.createEntityManager();
        return entityManager;
    }
    
}
