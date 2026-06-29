/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;

/**
 *
 * @author BALAMRUSH
 */
public class ControlNavegacionForms {

    private RegistroSolicitudPruebaFORM pantallaSolicitudActual;
    public ControlNavegacionForms() {
    }
    
    /**
     * Muestra la pantalla de MenuPrincipalFORM
     */
    public void mostrarMenuPrincipal(){
        MenuPrincipalFORM pantallaMenuPrincipalFORM = new MenuPrincipalFORM(this);
        pantallaMenuPrincipalFORM.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de CatalogoAnalisisFORM
     */
    public void mostrarCatalogoAnalisis(){
        CatalogoAnalisisFORM pantallaCatalogoAnalisis = new CatalogoAnalisisFORM(this);
        pantallaCatalogoAnalisis.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de RegistroAltaAnalisisFORM
     */
    public void mostrarRegistroAltaAnalisis(){
        RegistroAltaAnalisisFORM pantallaRegistroAltaAnalisis = new RegistroAltaAnalisisFORM(this);
        pantallaRegistroAltaAnalisis.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de CatalogoClientesFORM
     */
    public void mostrarCatalogoClientes() {
        CatalogoClientesFORM pantallaCatalogoClientes = new CatalogoClientesFORM(this);
        pantallaCatalogoClientes.setVisible(true);
    }

    /**
     * Muestra la pantalla de CatalogoDoctoresFORM
     */
    public void mostrarCatalogoDoctores() {
        CatalogoDoctoresFORM pantallaCatalogoDoctores = new CatalogoDoctoresFORM(this);
        pantallaCatalogoDoctores.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de RegistroSolicitudPruebaFORM
     */
    public void mostrarRegistroSolicitudPrueba() {
        RegistroSolicitudPruebaFORM pantalla = new RegistroSolicitudPruebaFORM(this);
        this.pantallaSolicitudActual = pantalla; 
        pantalla.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de CatalogoAnalisisPruebaFORM
     */
    public void mostrarCatalogoAnalisisPrueba() {
        CatalogoAnalisisPruebaFORM pantalla = new CatalogoAnalisisPruebaFORM(this);
        pantalla.setVisible(true);
    }
    
    /**
     * Muestra la pantalla de BusquedaPacienteFORM
     */
    public void mostrarBusquedaPaciente() {
        BusquedaPacienteFORM pantalla = new BusquedaPacienteFORM(this);
        pantalla.setVisible(true);
    }

    /**
     * Muestra la pantalla de EmisionReporteFORM
     */
    public void mostrarEmisionReporte() {
        EmisionReporteFORM pantalla = new EmisionReporteFORM(this);
        pantalla.setVisible(true);
    }
    
    
    public RegistroSolicitudPruebaFORM getPantallaSolicitudActual() {
        return pantallaSolicitudActual;
    }
    
    public void setPantallaSolicitudActual(RegistroSolicitudPruebaFORM pantallaSolicitudActual) {
        this.pantallaSolicitudActual = pantallaSolicitudActual;
    }
}
