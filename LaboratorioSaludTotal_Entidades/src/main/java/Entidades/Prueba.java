/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prueba")
public class Prueba implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrueba", nullable = false)
    private Integer idPrueba;

    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idDoctor", nullable = false)
    private Doctor doctor;

    @ManyToMany
    @JoinTable(
            name = "prueba_analisis",
            joinColumns = @JoinColumn(name = "idPrueba"),
            inverseJoinColumns = @JoinColumn(name = "idAnalisis")
    )
    private List<Analisis> analisis = new ArrayList<>();

    @OneToMany(mappedBy = "prueba", cascade = CascadeType.PERSIST)
    private List<Resultado> listaResultado = new ArrayList<>();

    public Prueba() {
    }

    public Integer getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Analisis> getAnalisis() {
        return analisis;
    }

    public void setAnalisis(List<Analisis> analisis) {
        this.analisis = analisis;
    }

    public List<Resultado> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<Resultado> listaResultado) {
        this.listaResultado = listaResultado;
    }
}
