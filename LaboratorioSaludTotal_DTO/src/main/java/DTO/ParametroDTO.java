/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author BALAMRUSH
 */
public class ParametroDTO {

    private Integer idParametro;
    private String nombre;
    private Integer ordenReporte;
    private String notaDescriptiva;
    private String unidadMedida;
    private Integer cantidadRangos;

    public ParametroDTO() {
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
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

    public Integer getCantidadRangos() {
        return cantidadRangos;
    }

    public void setCantidadRangos(Integer cantidadRangos) {
        this.cantidadRangos = cantidadRangos;
    }
}
