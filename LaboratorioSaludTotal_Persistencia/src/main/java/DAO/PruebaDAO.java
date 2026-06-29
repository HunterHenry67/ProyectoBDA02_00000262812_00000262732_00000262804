/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Analisis;
import Entidades.Cliente;
import Entidades.Doctor;
import Entidades.Prueba;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase de acceso a datos para la entidad {@link Prueba}.
 * Gestiona el registro, eliminación y consulta de pruebas clínicas, asegurando la 
 * integridad de las relaciones con las entidades {@link Cliente}, {@link Doctor} y {@link Analisis}.
 * * @author user
 */
public class PruebaDAO implements IPruebaDAO {

    private IConexionBD conexion;

    public PruebaDAO() {
        this.conexion = new ConexionBD();
    }

    /**
     * Persiste una nueva prueba en la base de datos.
     * Antes de guardar, verifica la existencia de las entidades relacionadas (Cliente, Doctor, Analisis)
     * en el sistema para evitar errores de persistencia
     * * @param prueba El objeto {@link Prueba} a guardar
     * @return El objeto {@link Prueba} persistido con su ID generado
     * @throws PersistenciaException Si alguna entidad relacionada no existe o ocurre un error en la transacción
     */
    @Override
    public Prueba agregarPrueba(Prueba prueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            em.getTransaction().begin();

            if (prueba.getCliente() != null && prueba.getCliente().getIdCliente() != null) {
                Cliente cliente = em.find(Cliente.class, prueba.getCliente().getIdCliente());

                if (cliente == null) {
                    throw new PersistenciaException("El cliente de la prueba no existe.");
                }

                prueba.setCliente(cliente);
            }

            if (prueba.getDoctor() != null && prueba.getDoctor().getIdDoctor() != null) {
                Doctor doctor = em.find(Doctor.class, prueba.getDoctor().getIdDoctor());

                if (doctor == null) {
                    throw new PersistenciaException("El doctor de la prueba no existe.");
                }

                prueba.setDoctor(doctor);
            }

            if (prueba.getAnalisis() != null && !prueba.getAnalisis().isEmpty()) {
                List<Analisis> analisisManaged = new ArrayList<>();

                for (Analisis analisis : prueba.getAnalisis()) {
                    Analisis analisisEncontrado = em.find(Analisis.class, analisis.getIdAnalisis());

                    if (analisisEncontrado == null) {
                        throw new PersistenciaException("El analisis no existe.");
                    }

                    analisisManaged.add(analisisEncontrado);
                }

                prueba.setAnalisis(analisisManaged);
            }

            em.persist(prueba);
            em.getTransaction().commit();

            return prueba;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al agregar prueba en DAO: " + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Elimina una prueba existente mediante su id
     * * @param idPrueba El ID de la prueba a eliminar
     * @return La prueba eliminada
     * @throws PersistenciaException Si la prueba no existe o hay error en la transacción
     */
    @Override
    public Prueba eliminarPrueba(int idPrueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            em.getTransaction().begin();

            Prueba pruebaEliminada = em.find(Prueba.class, idPrueba);

            if (pruebaEliminada == null) {
                throw new PersistenciaException("La prueba no existe.");
            }

            em.remove(pruebaEliminada);
            em.getTransaction().commit();

            return pruebaEliminada;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al eliminar prueba en DAO: " + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Busca una prueba específica por su ID
     * * @param idPrueba El identificador de la prueba
     * @return La prueba encontrada
     * @throws PersistenciaException Si la prueba no se encuentra o hay error en la consulta
     */
    @Override
    public Prueba buscarPruebaPorId(int idPrueba) throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            Prueba pruebaBuscada = em.find(Prueba.class, idPrueba);

            if (pruebaBuscada == null) {
                throw new PersistenciaException("La prueba buscada no existe.");
            }

            pruebaBuscada.getAnalisis().size();

            return pruebaBuscada;

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar prueba en DAO: " + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Consulta todas las pruebas registradas en el sistema
     * * @return Lista de objetos {@link Prueba}
     * @throws PersistenciaException Si ocurre un error al ejecutar la consulta
     */
    @Override
    public List<Prueba> consultarTodasLasPruebas() throws PersistenciaException {
        EntityManager em = conexion.conexionBD();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Prueba> cq = cb.createQuery(Prueba.class);
            Root<Prueba> root = cq.from(Prueba.class);

            cq.select(root);

            List<Prueba> pruebas = em.createQuery(cq).getResultList();

            for (Prueba prueba : pruebas) {
                prueba.getAnalisis().size();
            }

            return pruebas;

        } catch (Exception e) {
            throw new PersistenciaException("Error al traer todas las pruebas en DAO: " + e.getMessage());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}