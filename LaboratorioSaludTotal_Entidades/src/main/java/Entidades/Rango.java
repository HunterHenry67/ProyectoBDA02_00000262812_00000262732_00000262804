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

/**
 *
 * @author BALAMRUSH
 */
@Entity
public class Rango implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idRango", nullable = false)
    private Integer idRango;

    @Column(name = "sexo", nullable = false, length = 20)
    private Sexo sexo;
    
    @Column(name = "edadIncial", nullable = false)
    private Integer edadInicial;
    
    @Column(name = "edadFinal", nullable = false)
    private Integer edadFinal;
    
    @Column(name = "rangoIncial", nullable = false)
    private Float rangoIncial;
    
    @Column(name = "rangoFinal", nullable = false)
    private Float rangoFinal;
    
    @ManyToOne
    @JoinColumn(name = "idParametro", nullable = false)
    private Parametro parametro;
}
