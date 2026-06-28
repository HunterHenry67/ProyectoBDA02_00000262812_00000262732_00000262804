/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;
import DTO.ClienteDTO;
import DTO.DoctorDTO;
import DTO.PruebaDTO;
import Negocio.ClienteBO;
import Negocio.DoctorBO;
import Negocio.NegocioException;
import Negocio.PruebaBO;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author Andre
 */

public class BusquedaPacienteFORM extends JFrame {

    private ControlNavegacionForms controlNavegacion;

    private JTextField txtFolio;
    private JTextField txtPaciente;
    private JTextField txtEdad;
    private JTextField txtDoctor;

    private PruebaDTO pruebaSeleccionada;
    private ClienteDTO clienteSeleccionado;
    private DoctorDTO doctorSeleccionado;

    public BusquedaPacienteFORM(ControlNavegacionForms controlNavegacion) {
        this.controlNavegacion = controlNavegacion;
        initComponents();
    }

    private void initComponents() {
        setTitle("Búsqueda de Paciente");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Laboratorio Clínico Salud Total");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 22));
        lblTitulo.setBounds(280, 40, 400, 35);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Búsqueda de Paciente");
        lblSubtitulo.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblSubtitulo.setBounds(350, 85, 250, 30);
        add(lblSubtitulo);

        JLabel lblFolio = new JLabel("Número de folio:");
        lblFolio.setFont(new Font("Dialog", Font.BOLD, 14));
        lblFolio.setBounds(180, 160, 150, 30);
        add(lblFolio);

        txtFolio = new JTextField();
        txtFolio.setBounds(340, 160, 250, 35);
        add(txtFolio);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(610, 160, 120, 35);
        btnBuscar.addActionListener(e -> buscarPaciente());
        add(btnBuscar);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setFont(new Font("Dialog", Font.BOLD, 14));
        lblPaciente.setBounds(180, 250, 150, 30);
        add(lblPaciente);

        txtPaciente = new JTextField();
        txtPaciente.setBounds(340, 250, 390, 35);
        txtPaciente.setEditable(false);
        add(txtPaciente);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Dialog", Font.BOLD, 14));
        lblEdad.setBounds(180, 300, 150, 30);
        add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(340, 300, 150, 35);
        txtEdad.setEditable(false);
        add(txtEdad);

        JLabel lblDoctor = new JLabel("Doctor asignado:");
        lblDoctor.setFont(new Font("Dialog", Font.BOLD, 14));
        lblDoctor.setBounds(180, 350, 150, 30);
        add(lblDoctor);

        txtDoctor = new JTextField();
        txtDoctor.setBounds(340, 350, 390, 35);
        txtDoctor.setEditable(false);
        add(txtDoctor);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 450, 140, 45);
        btnCancelar.addActionListener(e -> regresarMenu());
        add(btnCancelar);

        JButton btnRegistrar = new JButton("Registrar Resultados");
        btnRegistrar.setBounds(470, 450, 190, 45);
        btnRegistrar.addActionListener(e -> abrirRegistrarResultados());
        add(btnRegistrar);
    }

    private void buscarPaciente() {
        try {
            limpiarCampos();

            String folioTexto = txtFolio.getText().trim();

            if (folioTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa el número de folio.");
                return;
            }

            int folio;

            try {
                folio = Integer.parseInt(folioTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El folio debe ser numérico.");
                return;
            }

            PruebaBO pruebaBO = new PruebaBO();
            ClienteBO clienteBO = new ClienteBO();
            DoctorBO doctorBO = new DoctorBO();

            pruebaSeleccionada = pruebaBO.buscarPruebaPorId(folio);

            if (pruebaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la prueba.");
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
                txtDoctor.setText(nombreCompletoDoctor(doctorSeleccionado));
            } else {
                txtDoctor.setText("N/A");
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void abrirRegistrarResultados() {
        if (pruebaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Primero busca una prueba.");
            return;
        }

        if (clienteSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "La prueba no tiene paciente.");
            return;
        }

        RegistrarResultadosFORM pantalla = new RegistrarResultadosFORM(
                controlNavegacion,
                pruebaSeleccionada,
                clienteSeleccionado,
                doctorSeleccionado
        );

        pantalla.setVisible(true);
        dispose();
    }

    private void limpiarCampos() {
        pruebaSeleccionada = null;
        clienteSeleccionado = null;
        doctorSeleccionado = null;

        txtPaciente.setText("");
        txtEdad.setText("");
        txtDoctor.setText("");
    }

    private String nombreCompletoCliente(ClienteDTO cliente) {
        String nombre = "";

        if (cliente.getNombres() != null) {
            nombre = nombre + cliente.getNombres() + " ";
        }

        if (cliente.getApellidoPaterno() != null) {
            nombre = nombre + cliente.getApellidoPaterno() + " ";
        }

        if (cliente.getApellidoMaterno() != null) {
            nombre = nombre + cliente.getApellidoMaterno();
        }

        return nombre.trim();
    }

    private String nombreCompletoDoctor(DoctorDTO doctor) {
        String nombre = "";

        if (doctor.getNombres() != null) {
            nombre = nombre + doctor.getNombres() + " ";
        }

        if (doctor.getApellidoPaterno() != null) {
            nombre = nombre + doctor.getApellidoPaterno() + " ";
        }

        if (doctor.getApellidoMaterno() != null) {
            nombre = nombre + doctor.getApellidoMaterno();
        }

        return nombre.trim();
    }

    private String calcularEdad(ClienteDTO cliente) {
        if (cliente.getFechaNacimiento() == null) {
            return "N/A";
        }

        LocalDate fechaNacimiento = cliente.getFechaNacimiento().toLocalDate();
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();

        return String.valueOf(edad);
    }

    private void regresarMenu() {
        controlNavegacion.mostrarMenuPrincipal();
        dispose();
    }
}