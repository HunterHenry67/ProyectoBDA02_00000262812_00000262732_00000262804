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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "prueba")
public class Prueba implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPrueba", nullable = false)
    private Integer idPrueba;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "folio", nullable = false)
    private Integer folio;
    
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;
    
    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "idDcotor",  nullable = false)
    private Doctor Doctor;
    
    @OneToMany(mappedBy = "idResultado", cascade = CascadeType.PERSIST)
    private List<Resultado> listaResultado = new ArrayList<>();    
}
