/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import Negocio.AnalisisBO;
import DTO.AnalisisDTO;
import Negocio.IAnalisisBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla que permite buscar, filtrar y seleccionar tipos de análisis para una prueba
 * @author user
 */
public class CatalogoAnalisisPruebaFORM extends javax.swing.JFrame {
    private ControlNavegacionForms controlNavegacion;
    private IAnalisisBO analisisBO;
    private List<AnalisisDTO> analisis;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CatalogoAnalisisPruebaFORM.class.getName());

    
    public CatalogoAnalisisPruebaFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.analisis = new ArrayList<>();
        configurarFiltrosAnalisis();
        this.controlNavegacion = controlNavegacion;
        this.analisisBO = new AnalisisBO();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        try {
            cargarTabla();
        } catch (PresentacionException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Define cómo funcionan los filtros para buscar análisis
     */
    private void configurarFiltrosAnalisis() {
        comboBoxFiltro.setModel(new DefaultComboBoxModel<>(
                new String[]{"Todos", "ID", "Análisis", "Tipo de Muestra", "Parámetros"}
        ));

        txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filtrarAnalisis(); }
            @Override public void removeUpdate(DocumentEvent e) { filtrarAnalisis(); }
            @Override public void changedUpdate(DocumentEvent e) { filtrarAnalisis(); }
        });

        comboBoxFiltro.addActionListener(e -> filtrarAnalisis());
    }
    
    /**
     * Actualiza la tabla basándose en lo que el usuario escribe en el buscador y el filtro seleccionado
     */
    private void filtrarAnalisis() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAnalisis.getModel();
        modelo.setRowCount(0);

        String entrada = txtBuscador.getText().trim().toLowerCase();
        String filtro = comboBoxFiltro.getSelectedItem().toString();

        for (AnalisisDTO analisisDTO : analisis) {
            String id = analisisDTO.getIdAnalisis() != null ? analisisDTO.getIdAnalisis().toString().toLowerCase() : "";
            String nombre = analisisDTO.getNombre() != null ? analisisDTO.getNombre().toLowerCase() : "";
            String tipoMuestra = analisisDTO.getTipoMuestra() != null ? analisisDTO.getTipoMuestra().toLowerCase() : "";
            String parametros = analisisDTO.getCantidadParametros() != null ? analisisDTO.getCantidadParametros().toString().toLowerCase() : "";

            boolean coincide = entrada.isEmpty();

            if (!entrada.isEmpty()) {
                switch (filtro) {
                    case "ID": coincide = id.contains(entrada); break;
                    case "Análisis": coincide = nombre.contains(entrada); break;
                    case "Tipo de Muestra": coincide = tipoMuestra.contains(entrada); break;
                    case "Parámetros": coincide = parametros.contains(entrada); break;
                    case "Todos": coincide = id.contains(entrada) || nombre.contains(entrada) || tipoMuestra.contains(entrada) || parametros.contains(entrada); break;
                }
            }

            if (coincide) {
                modelo.addRow(new Object[]{
                    analisisDTO.getIdAnalisis(),
                    analisisDTO.getNombre(),
                    analisisDTO.getTipoMuestra() != null ? analisisDTO.getTipoMuestra() : "N/A",
                    analisisDTO.getCantidadParametros()
                });
            }
        }
    }
    
    
    private String convertirTexto(Object valor) {
        if (valor == null) {
            return "N/A";
        }
        return valor.toString();
    }
    
    /**
     * Consulta todos los análisis en el negocio y llena la tabla visual
     * @throws PresentacionException Si no se pudieron cargar los datos
     */
    private void cargarTabla() throws PresentacionException {
        try {
            analisis = analisisBO.consultarTodos(); 
            llenarTabla(analisis);
        } catch (Exception ex) {
            throw new PresentacionException("Error al cargar la tabla: " + ex.getMessage());
        }
    }
    
    
    private void llenarTabla(List<AnalisisDTO> listaAnalisis) {
        DefaultTableModel modelo = (DefaultTableModel) tablaAnalisis.getModel();
        modelo.setRowCount(0);

        for (AnalisisDTO analisis : listaAnalisis) {
            modelo.addRow(new Object[]{
                analisis.getIdAnalisis(),
                analisis.getNombre(),
                analisis.getTipoMuestra() != null ? analisis.getTipoMuestra() : "N/A",
                analisis.getCantidadParametros()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Análisis", "Tipo Muestra", "Parametro"
            }
        ));
        jScrollPane1.setViewportView(tablaAnalisis);

        btnCancelar.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSeleccionar.setBackground(new java.awt.Color(0, 0, 0));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(this::btnSeleccionarActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Laboratorio Clinico Salud Total");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Catalogo de Análisi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionar)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSeleccionar))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        int fila = tablaAnalisis.getSelectedRow();

        if (fila != -1) {
            try {
                RegistroSolicitudPruebaFORM pantallaSolicitud = controlNavegacion.getPantallaSolicitudActual();

                if (pantallaSolicitud == null) {
                    javax.swing.JOptionPane.showMessageDialog(
                        this,
                        "Primero debes abrir una solicitud de prueba."
                    );
                    return;
                }

                int filaModelo = tablaAnalisis.convertRowIndexToModel(fila);

                AnalisisDTO analisisElegido = new AnalisisDTO();

                int idAnalisis = ((Number) tablaAnalisis.getModel().getValueAt(filaModelo, 0)).intValue();
                String nombre = convertirTexto(tablaAnalisis.getModel().getValueAt(filaModelo, 1));
                String tipoMuestra = convertirTexto(tablaAnalisis.getModel().getValueAt(filaModelo, 2));

                analisisElegido.setIdAnalisis(idAnalisis);
                analisisElegido.setNombre(nombre);
                analisisElegido.setTipoMuestra(tipoMuestra);

                pantallaSolicitud.agregarAnalisisTemporal(analisisElegido);
                this.dispose();

            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Error al seleccionar " + ex.getMessage()
                );
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Seleccione un análisis de la tabla.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
        
    }//GEN-LAST:event_btnSeleccionarActionPerformed

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
