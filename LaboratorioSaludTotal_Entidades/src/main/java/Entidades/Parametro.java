/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idParametro", nullable = false)
    private Integer idParametro;
    
    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;
    
    @Column(name = "ordenReporte", nullable = false)
    private Integer ordenReporte;
    
    @Column(name = "notaDescriptiva", nullable = false, length = 100)
    private String notaDescriptiva ;
    
    @Column(name = "unidadMedida", nullable = false, length = 10)
    private String unidadMedida;
    
    @OneToMany(mappedBy = "parametro", cascade = CascadeType.PERSIST)
    private List<Resultado> listaResultados = new ArrayList<>();
    
    @OneToMany(mappedBy = "parametro", cascade = CascadeType.PERSIST)
    private List<Rango> listaRangos = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "idAnalisis", nullable = false)
    private Analisis analisis;

    public Parametro() {
    }

    public Parametro(String nombre, Integer ordenReporte, String notaDescriptiva, String unidadMedida, Analisis analisis) {
        this.nombre = nombre;
        this.ordenReporte = ordenReporte;
        this.notaDescriptiva = notaDescriptiva;
        this.unidadMedida = unidadMedida;
        this.analisis = analisis;
    }

    public Parametro(Integer idParametro, String nombre, Integer ordenReporte, String notaDescriptiva, String unidadMedida, Analisis analisis) {
        this.idParametro = idParametro;
        this.nombre = nombre;
        this.ordenReporte = ordenReporte;
        this.notaDescriptiva = notaDescriptiva;
        this.unidadMedida = unidadMedida;
        this.analisis = analisis;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrdenReporte() {
        return ordenReporte;
    }

    public void setOrdenReporte(Integer ordenReporte) {
        this.ordenReporte = ordenReporte;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public List<Resultado> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<Resultado> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public List<Rango> getListaRangos() {
        return listaRangos;
    }

    public void setListaRangos(List<Rango> listaRangos) {
        this.listaRangos = listaRangos;
    }

    public Analisis getAnalisis() {
        return analisis;
    }

    public void setAnalisis(Analisis analisis) {
        this.analisis = analisis;
    }
    
    
}
