/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Andre
 */
public class ActualizarAnalisisDTO {

    private Integer idAnalisis;
    private String nombre;
    private String nota;
    private Integer idMuestra;

    public ActualizarAnalisisDTO() {
    }

    public ActualizarAnalisisDTO(Integer idAnalisis, String nombre, String nota, Integer idMuestra) {
        this.idAnalisis = idAnalisis;
        this.nombre = nombre;
        this.nota = nota;
        this.idMuestra = idMuestra;
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
}