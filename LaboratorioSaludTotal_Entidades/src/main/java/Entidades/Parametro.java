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
    
    @OneToMany(mappedBy = "reporte", cascade = CascadeType.PERSIST)
    private List<Resultado> listaResultados = new ArrayList<>();
    
    @OneToMany(mappedBy = "reporte", cascade = CascadeType.PERSIST)
    private List<Rango> listaRangos = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "idAnalisis", nullable = false)
    private Analisis analisis;
}
