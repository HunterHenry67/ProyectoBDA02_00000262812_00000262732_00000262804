/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.ClienteDTO;
import Negocio.IClienteBO;
import Negocio.NegocioException;
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
public class CatalogoClientesFORM extends javax.swing.JFrame {
    private ControlNavegacionForms controlNavegacion;
    private IClienteBO clienteBO;
    private List<ClienteDTO> clientes;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CatalogoClientesFORM.class.getName());

    /**
     * Creates new form CatalogoClientesFORM
     */
    public CatalogoClientesFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        
        this.controlNavegacion = controlNavegacion;
        this.clienteBO = new Negocio.ClienteBO();
        this.clientes = new ArrayList<>(); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        configurarFiltrosClientes();
        
        try {
            cargarTabla();
        } catch (PresentacionException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarTabla() throws PresentacionException {
        try {
            clientes = clienteBO.ObtenerClientes();
            llenarTabla(clientes);
        } catch (Exception ex) {
            throw new PresentacionException("Error al cargar la tabla con los clientes: " + ex.getMessage());
        }
    }
    
    private void llenarTabla(List<ClienteDTO> listaClientes) {
        DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
        modelo.setRowCount(0);

        for (ClienteDTO cliente : listaClientes) {
            String nombre = cliente.getNombres() != null ? cliente.getNombres() : "";
            String apellidoPaterno = cliente.getApellidoPaterno() != null ? cliente.getApellidoPaterno() : "";
            String apellidoMaterno = cliente.getApellidoMaterno() != null ? cliente.getApellidoMaterno() : "";
            String nombreCompleto = (nombre + " " + apellidoPaterno + " " + apellidoMaterno).trim();

            modelo.addRow(new Object[]{ cliente.getIdCliente(), nombreCompleto, cliente.getFechaNacimiento(), cliente.getTipoSangre(), cliente.getSexo() });
        }
    }
    
    private void configurarFiltrosClientes() {
        comboBoxFiltro.setModel(new DefaultComboBoxModel<>(
                new String[]{"Todos", "ID", "Nombre", "Fecha Nacimiento", "Tipo Sangre", "Sexo"}
        ));

        txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { filtrarClientes(); }
            @Override public void removeUpdate(DocumentEvent e) { filtrarClientes(); }
            @Override public void changedUpdate(DocumentEvent e) { filtrarClientes(); }
        });

        comboBoxFiltro.addActionListener(e -> filtrarClientes());
    }

    private void filtrarClientes() {
        DefaultTableModel modelo = (DefaultTableModel) tablaClientes.getModel();
        modelo.setRowCount(0);

        String entrada = txtBuscador.getText().trim().toLowerCase();
        String filtro = comboBoxFiltro.getSelectedItem().toString();

        for (ClienteDTO cliente : clientes) {
            String id = String.valueOf(cliente.getIdCliente()).toLowerCase();
            String nombre = cliente.getNombres() != null ? cliente.getNombres().toLowerCase() : "";
            String apellidoPaterno = cliente.getApellidoPaterno() != null ? cliente.getApellidoPaterno().toLowerCase() : "";
            String apellidoMaterno = cliente.getApellidoMaterno() != null ? cliente.getApellidoMaterno().toLowerCase() : "";
            String nombreCompleto = (nombre + " " + apellidoPaterno + " " + apellidoMaterno).trim();
            String fechaNacimiento = cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString().toLowerCase() : "";
            String tipoSangre = cliente.getTipoSangre() != null ? cliente.getTipoSangre().toLowerCase() : "";
            String sexo = cliente.getSexo() != null ? cliente.getSexo().toLowerCase() : "";

            boolean coincide = entrada.isEmpty();

            if (!entrada.isEmpty()) {
                switch (filtro) {
                    case "ID": coincide = id.contains(entrada); break;
                    case "Nombre": coincide = nombreCompleto.contains(entrada); break;
                    case "Fecha Nacimiento": coincide = fechaNacimiento.contains(entrada); break;
                    case "Tipo Sangre": coincide = tipoSangre.contains(entrada); break;
                    case "Sexo": coincide = sexo.contains(entrada); break;
                    case "Todos": coincide = id.contains(entrada) || nombreCompleto.contains(entrada) || fechaNacimiento.contains(entrada) || tipoSangre.contains(entrada) || sexo.contains(entrada); break;
                }
            }

            if (coincide) {
                modelo.addRow(new Object[]{ cliente.getIdCliente(), nombreCompleto, cliente.getFechaNacimiento(), cliente.getTipoSangre(), cliente.getSexo() });
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Laboratorio Clinico Salud Total");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Catalogo de Clientes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(78, 78, 78))
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

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Fecha Nacimiento", "Tipo de Sangre", "Sexo"
            }
        ));
        jScrollPane1.setViewportView(tablaClientes);

        btnCancelar.setBackground(new java.awt.Color(255, 204, 204));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSeleccionar.setBackground(new java.awt.Color(0, 0, 0));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(this::btnSeleccionarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionar)
                        .addGap(58, 58, 58))))
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
        int fila = tablaClientes.getSelectedRow();

        if (fila != -1) {
            try {
                RegistroSolicitudPruebaFORM pantallaSolicitud = controlNavegacion.getPantallaSolicitudActual();

                if (pantallaSolicitud == null) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Primero debes abrir una solicitud de prueba.");
                    return;
                }

                int filaModelo = tablaClientes.convertRowIndexToModel(fila);
                int idCliente = ((Number) tablaClientes.getModel().getValueAt(filaModelo, 0)).intValue();

                ClienteDTO cliElegido = clienteBO.buscarClienteId(idCliente);
                pantallaSolicitud.setClienteSeleccionado(cliElegido);

                this.dispose();
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al seleccionar " + ex.getMessage());
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Por favor seleccione un cliente de la tabla");
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBuscador;
    // End of variables declaration//GEN-END:variables
}
