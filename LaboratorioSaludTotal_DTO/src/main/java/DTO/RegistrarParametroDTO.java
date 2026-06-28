/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class RegistrarParametroDTO {
    private String nombre;
    private Integer ordenReporte;
    private String notaDescriptiva;
    private String unidadMedida;
    private Integer idAnalisis;
    private List<RangoDTO> rangos;

    public RegistrarParametroDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrdenReporte() {
        return ordenReporte;
    }

    public void setOrdenReporte(Integer ordenReporte) {
        this.ordenReporte = ordenReporte;
    }

    public String getNotaDescriptiva() {
        return notaDescriptiva;
    }

    public void setNotaDescriptiva(String notaDescriptiva) {
        this.notaDescriptiva = notaDescriptiva;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public List<RangoDTO> getRangos() {
        return rangos;
    }

    public void setRangos(List<RangoDTO> rangos) {
        this.rangos = rangos;
    }  
}
