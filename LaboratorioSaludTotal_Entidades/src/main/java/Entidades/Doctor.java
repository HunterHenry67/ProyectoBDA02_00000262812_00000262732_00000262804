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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "doctor")
public class Doctor implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDoctor", nullable = false)
    private Integer idDoctor;
    
    @Column(name = "nombes", nullable = false, length = 20)
    private String nombres;
    
    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;
    
    @Column(name = "apellidoMeterno")
    private String apellidoMaterno;
    
    @Column(name = "sexo", nullable = false, length = 9)
    private String sexo;
    
    @OneToMany(mappedBy = "dcotor", cascade = CascadeType.PERSIST)
    private List<Prueba> listaPruebas = new ArrayList<>();
    
    

    
}
