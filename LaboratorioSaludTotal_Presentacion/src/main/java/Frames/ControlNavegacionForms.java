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
    
    public void mostrarMenuPrincipal(){
        MenuPrincipalFORM pantallaMenuPrincipalFORM = new MenuPrincipalFORM(this);
        pantallaMenuPrincipalFORM.setVisible(true);
    }
    
    public void mostrarCatalogoAnalisis(){
        CatalogoAnalisisFORM pantallaCatalogoAnalisis = new CatalogoAnalisisFORM(this);
        pantallaCatalogoAnalisis.setVisible(true);
    }
    
    public void mostrarRegistroAltaAnalisis(){
        RegistroAltaAnalisisFORM pantallaRegistroAltaAnalisis = new RegistroAltaAnalisisFORM(this);
        pantallaRegistroAltaAnalisis.setVisible(true);
    }
    
    public void mostrarCatalogoClientes() {
        CatalogoClientesFORM pantallaCatalogoClientes = new CatalogoClientesFORM(this);
        pantallaCatalogoClientes.setVisible(true);
    }

    public void mostrarCatalogoDoctores() {
        CatalogoDoctoresFORM pantallaCatalogoDoctores = new CatalogoDoctoresFORM(this);
        pantallaCatalogoDoctores.setVisible(true);
    }
    
    public void mostrarRegistroSolicitudPrueba() {
        RegistroSolicitudPruebaFORM pantalla = new RegistroSolicitudPruebaFORM(this);
        this.pantallaSolicitudActual = pantalla; // <--- La única línea extra: tomamos foto de la pantalla
        pantalla.setVisible(true);
    }
    
    public void mostrarCatalogoAnalisisPrueba() {
        CatalogoAnalisisPruebaFORM pantalla = new CatalogoAnalisisPruebaFORM(this);
        pantalla.setVisible(true);
    }
    

    public void mostrarBusquedaPaciente() {
        BusquedaPacienteFORM pantalla = new BusquedaPacienteFORM(this);
        pantalla.setVisible(true);
    }

    public void mostrarEmisionReporte() {
        EmisionReporteFORM pantalla = new EmisionReporteFORM(this);
        pantalla.setVisible(true);
    }
    
    public RegistroSolicitudPruebaFORM getPantallaSolicitudActual() {
        return pantallaSolicitudActual;
    }
}
