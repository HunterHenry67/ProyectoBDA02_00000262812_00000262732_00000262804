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
    private Sexo sexo;
    
    @OneToMany(mappedBy = "dcotor", cascade = CascadeType.PERSIST)
    private List<Prueba> listaPruebas = new ArrayList<>();    

    public Doctor() {
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Prueba> getListaPruebas() {
        return listaPruebas;
    }

    public void setListaPruebas(List<Prueba> listaPruebas) {
        this.listaPruebas = listaPruebas;
    }
    
    
}
