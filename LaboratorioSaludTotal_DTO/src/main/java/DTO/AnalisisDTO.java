/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BALAMRUSH
 */
public class AnalisisDTO {
    private Integer idAnalisis;
    private String nombre;
    private String tipoMuestra;
    private Integer cantidadParametros;

    public AnalisisDTO() {
    }

    public AnalisisDTO(Integer idAnalisis, String nombre, String tipoMuestra, Integer cantidadParametros) {
        this.idAnalisis = idAnalisis;
        this.nombre = nombre;
        this.tipoMuestra = tipoMuestra;
        this.cantidadParametros = cantidadParametros;
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMuestra() {
        return tipoMuestra;
    }

    public void setTipoMuestra(String tipoMuestra) {
        this.tipoMuestra = tipoMuestra;
    }

    public Integer getCantidadParametros() {
        return cantidadParametros;
    }

    public void setCantidadParametros(Integer cantidadParametros) {
        this.cantidadParametros = cantidadParametros;
    }
    
}
