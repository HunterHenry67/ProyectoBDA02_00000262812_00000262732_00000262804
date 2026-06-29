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
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(356, 356, 356)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(563, 563, 563)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(690, 690, 690)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(579, 579, 579)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(169, 169, 169)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
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
