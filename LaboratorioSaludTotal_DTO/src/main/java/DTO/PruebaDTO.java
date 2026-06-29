/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author user
 */
public class PruebaDTO {
    private Integer idPrueba; 
    private LocalDateTime fechaHora;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idDoctor;
    private String nombreDoctor;

    public PruebaDTO() {
    }

    public Integer getIdPrueba() {
        return idPrueba;
    }

    public void setIdPrueba(Integer idPrueba) {
        this.idPrueba = idPrueba;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Integer idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }
    
    private List<AnalisisDTO> analisisAgregados;

    public List<AnalisisDTO> getAnalisisAgregados() {
        return analisisAgregados;
    }

    public void setAnalisisAgregados(List<AnalisisDTO> analisisAgregados) {
        this.analisisAgregados = analisisAgregados;
    }
    
}
