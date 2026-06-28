/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DAO.AnalisisDAO;
import DAO.ConexionBD;
import DAO.IConexionBD;
import DAO.ParametroDAO;
import DAO.PruebaDAO;
import DAO.ResultadoDAO;
import DTO.ClienteDTO;
import DTO.DoctorDTO;
import DTO.ParametroDTO;
import DTO.PruebaDTO;
import DTO.RegistrarResultadoDTO;
import Negocio.NegocioException;
import Negocio.ParametroBO;
import Negocio.ResultadoBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BALAMRUSH
 */
public class RegistrarResultadosFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;

    private PruebaDTO prueba;
    private ClienteDTO cliente;
    private DoctorDTO doctor;

    private ParametroBO parametroBO;
    private ResultadoBO resultadoBO;
    IConexionBD conexion = new ConexionBD();
    private List<ParametroDTO> parametros;

    public RegistrarResultadosFORM(
            ControlNavegacionForms controlNavegacion,
            PruebaDTO prueba,
            ClienteDTO cliente,
            DoctorDTO doctor
    ) {
        initComponents();

        this.controlNavegacion = controlNavegacion;
        this.prueba = prueba;
        this.cliente = cliente;
        this.doctor = doctor;

        this.resultadoBO = new ResultadoBO(
                new ResultadoDAO(conexion),
                new PruebaDAO(),
                new ParametroDAO(conexion)
        );
        this.parametroBO = new ParametroBO(
                new ParametroDAO(conexion),
                new AnalisisDAO()
        );
        this.parametros = new ArrayList<>();

        setExtendedState(MAXIMIZED_BOTH);

        txtFieldNombrePaciente.setEditable(false);
        txtFieldNombreAnalisis1.setEditable(false);

        cargarDatosPaciente();
        cargarTablaResultados();
    }

    private void cargarDatosPaciente() {
        String nombreCompleto = "";

        if (cliente.getNombres() != null) {
            nombreCompleto = nombreCompleto + cliente.getNombres() + " ";
        }

        if (cliente.getApellidoPaterno() != null) {
            nombreCompleto = nombreCompleto + cliente.getApellidoPaterno() + " ";
        }

        if (cliente.getApellidoMaterno() != null) {
            nombreCompleto = nombreCompleto + cliente.getApellidoMaterno();
        }

        txtFieldNombrePaciente.setText(nombreCompleto.trim());
        txtFieldNombreAnalisis1.setText("Folio: " + prueba.getIdPrueba());
    }

    private void cargarTablaResultados() {
        try {
            parametros = parametroBO.listarTodos();

            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();
            modelo.setRowCount(0);

            for (ParametroDTO parametro : parametros) {
                modelo.addRow(new Object[]{
                    parametro.getNombre(),
                    parametro.getUnidadMedida(),
                    "",
                    "",
                    ""
                });
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void registrarResultados() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblResultados.getModel();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valor = modelo.getValueAt(i, 3);

                if (valor != null) {
                    String resultadoTexto = valor.toString().trim();

                    if (!resultadoTexto.isEmpty()) {
                        double resultadoNumero = Double.parseDouble(resultadoTexto);

                        ParametroDTO parametro = parametros.get(i);

                        String observacion = "";

                        Object valorObservacion = modelo.getValueAt(i, 4);

                        if (valorObservacion != null) {
                            observacion = valorObservacion.toString();
                        }

                        RegistrarResultadoDTO dto = new RegistrarResultadoDTO();
                        dto.setIdPrueba(prueba.getIdPrueba());
                        dto.setIdParametro(parametro.getIdParametro());
                        dto.setResultadoObtenido(resultadoNumero);
                        dto.setObservacion(observacion);

                        resultadoBO.registrarResultado(dto);
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Resultados registrados correctamente.");

            controlNavegacion.mostrarMenuPrincipal();
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El resultado debe ser numérico.");
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblNombrePaciente = new javax.swing.JLabel();
        lblNombreAnalisis = new javax.swing.JLabel();
        txtFieldNombrePaciente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnRegistrarAnalisis = new javax.swing.JButton();
        txtFieldNombreAnalisis1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

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

        lblTitulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTitulo.setText("Laboratorio Clínico Salud Total");

        lblSubtitulo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSubtitulo.setText("Registrar Resultados");

        lblNombrePaciente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNombrePaciente.setText("Nombre del paciente:");

        lblNombreAnalisis.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNombreAnalisis.setText("Nombre de Ánalisis:");

        txtFieldNombrePaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNombrePacienteActionPerformed(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parametro", "Unidad", "Rango Normal", "Resultado", "Observacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblResultadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblResultados);

        btnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        txtFieldNombreAnalisis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFieldNombreAnalisis1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(755, 755, 755))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblSubtitulo)
                        .addGap(851, 851, 851))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombrePaciente)
                            .addComponent(lblNombreAnalisis))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldNombreAnalisis1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(262, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnRegistrarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1510, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSubtitulo)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombrePaciente)
                    .addComponent(txtFieldNombrePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreAnalisis)
                    .addComponent(txtFieldNombreAnalisis1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegistrarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarAnalisisActionPerformed
        registrarResultados();
    }//GEN-LAST:event_btnRegistrarAnalisisActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblResultadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblResultadosMouseClicked

    }//GEN-LAST:event_tblResultadosMouseClicked

    private void txtFieldNombreAnalisis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNombreAnalisis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNombreAnalisis1ActionPerformed

    private void txtFieldNombrePacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFieldNombrePacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFieldNombrePacienteActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        BusquedaPacienteFORM pantalla = new BusquedaPacienteFORM(controlNavegacion);
        pantalla.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrarAnalisis;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombreAnalisis;
    private javax.swing.JLabel lblNombrePaciente;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtFieldNombreAnalisis1;
    private javax.swing.JTextField txtFieldNombrePaciente;
    // End of variables declaration//GEN-END:variables
}
