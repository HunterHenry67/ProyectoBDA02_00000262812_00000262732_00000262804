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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "muestra")
public class Muestra implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMuestra", nullable = false)
    private Integer idMuestra;
    
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;
    
    @OneToMany(mappedBy = "muestra", cascade = CascadeType.PERSIST)
    private List<Analisis> listaAnalisis = new ArrayList<>(); 
}
