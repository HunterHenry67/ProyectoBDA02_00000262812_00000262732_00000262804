/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

/**
 *
 * @author BALAMRUSH
 */
public class MenuPrincipalFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;

    public MenuPrincipalFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
//        setExtendedState(MAXIMIZED_BOTH);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegistroSoliPrueba = new javax.swing.JButton();
        btnCatalogoAnalisis = new javax.swing.JButton();
        btnEmisionReporte = new javax.swing.JButton();
        btnIngresoResultado = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Menú Principal");

        btnRegistroSoliPrueba.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistroSoliPrueba.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegistroSoliPrueba.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroSoliPrueba.setText("Registro de Solicitud de Prueba");
        btnRegistroSoliPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroSoliPruebaActionPerformed(evt);
            }
        });

        btnCatalogoAnalisis.setBackground(new java.awt.Color(0, 0, 0));
        btnCatalogoAnalisis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCatalogoAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        btnCatalogoAnalisis.setText("Catálogo de Análisis");
        btnCatalogoAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoAnalisisActionPerformed(evt);
            }
        });

        btnEmisionReporte.setBackground(new java.awt.Color(0, 0, 0));
        btnEmisionReporte.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEmisionReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnEmisionReporte.setText("Emisión de Reporte");
        btnEmisionReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmisionReporteActionPerformed(evt);
            }
        });

        btnIngresoResultado.setBackground(new java.awt.Color(0, 0, 0));
        btnIngresoResultado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnIngresoResultado.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresoResultado.setText("Ingreso de Resultado");
        btnIngresoResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoResultadoActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(0, 0, 0));
        btnSalir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(250, 250, 250)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(571, 571, 571)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(185, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(541, 541, 541))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(662, 662, 662))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEmisionReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmisionReporteActionPerformed
        controlNavegacion.mostrarEmisionReporte();
        this.dispose();
    }//GEN-LAST:event_btnEmisionReporteActionPerformed

    private void btnCatalogoAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoAnalisisActionPerformed
        controlNavegacion.mostrarCatalogoAnalisis();
        this.dispose();
    }//GEN-LAST:event_btnCatalogoAnalisisActionPerformed

    private void btnRegistroSoliPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroSoliPruebaActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarRegistroSolicitudPrueba();
        this.dispose();
    }//GEN-LAST:event_btnRegistroSoliPruebaActionPerformed

    private void btnIngresoResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoResultadoActionPerformed
        controlNavegacion.mostrarBusquedaPaciente();
        this.dispose();
    }//GEN-LAST:event_btnIngresoResultadoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatalogoAnalisis;
    private javax.swing.JButton btnEmisionReporte;
    private javax.swing.JButton btnIngresoResultado;
    private javax.swing.JButton btnRegistroSoliPrueba;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
