/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andre
 */

public class DoctorDTO implements Serializable {

    private Integer idDoctor;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String sexo;
    private List<Integer> idsPruebas;

    public DoctorDTO() {
    }

    public DoctorDTO(Integer idDoctor, String nombres, String apellidoPaterno, String apellidoMaterno, String sexo, List<Integer> idsPruebas) {
        this.idDoctor = idDoctor;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.sexo = sexo;
        this.idsPruebas = idsPruebas;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Integer> getIdsPruebas() {
        return idsPruebas;
    }

    public void setIdsPruebas(List<Integer> idsPruebas) {
        this.idsPruebas = idsPruebas;
    }
}