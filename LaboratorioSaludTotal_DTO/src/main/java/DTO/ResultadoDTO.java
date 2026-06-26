/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BALAMRUSH
 */
public class ResultadoDTO {
    
    private Integer idResultado;  
    private String nombreParametro;
    private String unidadMedida;
    private String rangoNormal;
    private Double resultadoObtenido;
    private String observacion;
    private Integer idPrueba;
    private Integer idParametro;

    public ResultadoDTO() {
    }

    public ResultadoDTO(Integer idResultado, String nombreParametro, String unidadMedida, String rangoNormal, Double resultadoObtenido, String observacion, Integer idPrueba, Integer idParametro) {
        this.idResultado = idResultado;
        this.nombreParametro = nombreParametro;
        this.unidadMedida = unidadMedida;
        this.rangoNormal = rangoNormal;
        this.resultadoObtenido = resultadoObtenido;
        this.observacion = observacion;
        this.idPrueba = idPrueba;
        this.idParametro = idParametro;
    }

    public Integer getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(Integer idResultado) {
        this.idResultado = idResultado;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getRangoNormal() {
        return rangoNormal;
    }

    public void setRangoNormal(String rangoNormal) {
        this.rangoNormal = rangoNormal;
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
