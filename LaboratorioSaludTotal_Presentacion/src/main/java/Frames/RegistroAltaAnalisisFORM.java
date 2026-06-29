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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BALAMRUSH
 */
public class RegistroAltaAnalisisFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;
    private IMuestraBO muestraBO;
    private IAnalisisBO analisisBO;
    private List<RegistrarParametroDTO> parametros;
    private List<RegistrarParametroDTO> parametrosMostradosTabla;
    private int paginaActual = 1;
    private int tamanoPagina = 5;

    public RegistroAltaAnalisisFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
        setExtendedState(MAXIMIZED_BOTH);
        this.muestraBO = new MuestraBO();
        this.analisisBO = new AnalisisBO();
        this.parametros = new ArrayList<>();
        this.parametrosMostradosTabla = new ArrayList<>();
        cargarTablaParametros();
        try {
            this.cargarMuestra();
        } catch (PresentacionException ex) {
            saltoErrores(ex.getMessage());
        }
    }

    /**
     *
     * @throws PresentacionException
     */
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

    /**
     *
     * @param parametroDTO
     * @throws PresentacionException
     */
    public void agregarParametroAlAnalisis(RegistrarParametroDTO parametroDTO) throws PresentacionException {
        if (parametroDTO == null) {
            throw new PresentacionException("Debe existir al menos un parámetro.");
        }
        if (parametroDTO.getRangos() == null || parametroDTO.getRangos().isEmpty()) {
            throw new PresentacionException("Debe haber por lo menos 1 rango.");
        }
        for (RegistrarParametroDTO parametro : parametros) {
            if (parametro.getOrdenReporte() != null && parametro.getOrdenReporte().equals(parametroDTO.getOrdenReporte())) {
                throw new PresentacionException(
                        "Ya existe un parámetro con ese órden, favor de elegir otro orden.");
            }
        }
        parametros.add(parametroDTO);
        cargarTablaParametros();
    }

    /**
     *
     * @throws PresentacionException
     */
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
            JOptionPane.showMessageDialog(this, "El análisis fue registrado correctamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            controlNavegacion.mostrarCatalogoAnalisis();
            this.dispose();
        } catch (NegocioException ex) {
            throw new PresentacionException("Error al registrar el análisis." + ex.getMessage());
        }
    }

    /**
     *
     */
    private void cargarTablaParametros() {
        llenadoTablaParametros(parametros);
    }

    /**
     *
     * @param parametroDTO
     * @throws PresentacionException
     */
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

    /**
     *
     * @param listaParametros
     */
    private void llenadoTablaParametros(List<RegistrarParametroDTO> listaParametros) {
        parametrosMostradosTabla = new ArrayList<>(listaParametros);
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        int inicio = (paginaActual - 1) * tamanoPagina;
        int fin = Math.min(inicio + tamanoPagina, listaParametros.size());
        if (inicio >= listaParametros.size()) {
            paginaActual = 1;
            inicio = 0;
            fin = Math.min(tamanoPagina, listaParametros.size());
        }
        for (int i = inicio; i < fin; i++) {
            RegistrarParametroDTO parametro = listaParametros.get(i);
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
        actualizarTextoPaginaParametros(listaParametros.size());
    }

    /**
     *
     * @throws PresentacionException
     */
    private void buscarParametro() throws PresentacionException {
        String entrada = txtFieldBuscar.getText();
        if (entrada.isEmpty()) {
            paginaActual = 1;
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
                        throw new PresentacionException("Favor de buscar el número por el cual quiere filtrar.");
                    }
                    break;
                case "Cantidad de Rangos":
                    try {
                        Integer cantidadBuscada = Integer.valueOf(entrada);
                        int cantidadRangos = 0;
                        if (parametro.getRangos() != null) {
                            cantidadRangos = parametro.getRangos().size();
                        }
                        if (cantidadRangos == cantidadBuscada) {
                            resultados.add(parametro);
                        }
                    } catch (NumberFormatException ex) {
                        throw new PresentacionException("Para buscar por cantidad de rangos se debe escribir un número.");
                    }
                    break;
                default:
                    if (parametro.getNombre() != null && parametro.getNombre().toLowerCase().contains(entrada.toLowerCase())) {
                        resultados.add(parametro);
                    }
                    break;
            }
        }
        paginaActual = 1;
        llenadoTablaParametros(resultados);
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
     * @param totalRegistros
     */
    private void actualizarTextoPaginaParametros(int totalRegistros) {
        int totalPaginas = (int) Math.ceil((double) totalRegistros / tamanoPagina);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        lblPaginacion.setText("Página " + paginaActual + " de " + totalPaginas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboBoxTipoMuestra = new javax.swing.JComboBox<>();
        txtFieldNombreAnalisis = new javax.swing.JTextField();
        txtFieldDescripcion = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        txtFieldBuscar = new javax.swing.JTextField();
        comboBoxFiltros = new javax.swing.JComboBox<>();
        btnAgregarNuevoParametro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnRegistrarAnalisis = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblPaginacion = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();

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
        jLabel2.setText("Registro / Alta de Análisis");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tipo de Muestra:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Nombre de Ánalisis:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Descripción:");

        comboBoxTipoMuestra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscar1.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        comboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Unidad de Medida", "Orden", "Cantidad de Rangos" }));

        btnAgregarNuevoParametro.setBackground(new java.awt.Color(0, 204, 0));
        btnAgregarNuevoParametro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAgregarNuevoParametro.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarNuevoParametro.setText("Agregar Nuevo Parámetro");
        btnAgregarNuevoParametro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevoParametroActionPerformed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Órden Reporte", "Nota Descriptiva", "Unidad Medida", "Cantidad de Rangos", "Eliminar"
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

        btnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(0, 0, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrarAnalisis.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrarAnalisis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegistrarAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAnalisis.setText("Registrar ");
        btnRegistrarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarAnalisisActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtro Búsqueda");

        lblPaginacion.setText("jLabel7");

        btnSiguiente.setBackground(new java.awt.Color(0, 153, 255));
        btnSiguiente.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSiguiente.setForeground(new java.awt.Color(0, 0, 0));
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(502, 502, 502)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboBoxTipoMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldNombreAnalisis)
                            .addComponent(txtFieldDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5)))
                                .addGap(88, 88, 88)
                                .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnAgregarNuevoParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 49, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAnterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPaginacion, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(126, 126, 126)
                                        .addComponent(btnSiguiente)
                                        .addGap(87, 87, 87)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRegistrarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(458, 458, 458))
            .addGroup(layout.createSequentialGroup()
                .addGap(537, 537, 537)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboBoxTipoMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFieldNombreAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarNuevoParametro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnterior)
                            .addComponent(lblPaginacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSiguiente)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlNavegacion.mostrarCatalogoAnalisis();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarNuevoParametroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevoParametroActionPerformed
        RegistroParametroFORM form = new RegistroParametroFORM(this);
        form.setLocationRelativeTo(this);
        form.setVisible(true);
    }//GEN-LAST:event_btnAgregarNuevoParametroActionPerformed

    private void btnRegistrarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAnalisisActionPerformed
        try {
            this.registrarAnalisis();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarAnalisisActionPerformed

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
                int indiceReal = ((paginaActual - 1) * tamanoPagina) + fila;
                if (indiceReal >= 0 && indiceReal < parametrosMostradosTabla.size()) {
                    RegistrarParametroDTO parametroEliminar = parametrosMostradosTabla.get(indiceReal);
                    parametros.remove(parametroEliminar);
                    parametrosMostradosTabla.remove(parametroEliminar);
                    cargarTablaParametros();
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            llenadoTablaParametros(parametrosMostradosTabla);
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        int totalPaginas = (int) Math.ceil((double) parametrosMostradosTabla.size() / tamanoPagina);
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        if (paginaActual < totalPaginas) {
            paginaActual++;
            llenadoTablaParametros(parametrosMostradosTabla);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarNuevoParametro;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarAnalisis;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> comboBoxFiltros;
    private javax.swing.JComboBox<Object> comboBoxTipoMuestra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblPaginacion;
    private javax.swing.JTextField txtFieldBuscar;
    private javax.swing.JTextField txtFieldDescripcion;
    private javax.swing.JTextField txtFieldNombreAnalisis;
    // End of variables declaration//GEN-END:variables
}
