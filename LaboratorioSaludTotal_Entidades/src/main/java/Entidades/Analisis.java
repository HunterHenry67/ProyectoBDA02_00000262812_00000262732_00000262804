/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "analisis")
public class Analisis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAnalisis", nullable = false)
    private Integer idAnalisis;
    
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    
    @Column(name = "nota", nullable = false, length = 100)
    private String nota;
    
    @ManyToOne
    @JoinColumn(name = "idMuestra", nullable = false)
    private Muestra muestra;
}
