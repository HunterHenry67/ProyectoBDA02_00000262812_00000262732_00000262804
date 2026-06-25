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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BALAMRUSH
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente", nullable = false)
    private Integer idCliente;
    
    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;
    
    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    private String apellidoPaterno;
    
    @Column(name = "apellidoMaterno", nullable = false, length = 50)
    private String apellidoMaterno;
    
    @Column(name = "sexo", nullable = false, length = 9)
    private String sexo;
    
    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDateTime fechaNacimiento;
    
    @Column(name = "tipoSangre", nullable = false, length = 10)
    private String tipoSangre;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Prueba> listaPruebas = new ArrayList<>();  
}
