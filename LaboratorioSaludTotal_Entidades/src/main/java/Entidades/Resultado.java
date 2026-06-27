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
@Table(name = "resultado")
public class Resultado implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResultado", nullable = false)
    private Integer idResultado;
    
    @Column(name = "resultadoObtenido", nullable = false)
    private Double resultadoObtenido;

    @Column(name = "observacion", nullable = false, length = 100)
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "idPrueba", nullable = false)
    private Prueba prueba;
    
    @ManyToOne
    @JoinColumn(name = "idParametro", nullable = false)
    private Parametro parametro;

    public Resultado() {
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public Double getResultadoObtenido() {
        return resultadoObtenido;
    }

    public void setResultadoObtenido(Double resultadoObtenido) {
        this.resultadoObtenido = resultadoObtenido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }
    
    
}
