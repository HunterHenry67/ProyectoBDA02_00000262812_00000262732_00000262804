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
}
