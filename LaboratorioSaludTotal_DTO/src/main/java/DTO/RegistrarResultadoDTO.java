/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BALAMRUSH
 */
public class RegistrarResultadoDTO {
    private Double resultadoObtenido;
    private String observacion;
    private Integer idPrueba;
    private Integer idParametro;

    public RegistrarResultadoDTO() {
    }

    public RegistrarResultadoDTO(Double resultadoObtenido, String observacion, Integer idPrueba, Integer idParametro) {
        this.resultadoObtenido = resultadoObtenido;
        this.observacion = observacion;
        this.idPrueba = idPrueba;
        this.idParametro = idParametro;
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

    public Integer getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }
    
}
