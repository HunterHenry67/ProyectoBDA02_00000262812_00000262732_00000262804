/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author Andre
 */
public class GuardarAnalisisDTO {
    private String nombre;
    private String nota;
    private Integer idMuestra;
    private List<RegistrarParametroDTO> parametros;

    public GuardarAnalisisDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(Integer idMuestra) {
        this.idMuestra = idMuestra;
    }

    public List<RegistrarParametroDTO> getParametros() {
        return parametros;
    }

    public void setParametros(List<RegistrarParametroDTO> parametros) {
        this.parametros = parametros;
    }   
}
