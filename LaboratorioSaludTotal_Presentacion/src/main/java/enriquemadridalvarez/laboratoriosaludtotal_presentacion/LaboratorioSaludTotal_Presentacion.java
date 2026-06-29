/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package enriquemadridalvarez.laboratoriosaludtotal_presentacion;

import Frames.ControlNavegacionForms;

/**
 *
 * @author BALAMRUSH
 */
public class LaboratorioSaludTotal_Presentacion {
    

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        iniciarPrograma();
    }
    
    private static void iniciarPrograma(){
        ControlNavegacionForms control = new ControlNavegacionForms();
        control.mostrarMenuPrincipal();
    }
}
