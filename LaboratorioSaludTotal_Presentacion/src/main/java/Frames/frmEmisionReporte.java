/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.GuardarAnalisisDTO;
import DTO.MuestraDTO;
import DTO.RegistrarParametroDTO;
import Negocio.AnalisisBO;
import Negocio.IAnalisisBO;
import Negocio.IMuestraBO;
import Negocio.MuestraBO;
import Negocio.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BALAMRUSH
 */
public class frmEmisionReporte extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;
    private IMuestraBO muestraBO;
    private IAnalisisBO analisisBO;
    private List<RegistrarParametroDTO> parametros;

    public frmEmisionReporte(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
        setExtendedState(MAXIMIZED_BOTH);
        this.muestraBO = new MuestraBO();
        this.analisisBO = new AnalisisBO();
        this.parametros = new ArrayList<>();
        try {
            this.cargarMuestra();
        } catch (PresentacionException ex) {
            saltoErrores(ex.getMessage());
        }
    }

    private void cargarMuestra() throws PresentacionException {
        try {
            comboBoxTipoMuestra.removeAllItems();
            List<MuestraDTO> muestras = muestraBO.consultarTodasLasMuestras();
            for (MuestraDTO muestra : muestras) {
                comboBoxTipoMuestra.addItem(muestra);
            }
        } catch (NegocioException ex) {
            throw new PresentacionException("No se pudieran cargar los tipo de muestra: " + ex.getMessage());
        }
    }

    private void registrarAnalisis() throws PresentacionException {
        try {
            if (txtFieldNombreAnalisis.getText().trim().isEmpty()) {
                throw new PresentacionException("Favor de ingresar nombre del análisis.");
            }
            if (txtFieldDescripcion.getText().trim().isEmpty()) {
                throw new PresentacionException("Favor de ingresar una descripción.");
            }
            if (comboBoxTipoMuestra.getSelectedItem() == null) {
                throw new PresentacionException("Favor de seleccionar un tipo de muestra.");
            }
            if (parametros.isEmpty()) {
                throw new PresentacionException("No puedes agregar un análisis sin parámetros.");
            }
            MuestraDTO muestraSeleccionada = (MuestraDTO) comboBoxTipoMuestra.getSelectedItem();
            GuardarAnalisisDTO guardarAnalisisDTO = new GuardarAnalisisDTO();
            guardarAnalisisDTO.setNombre(txtFieldNombreAnalisis.getText().trim());
            guardarAnalisisDTO.setNota(txtFieldDescripcion.getText().trim());
            guardarAnalisisDTO.setIdMuestra(muestraSeleccionada.getIdMuestra());
            guardarAnalisisDTO.setParametros(parametros);
            analisisBO.guardarAnalisis(guardarAnalisisDTO);
            JOptionPane.showMessageDialog(this, "Registro Exitoso", "El análisis fue registrado correctamente.", JOptionPane.INFORMATION_MESSAGE);
            controlNavegacion.mostrarCatalogoAnalisis();
            this.dispose();
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al registrar el análisis." + ex.getMessage());
        }
    }

    private void cargarTablaParametros() {
        llenadoTablaParametros(parametros);
    }

    public void parametrosTemporales(RegistrarParametroDTO parametroDTO) throws PresentacionException {
        if (parametroDTO == null) {
            throw new PresentacionException("Debe de existir al menos un parámetro.");
        }
        if (parametroDTO.getRangos() == null || parametroDTO.getRangos().isEmpty()) {
            throw new PresentacionException("Debe de haber por lo menos 1 rango.");
        }

        parametros.add(parametroDTO);
        cargarTablaParametros();
    }

    private void llenadoTablaParametros(List<RegistrarParametroDTO> listaParametros) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (RegistrarParametroDTO parametro : listaParametros) {
            int cantidadRangos = 0;
            if (parametro.getRangos() != null) {
                cantidadRangos = parametro.getRangos().size();
            }
            modelo.addRow(new Object[]{
                parametro.getNombre(),
                parametro.getOrdenReporte(),
                parametro.getNotaDescriptiva(),
                parametro.getUnidadMedida(),
                cantidadRangos,
                "Eliminar"
            });
        }
    }

    private void buscarParametro() throws PresentacionException {
        String entrada = txtFieldBuscar.getText();
        if (entrada.isEmpty()) {
            cargarTablaParametros();
            return;
        }
        String filtro = comboBoxFiltros.getSelectedItem().toString();
        List<RegistrarParametroDTO> resultados = new ArrayList<>();

        for (RegistrarParametroDTO parametro : parametros) {
            switch (filtro) {
                case "Nombre":
                    if (parametro.getNombre() != null && parametro.getNombre().toLowerCase().contains(entrada.toLowerCase())) {
                        resultados.add(parametro);
                    }
                    break;
                case "Unidad de Medida":
                    if (parametro.getUnidadMedida() != null && parametro.getUnidadMedida().toLowerCase().contains(entrada.toLowerCase())) {
                        resultados.add(parametro);
                    }
                    break;
                case "Orden":
                    try {
                        Integer orden = Integer.valueOf(entrada);
                        if (parametro.getOrdenReporte() != null && parametro.getOrdenReporte().equals(orden)) {
                            resultados.add(parametro);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("favor de buscar el número por el cual quiere filtrar.");
                    }
                    break;
                default:
                    if (parametro.getNombre() != null && parametro.getNombre().toLowerCase().contains(entrada.toLowerCase())) {
                        resultados.add(parametro);
                    }
                    break;
            }
        }
        llenadoTablaParametros(resultados);
    }

    private void saltoErrores(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void saltoAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        txtFieldBuscar = new javax.swing.JTextField();
        comboBoxFiltros = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnRegresar1 = new javax.swing.JButton();

        btnBuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro/Alta de Análisis");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Emision Reporte");

        btnBuscar1.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        comboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Folio", "Paciente", "Analisis", " " }));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Paciente", "Analisis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnImprimir.setBackground(new java.awt.Color(0, 0, 0));
        btnImprimir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("Imprimir Reporte");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtro Búsqueda");

        btnRegresar1.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar1.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar1.setText("Regresar");
        btnRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(755, 755, 755))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(851, 851, 851))))
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImprimir)))))
                .addGap(5, 267, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(1808, Short.MAX_VALUE)
                    .addComponent(btnRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(371, 371, 371)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(945, Short.MAX_VALUE)
                    .addComponent(btnRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        try {
            this.buscarParametro();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            this.registrarAnalisis();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        int columna = jTable1.getSelectedColumn();
        if (fila == -1) {
            return;
        }
        int columnaEliminar = 5;
        if (columna == columnaEliminar) {
            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Quiéres eliminar el parámetro?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                parametros.remove(fila);
                cargarTablaParametros();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegresar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRegresar1;
    private javax.swing.JComboBox<String> comboBoxFiltros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
