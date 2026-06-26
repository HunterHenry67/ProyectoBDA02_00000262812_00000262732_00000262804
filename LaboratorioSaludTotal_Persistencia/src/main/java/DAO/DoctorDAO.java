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
public class DoctorDAO implements IDoctorDAO{

    private IConexionBD conexionBD;
    
    public DoctorDAO(){
        this.conexionBD = conexionBD;
    }
    
    @Override
    public Doctor consultarPorID(Integer idDoctor) throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root).where(cb.equal(root.get("idDoctor"), idDoctor));
            return em.createQuery(cq).getSingleResult();
        }catch(Exception e){
            throw new PersistenciaException("Error al consultar al análisis: " + e.getMessage());
        }finally{
            em.close();
        }
    }

    @Override
    public List<Doctor> consultarTodos() throws PersistenciaException {
        EntityManager em = conexionBD.conexionBD();
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> root = cq.from(Doctor.class);
            cq.select(root);
            return em.createQuery(cq).getResultList();
        }catch(Exception e){
            throw new PersistenciaException("Error al consultar los análisis: " + e.getMessage());
        }finally{
            em.close();
        }
    }

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
