/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.RangoDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author BALAMRUSH
 */
public class RegistroRangoFORM extends javax.swing.JFrame {

    private RegistroParametroFORM registroParametroFORM;
    
    public RegistroRangoFORM(RegistroParametroFORM registroParametroFORM) {
        initComponents();
        this.registroParametroFORM = registroParametroFORM;
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void guardarRango() throws PresentacionException {
        if (comboBoxSexo.getSelectedItem() == null) {
            throw new PresentacionException("Favor de seleccionar el sexo.");
        }
        if (txtFieldEdadInicial.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar la edad inicial.");
        }
        if (txtFieldEdadFinal.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar la edad final.");
        }
        if (txtFieldRangoInicial.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar el rango inicial.");
        }
        if (txtFieldRangoFinal.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar el rango final.");
        }
        Integer edadInicial = convertirEdadInicial();
        Integer edadFinal = convertirEdadFinal();

        if (edadInicial < 0 || edadFinal < 0) {
            throw new PresentacionException("Favor de ingresar una edad válida.");
        }
        if (edadInicial > edadFinal) {
            throw new PresentacionException("Edad inicial no válida.");
        }
        Float rangoInicial = convertirRangoInicial();
        Float rangoFinal = convertirRangoFinal();
        
        if (rangoInicial > rangoFinal) {
            throw new PresentacionException("Rango inicial no válido.");
        }
        RangoDTO rangoDTO = new RangoDTO();
        rangoDTO.setSexo(comboBoxSexo.getSelectedItem().toString());
        rangoDTO.setEdadInicial(convertirEdadInicial());
        rangoDTO.setEdadFinal(convertirEdadFinal());
        rangoDTO.setRangoInicial(convertirRangoInicial());
        rangoDTO.setRangoFinal(convertirRangoFinal());

        if (registroParametroFORM == null) {
            throw new PresentacionException("No se encontró la pantalla de parámetro.");
        }

        registroParametroFORM.agregarRangoAlParametro(rangoDTO);
        JOptionPane.showMessageDialog(this,"El rango ha sido agregado correctamente.","Registro exitoso",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }
    
     private Integer convertirEdadInicial() throws PresentacionException {
        try {
            return Integer.valueOf(txtFieldEdadInicial.getText().trim());
        } catch (NumberFormatException ex) {
            throw new PresentacionException("La edad inicial debe ser un entero.");
        }
    }
     
    private Integer convertirEdadFinal() throws PresentacionException {
        try {
            return Integer.valueOf(txtFieldEdadFinal.getText().trim());
        } catch (NumberFormatException ex) {
            throw new PresentacionException("La edad final debe ser un entero.");
        }
    }

    private Float convertirRangoInicial() throws PresentacionException {
        try {
            return Float.valueOf(txtFieldRangoInicial.getText().trim());
        } catch (NumberFormatException ex) {
            throw new PresentacionException("El rango inicial debe ser entero..");
        }
    }

    private Float convertirRangoFinal() throws PresentacionException {
        try {
            return Float.valueOf(txtFieldRangoFinal.getText().trim());
        } catch (NumberFormatException ex) {
            throw new PresentacionException("El rango final debe ser entero.");
        }
    }
    
    private void saltoAdvertencia(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboBoxSexo = new javax.swing.JComboBox<>();
        txtFieldEdadInicial = new javax.swing.JTextField();
        txtFieldEdadFinal = new javax.swing.JTextField();
        txtFieldRangoInicial = new javax.swing.JTextField();
        txtFieldRangoFinal = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Rango");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Registro / Alta de Análisis (Rango)");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Sexo:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Edad Inicial:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Edad Final:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Rango Inicial: ");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Rango Final:");

        comboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));

        btnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Registrar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldEdadInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldEdadFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(768, 768, 768)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jLabel2)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFieldEdadInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFieldEdadFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFieldRangoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFieldRangoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        try {
            guardarRango();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtFieldEdadFinal;
    private javax.swing.JTextField txtFieldEdadInicial;
    private javax.swing.JTextField txtFieldRangoFinal;
    private javax.swing.JTextField txtFieldRangoInicial;
    // End of variables declaration//GEN-END:variables
}
