/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.AnalisisDTO;
import DTO.ClienteDTO;
import DTO.DoctorDTO;
import DTO.PruebaDTO;
import Negocio.IPruebaBO;
import Negocio.PruebaBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class RegistroSolicitudPruebaFORM extends javax.swing.JFrame {
    private ControlNavegacionForms controlNavegacion;
    private IPruebaBO pruebaBO;
    private ClienteDTO clienteSeleccionado;
    private DoctorDTO doctorSeleccionado;
    private List<AnalisisDTO> analisisAgregados;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroSolicitudPruebaFORM.class.getName());

    /**
     * Creates new form RegistroSolicitudPruebaFORM
     */
    public RegistroSolicitudPruebaFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
        this.controlNavegacion.setPantallaSolicitudActual(this); 
        setExtendedState(MAXIMIZED_BOTH);

        this.pruebaBO = new PruebaBO();
        this.analisisAgregados = new ArrayList<>();

        configurarFiltrosAnalisis();
        txtFolio.setEditable(false);
        txtFechaHora.setEditable(false);
        txtCliente.setEditable(false);
        txtDoctor.setEditable(false);
        txtFolio.setText("Asignado al guardar...");
        txtFechaHora.setText(java.time.LocalDateTime.now().toString());

        btnBuscarDoctor.addActionListener(evt -> {
            this.controlNavegacion.mostrarCatalogoDoctores();
        });
        
        cargarTablaAnalisis();
    }
    
    private void configurarFiltrosAnalisis() {
        comboBoxFiltro.setModel(new DefaultComboBoxModel<>(
                new String[]{"Todos", "Análisis", "Tipo Muestra"}
        ));

        txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { cargarTablaAnalisis(); }
            @Override
            public void removeUpdate(DocumentEvent e) { cargarTablaAnalisis(); }
            @Override
            public void changedUpdate(DocumentEvent e) { cargarTablaAnalisis(); }
        });

        comboBoxFiltro.addActionListener(e -> cargarTablaAnalisis());
    }
    
    public void setClienteSeleccionado(ClienteDTO cliente) {
        if (cliente != null) {
            this.clienteSeleccionado = cliente;
            txtCliente.setText(cliente.getNombres());
        }
    }

    public void setDoctorSeleccionado(DoctorDTO doctor) {
        if (doctor != null) {
            this.doctorSeleccionado = doctor;
            txtDoctor.setText(doctor.getNombres()); 
        }
    }

    public void agregarAnalisisTemporal(AnalisisDTO analisis) throws PresentacionException {
        if (analisis == null) {
            throw new PresentacionException("Debe de existir un análisis seleccionado");
        }
 
        for (AnalisisDTO a : analisisAgregados) {
            if (a.getIdAnalisis().equals(analisis.getIdAnalisis())) {
                throw new PresentacionException("El análisis ya esta agregado en la tabla");
            }
        }
        analisisAgregados.add(analisis);
        cargarTablaAnalisis();
    }
    
    
    private void cargarTablaAnalisis() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAnalisis.getModel();
        modelo.setRowCount(0);

        String entrada = txtBuscador.getText().trim().toLowerCase();
        String filtro = comboBoxFiltro.getSelectedItem().toString();

        for (AnalisisDTO analisis : analisisAgregados) {
            String nombre = analisis.getNombre() != null
                    ? analisis.getNombre().toLowerCase()
                    : "";

            String tipoMuestra = analisis.getTipoMuestra() != null
                    ? analisis.getTipoMuestra().toLowerCase()
                    : "";

            boolean coincide = entrada.isEmpty();

            if (!entrada.isEmpty()) {
                switch (filtro) {
                    case "Análisis":
                        coincide = nombre.contains(entrada);
                        break;

                    case "Tipo Muestra":
                        coincide = tipoMuestra.contains(entrada);
                        break;

                    case "Todos":
                        coincide = nombre.contains(entrada)
                                || tipoMuestra.contains(entrada);
                        break;
                }
            }

            if (coincide) {
                modelo.addRow(new Object[]{
                    analisis.getIdAnalisis(),
                    analisis.getNombre(),
                    analisis.getTipoMuestra(),
                    "Eliminar"
                });
            }
        }
    }

    private void registrarSolicitud() throws PresentacionException {
        try {
            if (clienteSeleccionado == null) {
                throw new PresentacionException("Favor de buscar y seleccionar un cliente.");
            }
            if (doctorSeleccionado == null) {
                throw new PresentacionException("Favor de buscar y seleccionar un doctor.");
            }
            if (analisisAgregados.isEmpty()) {
                throw new PresentacionException("No puedes registrar una solicitud sin agregar análisis.");
            }

            PruebaDTO nuevaPrueba = new PruebaDTO();
            nuevaPrueba.setIdCliente(clienteSeleccionado.getIdCliente());
            nuevaPrueba.setIdDoctor(doctorSeleccionado.getIdDoctor());
            nuevaPrueba.setFechaHora(java.time.LocalDateTime.now());

            PruebaDTO pruebaGuardada = pruebaBO.agregarPrueba(nuevaPrueba);
            
            txtFolio.setText(String.valueOf(pruebaGuardada.getIdPrueba()));
            javax.swing.JOptionPane.showMessageDialog(this, "Registro Exitoso", "La solicitud fue registrada correctamente.", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            controlNavegacion.mostrarMenuPrincipal();
            this.dispose();

        } catch (Exception ex) {
            throw new PresentacionException("Error al registrar la solicitud: " + ex.getMessage());
        }
    }

    private void saltoErrores(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    private void saltoAdvertencia(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje, "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFolio = new javax.swing.JLabel();
        lblFechaHora = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        txtFechaHora = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtDoctor = new javax.swing.JTextField();
        txtFolio = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        btnBuscarDoctor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        btnAgregarAnalisis = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAnalisis = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Laboratorio Clinico Salud Total");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Registro de Solicitud de Prueba");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
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

        lblFolio.setText("Folio: ");

        lblFechaHora.setText("Fecha Hora:");

        lblCliente.setText("Cliente:");

        lblDoctor.setText("Doctor:");

        txtFechaHora.setEditable(false);

        txtCliente.setEditable(false);

        txtDoctor.setEditable(false);

        txtFolio.setEditable(false);

        btnBuscarCliente.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarCliente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarCliente.setForeground(new java.awt.Color(204, 204, 204));
        btnBuscarCliente.setText("Buscar Cliente");
        btnBuscarCliente.addActionListener(this::btnBuscarClienteActionPerformed);

        btnBuscarDoctor.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarDoctor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarDoctor.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarDoctor.setText("Buscar Doctor");
        btnBuscarDoctor.addActionListener(this::btnBuscarDoctorActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCliente)
                    .addComponent(btnBuscarDoctor))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFolio)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaHora)
                    .addComponent(txtFechaHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliente)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(txtDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarDoctor))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarAnalisis.setBackground(new java.awt.Color(51, 153, 0));
        btnAgregarAnalisis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarAnalisis.setText("Agregar Análisis");
        btnAgregarAnalisis.addActionListener(this::btnAgregarAnalisisActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnAgregarAnalisis)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarAnalisis))
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
                "ID", "Análisis", "Tipo Muestra", "Eliminar"
            }
        ));
        tablaAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAnalisisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAnalisis);

        btnCancelar.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnRegistrar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnRegistrar))))
                .addGap(159, 159, 159))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarCatalogoClientes();
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnAgregarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAnalisisActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarCatalogoAnalisisPrueba();
    }//GEN-LAST:event_btnAgregarAnalisisActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            this.registrarSolicitud();
        } catch (PresentacionException ex) {
            saltoAdvertencia(ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaAnalisisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAnalisisMouseClicked
        // TODO add your handling code here:
        int fila = tablaAnalisis.getSelectedRow();
        int columna = tablaAnalisis.getSelectedColumn();
        
        if (fila == -1) {
            return;
        }
        int columnaEliminar = 3; 
        
        if (columna == columnaEliminar) {
            int confirmacion = javax.swing.JOptionPane.showConfirmDialog( this,"¿Quiéres eliminar el análisis de la lista?","Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirmacion == javax.swing.JOptionPane.YES_OPTION) {
                analisisAgregados.remove(fila);
                cargarTablaAnalisis(); 
            }
        }
    }//GEN-LAST:event_tablaAnalisisMouseClicked

    private void btnBuscarDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDoctorActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarCatalogoDoctores();
    }//GEN-LAST:event_btnBuscarDoctorActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarAnalisis;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnBuscarDoctor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblFechaHora;
    private javax.swing.JLabel lblFolio;
    private javax.swing.JTable tablaAnalisis;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDoctor;
    private javax.swing.JTextField txtFechaHora;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
