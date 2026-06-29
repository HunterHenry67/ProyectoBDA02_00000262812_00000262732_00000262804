/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.AnalisisDTO;
import Negocio.AnalisisBO;
import Negocio.IAnalisisBO;
import Negocio.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BALAMRUSH
 */
public class CatalogoAnalisisFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;
    private IAnalisisBO analisisBO;
    private int paginaActual = 1;
    private int tamanoPagina = 5;
    private List<AnalisisDTO> analisis;
    private List<AnalisisDTO> analisisMostradosTabla;

    public CatalogoAnalisisFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
        this.analisisBO = new AnalisisBO();
        this.analisis = new ArrayList<>();
        this.analisisMostradosTabla = new ArrayList<>();
        setExtendedState(MAXIMIZED_BOTH);

        try {
            cargarTabla();
        } catch (PresentacionException ex) {
            saltoErrores(ex.getMessage());
        }
    }

    /**
     * 
     * @throws PresentacionException 
     */
    private void cargarTabla() throws PresentacionException {
        try {
            analisis = analisisBO.consultarTodos();
            paginaActual = 1;
            llenarTabla(analisis);
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al cargar la tabla con los análisis: " + ex.getMessage());
        }
    }

    /**
     * 
     * @param listaAnalisis 
     */
    private void llenarTabla(List<AnalisisDTO> listaAnalisis) {
        analisisMostradosTabla = new ArrayList<>(listaAnalisis);
        DefaultTableModel modelo = (DefaultTableModel) tablaAnalisis.getModel();
        modelo.setRowCount(0);
        int inicio = (paginaActual - 1) * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaAnalisis.size());
        if (inicio >= listaAnalisis.size()) {
            paginaActual = 1;
            inicio = 0;
            fin = Math.min(tamanoPagina, listaAnalisis.size());
        }
        for (int i = inicio; i < fin; i++) {
            AnalisisDTO analisisDTO = listaAnalisis.get(i);

            modelo.addRow(new Object[]{
                analisisDTO.getIdAnalisis(),
                analisisDTO.getNombre(),
                analisisDTO.getTipoMuestra(),
                analisisDTO.getCantidadParametros(),});
        }
        actualizarTextoPaginaAnalisis(listaAnalisis.size());
    }

    /**
     * 
     * @throws PresentacionException 
     */
    private void buscarAnalisis() throws PresentacionException {
        try {
            String entrada = txtFieldBuscar.getText().trim();
            if (entrada.isEmpty()) {
                cargarTabla();
                return;
            }
            String filtroSeleccionado = comboFiltroAnalisis.getSelectedItem().toString();
            List<AnalisisDTO> resultados;

            switch (filtroSeleccionado) {
                case "Nombre":
                    resultados = analisisBO.buscarPorNombre(entrada);
                    break;
                case "Tipo de Muestra":
                    resultados = analisisBO.buscarPorTipoMuestra(entrada);
                    break;
                case "Parametros":
                    Integer conversion = conversionNumero(entrada);
                    resultados = analisisBO.buscarPorCantidadParametro(conversion);
                    break;
                default:
                    resultados = analisisBO.buscarPorNombre(entrada);
                    break;
            }
            this.llenarTabla(resultados);
        } catch (NegocioException ex) {
            throw new PresentacionException("");
        }
    }

    /**
     * 
     * @param mensaje 
     */
    private void saltoErrores(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 
     * @param mensaje 
     */
    private void saltoAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 
     * @param texto
     * @return
     * @throws PresentacionException 
     */
    private Integer conversionNumero(String texto) throws PresentacionException {
        try {
            return Integer.valueOf(texto);

        } catch (NumberFormatException ex) {
            throw new PresentacionException("La búsqueda de parámetros debe de ser por medio de números." + ex.getMessage());
        }
    }

    /**
     * 
     * @param totalRegistros 
     */
    private void actualizarTextoPaginaAnalisis(int totalRegistros) {
        int totalPaginas = (int) Math.ceil((double) totalRegistros / tamanoPagina);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        lblPaginacion.setText("Página " + paginaActual + " de " + totalPaginas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtFieldBuscar = new javax.swing.JTextField();
        comboFiltroAnalisis = new javax.swing.JComboBox<>();
        btnAgregarAnalisis = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblPaginacion = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Catálogo de Ánalisis");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Catálogo de Análisis");

        tablaAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo de Muestra", "Parametros"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaAnalisis);

        btnBuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        comboFiltroAnalisis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Tipo de Muestra", "Parametros" }));
        comboFiltroAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFiltroAnalisisActionPerformed(evt);
            }
        });

        btnAgregarAnalisis.setBackground(new java.awt.Color(0, 204, 51));
        btnAgregarAnalisis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregarAnalisis.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarAnalisis.setText("Agregar Análisis");
        btnAgregarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAnalisisActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(255, 153, 153));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegresar.setText("Regresar ");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblPaginacion.setText("jLabel3");

        btnAnterior.setBackground(new java.awt.Color(0, 153, 255));
        btnAnterior.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAnterior.setForeground(new java.awt.Color(0, 0, 0));
        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setBackground(new java.awt.Color(0, 153, 255));
        btnSiguiente.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPaginacion, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(704, 704, 704)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(766, 766, 766)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(870, 870, 870)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1609, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(comboFiltroAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente)
                                .addGap(67, 67, 67)))))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboFiltroAnalisis)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPaginacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnterior)
                            .addComponent(btnSiguiente))
                        .addContainerGap(67, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAnalisisActionPerformed
        controlNavegacion.mostrarRegistroAltaAnalisis();
        this.dispose();
    }//GEN-LAST:event_btnAgregarAnalisisActionPerformed

    private void comboFiltroAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFiltroAnalisisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFiltroAnalisisActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            this.buscarAnalisis();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        controlNavegacion.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            llenarTabla(analisisMostradosTabla);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        int totalPaginas = (int) Math.ceil((double) analisisMostradosTabla.size() / tamanoPagina);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        if (paginaActual < totalPaginas) {
            paginaActual++;
            llenarTabla(analisisMostradosTabla);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAnalisis;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboFiltroAnalisis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPaginacion;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtFieldBuscar;
    // End of variables declaration//GEN-END:variables
}
