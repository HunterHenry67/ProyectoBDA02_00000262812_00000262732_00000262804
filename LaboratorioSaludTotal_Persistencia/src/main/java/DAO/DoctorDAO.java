/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Doctor;
import Entidades.Sexo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author BALAMRUSH
 */
public class DoctorDAO implements IDoctorDAO {

    private IConexionBD conexionBD;
    
    public DoctorDAO() {
        this.conexionBD = new ConexionBD();
    }

    public DoctorDAO(IConexionBD conexionBD) {
        if (conexionBD == null) {
            this.conexionBD = new ConexionBD();
        } else {
            this.conexionBD = conexionBD;
        }
    }

    /**
     * Consulta el doctor buscado a través de su id
     * * @param idDoctor identificador de doctor
     * @return un objeto de la clase Doctor que coincida con el id
     * @throws PersistenciaException Si ocurre un error al acceder a la base de datos
     */
    @Override
    public Doctor consultarPorID(Integer idDoctor) throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root).where(cb.equal(root.get("idDoctor"), idDoctor));
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar al doctor: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * devuelve una lista con todos los doctores registrados
     * * @return una lista con objetos Doctor
     * @throws PersistenciaException Si ocurre un error durante la consulta de la bd
     */
    @Override
    public List<Doctor> consultarTodos() throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar los doctores: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * busca doctores cuyos nombres coincidan con el parametro mandado
     * * @param nombres nombre a buscar
     * @return Una lista de objetos Doctor que coinciden con el parametro
     * @throws PersistenciaException Si ocurre un error durante la búsqueda
     */
    @Override
    public List<Doctor> buscarPorNombres(String nombres) throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root).where(cb.like(root.get("nombres"), "%" + nombres + "%"));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar doctores por nombre: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * manda los doctores basándose en el sexo especificado
     * * @param sexo El valor del enum para filtrar.
     * @return Una lista de objetos Doctor filtrada por el sexo requerido
     * @throws PersistenciaException Si ocurre un error durante la consulta
     */
    @Override
    public List<Doctor> buscarPorSexo(Sexo sexo) throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root).where(cb.equal(root.get("sexo"), sexo));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar doctores por sexo: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}