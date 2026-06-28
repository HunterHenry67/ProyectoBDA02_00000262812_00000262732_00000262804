/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import DTO.ClienteDTO;
import DTO.DoctorDTO;
import DTO.PruebaDTO;
import Negocio.ClienteBO;
import Negocio.DoctorBO;
import Negocio.NegocioException;
import Negocio.PruebaBO;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JOptionPane;

/**
 *
 * @author BALAMRUSH
 */
public class BusquedaPacienteFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;

    private PruebaDTO pruebaSeleccionada;
    private ClienteDTO clienteSeleccionado;
    private DoctorDTO doctorSeleccionado;

    public BusquedaPacienteFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
        setExtendedState(MAXIMIZED_BOTH);

        txtPaciente.setEditable(false);
        txtEdad.setEditable(false);
        txtDoctorAsignado.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        lblDoctor = new javax.swing.JLabel();
        txtFieldEdadInicial = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtDoctorAsignado = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblLinea = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtPaciente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Rango");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Busqueda de Paciente");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Numero de folio:");

        lblPaciente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPaciente.setText("Paciente:");

        lblEdad.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEdad.setText("Edad: ");

        lblDoctor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDoctor.setText("Doctor Asignado:");

        btnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Registrar Resultados");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblLinea.setText("------------------------------------------------------------------------------------------------------------------------------------------");

        btnBuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(842, 842, 842))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(794, 794, 794)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(lblLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEdad)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(234, 234, 234)
                                    .addComponent(txtFieldEdadInicial, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDoctor)
                                        .addComponent(lblPaciente))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDoctorAsignado, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                        .addComponent(txtEdad)
                                        .addComponent(txtPaciente)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(82, 82, 82)
                                    .addComponent(btnRegresar))))))
                .addContainerGap(756, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFieldEdadInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lblLinea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaciente)
                    .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEdad)
                    .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDoctor)
                    .addComponent(txtDoctorAsignado, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        controlNavegacion.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarPaciente();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        abrirRegistrarResultados();
    }//GEN-LAST:event_btnRegresarActionPerformed
    private void buscarPaciente() {
        try {
            limpiarCampos();

            String folioTexto = txtFieldEdadInicial.getText().trim();

            if (folioTexto.isEmpty()) {
                mostrarAdvertencia("Ingresa el número de folio.");
                return;
            }

            int folio;

            try {
                folio = Integer.parseInt(folioTexto);
            } catch (NumberFormatException ex) {
                mostrarAdvertencia("El folio debe ser numérico.");
                return;
            }

            PruebaBO pruebaBO = new PruebaBO();
            ClienteBO clienteBO = new ClienteBO();
            DoctorBO doctorBO = new DoctorBO();

            pruebaSeleccionada = pruebaBO.buscarPruebaPorId(folio);

            if (pruebaSeleccionada == null) {
                mostrarAdvertencia("No se encontró una prueba con ese folio.");
                return;
            }

            if (pruebaSeleccionada.getIdCliente() != null) {
                clienteSeleccionado = clienteBO.buscarClienteId(pruebaSeleccionada.getIdCliente());
            }

            if (pruebaSeleccionada.getIdDoctor() != null) {
                doctorSeleccionado = doctorBO.consultarPorID(pruebaSeleccionada.getIdDoctor());
            }

            if (clienteSeleccionado != null) {
                txtPaciente.setText(nombreCompletoCliente(clienteSeleccionado));
                txtEdad.setText(calcularEdad(clienteSeleccionado));
            }

            if (doctorSeleccionado != null) {
                txtDoctorAsignado.setText(nombreCompletoDoctor(doctorSeleccionado));
            } else {
                txtDoctorAsignado.setText("N/A");
            }

        } catch (NegocioException ex) {
            mostrarError(ex.getMessage());
        } catch (Exception ex) {
            mostrarError("Error al buscar paciente: " + ex.getMessage());
        }
    }

    private void abrirRegistrarResultados() {
        if (pruebaSeleccionada == null) {
            mostrarAdvertencia("Primero busca una prueba válida.");
            return;
        }

        if (clienteSeleccionado == null) {
            mostrarAdvertencia("La prueba no tiene paciente asignado.");
            return;
        }

        RegistrarResultadosFORM pantalla = new RegistrarResultadosFORM(
                controlNavegacion,
                pruebaSeleccionada,
                clienteSeleccionado,
                doctorSeleccionado
        );

        pantalla.setVisible(true);
        this.dispose();
    }

    private void limpiarCampos() {
        pruebaSeleccionada = null;
        clienteSeleccionado = null;
        doctorSeleccionado = null;

        txtPaciente.setText("");
        txtEdad.setText("");
        txtDoctorAsignado.setText("");
    }

    private String nombreCompletoCliente(ClienteDTO cliente) {
        String nombres = "";
        String paterno = "";
        String materno = "";

        if (cliente.getNombres() != null) {
            nombres = cliente.getNombres();
        }

        if (cliente.getApellidoPaterno() != null) {
            paterno = cliente.getApellidoPaterno();
        }

        if (cliente.getApellidoMaterno() != null) {
            materno = cliente.getApellidoMaterno();
        }

        return (nombres + " " + paterno + " " + materno).trim();
    }

    private String nombreCompletoDoctor(DoctorDTO doctor) {
        String nombres = "";
        String paterno = "";
        String materno = "";

        if (doctor.getNombres() != null) {
            nombres = doctor.getNombres();
        }

        if (doctor.getApellidoPaterno() != null) {
            paterno = doctor.getApellidoPaterno();
        }

        if (doctor.getApellidoMaterno() != null) {
            materno = doctor.getApellidoMaterno();
        }

        return (nombres + " " + paterno + " " + materno).trim();
    }

    private String calcularEdad(ClienteDTO cliente) {
        if (cliente.getFechaNacimiento() == null) {
            return "N/A";
        }

        LocalDate nacimiento = cliente.getFechaNacimiento().toLocalDate();
        int edad = Period.between(nacimiento, LocalDate.now()).getYears();

        return String.valueOf(edad);
    }

    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblLinea;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JTextField txtDoctorAsignado;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtFieldEdadInicial;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
