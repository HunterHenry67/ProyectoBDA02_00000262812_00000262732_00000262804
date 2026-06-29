/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.RangoDTO;
import DTO.RegistrarParametroDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BALAMRUSH
 */
public class RegistroParametroFORM extends javax.swing.JFrame {

    private RegistroAltaAnalisisFORM registroAltaAnalisisFORM;
    private List<RangoDTO> rangosTabla;
    private List<RangoDTO> rangos;
    private int paginaActualRango = 1;
    private int tamanoPaginaRango = 5;

    public RegistroParametroFORM(RegistroAltaAnalisisFORM registroAltaAnalisisFORM) {
        initComponents();
        this.registroAltaAnalisisFORM = registroAltaAnalisisFORM;
        this.rangos = new ArrayList<>();
        this.rangosTabla = new ArrayList<>();
        cargarTablaRangos();
    }

    /**
     * 
     * @throws PresentacionException 
     */
    private void guardarParametro() throws PresentacionException {
        if (txtFieldNombre.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar el nombre del parámetro.");
        }
        if (txtFieldOrdenReporte.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar el órden de reporte.");
        }
        this.convertirOrdenReporte();
        if (txtFieldNota.getText().trim().isEmpty()) {
            throw new PresentacionException("Favor de ingresar la nota descriptiva.");
        }
        if (comboUnidadMedida.getSelectedItem() == null) {
            throw new PresentacionException("Favor de seleccionar una unidad de medida.");
        }
        if (rangos.isEmpty()) {
            throw new PresentacionException("Favor de ingresar un rango en tu parámetro.");
        }

        RegistrarParametroDTO parametroDTO = new RegistrarParametroDTO();
        parametroDTO.setNombre(txtFieldNombre.getText().trim());
        parametroDTO.setOrdenReporte(convertirOrdenReporte());
        parametroDTO.setNotaDescriptiva(txtFieldNota.getText().trim());
        parametroDTO.setUnidadMedida(comboUnidadMedida.getSelectedItem().toString());
        parametroDTO.setRangos(rangos);

        if (registroAltaAnalisisFORM == null) {
            throw new PresentacionException("No se encontró la pantalla de análisis.");
        }
        registroAltaAnalisisFORM.agregarParametroAlAnalisis(parametroDTO);

        JOptionPane.showMessageDialog(this, "El parámetro ha sido agregado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    /**
     * 
     * @param rangoDTO
     * @throws PresentacionException 
     */
    public void agregarRangoAlParametro(RangoDTO rangoDTO) throws PresentacionException {
        if (rangoDTO == null) {
            throw new PresentacionException("El rango no puede estar vacío.");
        }
        rangos.add(rangoDTO);
        paginaActualRango = 1;
        cargarTablaRangos();
    }

    /**
     * 
     */
    private void cargarTablaRangos() {
        llenadoTablaRangos(rangos);
    }

    /**
     * 
     * @param listaRangos 
     */
    private void llenadoTablaRangos(List<RangoDTO> listaRangos) {

        rangosTabla = new ArrayList<>(listaRangos);
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        int inicio = (paginaActualRango - 1) * tamanoPaginaRango;
        int fin = Math.min(inicio + tamanoPaginaRango, listaRangos.size());
        if (inicio >= listaRangos.size()) {
            paginaActualRango = 1;
            inicio = 0;
            fin = Math.min(tamanoPaginaRango, listaRangos.size());
        }
        for (int i = inicio; i < fin; i++) {
            RangoDTO rango = listaRangos.get(i);
            modelo.addRow(new Object[]{
                rango.getSexo(),
                rango.getEdadInicial(),
                rango.getEdadFinal(),
                rango.getRangoInicial(),
                rango.getRangoFinal(),
                "Eliminar"
            });
        }
        actualizarTextoPaginaRangos(listaRangos.size());
    }

    /**
     * 
     * @return
     * @throws PresentacionException 
     */
    private Integer convertirOrdenReporte() throws PresentacionException {
        try {
            return Integer.valueOf(txtFieldOrdenReporte.getText().trim());
        } catch (NumberFormatException ex) {
            throw new PresentacionException("El orden de reporte debe ser un número.");
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
     * @throws PresentacionException 
     */
    private void buscarRango() throws PresentacionException {
        if (comboFiltro.getSelectedItem() == null) {
            throw new PresentacionException("Favor de seleccionar un filtro.");
        }
        String entrada = txtFieldBuscar.getText().trim();
        if (entrada.isEmpty()) {
            paginaActualRango = 1;
            cargarTablaRangos();
            return;
        }
        String filtro = comboFiltro.getSelectedItem().toString();
        List<RangoDTO> resultados = new ArrayList<>();
        for (RangoDTO rango : rangos) {
            switch (filtro) {
                case "Sexo":
                    if (rango.getSexo() != null && rango.getSexo().toString().toLowerCase().contains(entrada.toLowerCase())) {
                        resultados.add(rango);
                    }
                    break;
                case "Edad Inicial":
                    try {
                        Integer edadInicial = Integer.valueOf(entrada);
                        if (rango.getEdadInicial() != null && rango.getEdadInicial().equals(edadInicial)) {
                            resultados.add(rango);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("Para buscar por edad inicial debes escribir un número.");
                    }
                    break;
                case "Edad Final":
                    try {
                        Integer edadFinal = Integer.valueOf(entrada);
                        if (rango.getEdadFinal() != null && rango.getEdadFinal().equals(edadFinal)) {
                            resultados.add(rango);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("Para buscar por edad final debes escribir un número.");
                    }
                    break;
                case "Rango Inicial":
                    try {
                        Float rangoInicial = Float.valueOf(entrada);
                        if (rango.getRangoInicial() != null && rango.getRangoInicial().equals(rangoInicial)) {
                            resultados.add(rango);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("Para buscar por rango inicial debes escribir un número.");
                    }
                    break;
                case "Rango Final":
                    try {
                        Float rangoFinal = Float.valueOf(entrada);

                        if (rango.getRangoFinal() != null && rango.getRangoFinal().equals(rangoFinal)) {
                            resultados.add(rango);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("Para buscar por rango final debes escribir un número.");
                    }
                    break;
            }
        }
        paginaActualRango = 1;
        llenadoTablaRangos(resultados);
    }

    /**
     * 
     * @param totalRegistros 
     */
    private void actualizarTextoPaginaRangos(int totalRegistros) {
        int totalPaginas = (int) Math.ceil((double) totalRegistros / tamanoPaginaRango);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        lblPaginacion.setText("Página " + paginaActualRango + " de " + totalPaginas);
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
        txtFieldNombre = new javax.swing.JTextField();
        txtFieldOrdenReporte = new javax.swing.JTextField();
        txtFieldNota = new javax.swing.JTextField();
        comboUnidadMedida = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtFieldBuscar = new javax.swing.JTextField();
        comboFiltro = new javax.swing.JComboBox<>();
        btnAgregarRango = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnRegistrarParamtero = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        lblPaginacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Parámetro");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Registro / Alta de Análisis (Parámetros)");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Nota Descriptiva: ");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Unidad de Medida: ");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Órden de Reporte:");

        txtFieldOrdenReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldOrdenReporteActionPerformed(evt);
            }
        });

        comboUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mg/dL", "g/dL", "mmol/L", "U/L", "%" }));

        btnBuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        comboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sexo", "Edad Inicial", "Edad Final", "Rango Inicial", "Rango Final", " " }));

        btnAgregarRango.setBackground(new java.awt.Color(0, 204, 0));
        btnAgregarRango.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregarRango.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarRango.setText("Agregar Rango");
        btnAgregarRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarRangoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sexo", "Edad Inicial", "Edad Final", "Rango Inicial", "Rango Final", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel7.setText("Filtro Búsqueda");

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrarParamtero.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrarParamtero.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegistrarParamtero.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarParamtero.setText("Registrar");
        btnRegistrarParamtero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarParamteroActionPerformed(evt);
            }
        });

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

        lblPaginacion.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(7, 7, 7)))
                                    .addGap(139, 139, 139)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFieldNota, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFieldOrdenReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(344, 344, 344))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(comboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(246, 246, 246)
                                            .addComponent(btnAgregarRango, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane1))
                        .addGap(0, 146, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAnterior)
                        .addGap(252, 252, 252)
                        .addComponent(lblPaginacion, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnRegistrarParamtero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(444, 444, 444))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(468, 468, 468))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFieldOrdenReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtFieldNota, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarRango, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaginacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSiguiente)
                    .addComponent(btnRegistrarParamtero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnterior)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFieldOrdenReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldOrdenReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldOrdenReporteActionPerformed

    private void btnAgregarRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRangoActionPerformed
        RegistroRangoFORM pantallaAgregarRango = new RegistroRangoFORM(this);
        pantallaAgregarRango.setLocationRelativeTo(this);
        pantallaAgregarRango.setVisible(true);
    }//GEN-LAST:event_btnAgregarRangoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
                    "¿Quieres eliminar este rango?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmacion == JOptionPane.YES_OPTION) {
                int indiceReal = ((paginaActualRango - 1) * tamanoPaginaRango) + fila;
                if (indiceReal >= 0 && indiceReal < rangosTabla.size()) {
                    RangoDTO rangoEliminar = rangosTabla.get(indiceReal);
                    rangos.remove(rangoEliminar);
                    rangosTabla.remove(rangoEliminar);
                    llenadoTablaRangos(rangosTabla);
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            this.buscarRango();
        } catch (PresentacionException ex) {
            this.saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarParamteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarParamteroActionPerformed
        try {
            guardarParametro();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarParamteroActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (paginaActualRango > 1) {
            paginaActualRango--;
            llenadoTablaRangos(rangosTabla);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        int totalPaginas = (int) Math.ceil((double) rangosTabla.size() / tamanoPaginaRango);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        if (paginaActualRango < totalPaginas) {
            paginaActualRango++;
            llenadoTablaRangos(rangosTabla);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarRango;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarParamtero;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboFiltro;
    private javax.swing.JComboBox<String> comboUnidadMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblPaginacion;
    private javax.swing.JTextField txtFieldBuscar;
    private javax.swing.JTextField txtFieldNombre;
    private javax.swing.JTextField txtFieldNota;
    private javax.swing.JTextField txtFieldOrdenReporte;
    // End of variables declaration//GEN-END:variables
}
