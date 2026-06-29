/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frames;

import DTO.AnalisisDTO;
import DTO.ClienteDTO;
import DTO.DoctorDTO;
import DTO.PruebaDTO;
import Negocio.IPruebaBO;
import Negocio.PruebaBO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre
 */
public class RegistroSolicitudPruebaFORM extends JFrame {

    private ControlNavegacionForms controlNavegacion;
    private IPruebaBO pruebaBO;

    private ClienteDTO clienteSeleccionado;
    private DoctorDTO doctorSeleccionado;
    private List<AnalisisDTO> analisisAgregados;

    private JTextField txtFolio;
    private JTextField txtFechaHora;
    private JTextField txtCliente;
    private JTextField txtDoctor;
    private JTextField txtBuscador;

    private JComboBox<String> comboBoxFiltro;
    private JTable tablaAnalisis;

    private JButton btnBuscarCliente;
    private JButton btnBuscarDoctor;
    private JButton btnAgregarAnalisis;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistroSolicitudPruebaFORM(ControlNavegacionForms controlNavegacion) {
        this.controlNavegacion = controlNavegacion;
        this.controlNavegacion.setPantallaSolicitudActual(this);

        this.pruebaBO = new PruebaBO();
        this.analisisAgregados = new ArrayList<>();

        initComponents();
        configurarPantalla();
        cargarTablaAnalisis();
    }

    private void configurarPantalla() {
        setTitle("Registro de Solicitud de Prueba");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        txtFolio.setEditable(false);
        txtFechaHora.setEditable(false);
        txtCliente.setEditable(false);
        txtDoctor.setEditable(false);

        txtFolio.setText("Asignado al guardar...");
        txtFechaHora.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        btnBuscarDoctor.addActionListener(evt -> {
            this.controlNavegacion.mostrarCatalogoDoctores();
        });
        
        configurarFiltrosAnalisis();
        cargarTablaAnalisis();
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
            throw new PresentacionException("Debe existir un analisis seleccionado.");
        }

        for (AnalisisDTO a : analisisAgregados) {
            if (a.getIdAnalisis().equals(analisis.getIdAnalisis())) {
                throw new PresentacionException("El analisis ya esta agregado en la tabla.");
            }
        }

        analisisAgregados.add(analisis);
        cargarTablaAnalisis();
    }

    private void cargarTablaAnalisis() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAnalisis.getModel();
        modelo.setRowCount(0);

        for (AnalisisDTO analisis : analisisAgregados) {
            modelo.addRow(new Object[]{
                analisis.getIdAnalisis(),
                analisis.getNombre(),
                analisis.getTipoMuestra(),
                "Eliminar"
            });
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
                throw new PresentacionException("No puedes registrar una solicitud sin agregar analisis.");
            }

            PruebaDTO nuevaPrueba = new PruebaDTO();
            nuevaPrueba.setIdCliente(clienteSeleccionado.getIdCliente());
            nuevaPrueba.setIdDoctor(doctorSeleccionado.getIdDoctor());
            nuevaPrueba.setFechaHora(java.time.LocalDateTime.now());
            nuevaPrueba.setAnalisisAgregados(analisisAgregados);

            PruebaDTO pruebaGuardada = pruebaBO.agregarPrueba(nuevaPrueba);

            txtFolio.setText(String.valueOf(pruebaGuardada.getIdPrueba()));

            JOptionPane.showMessageDialog(
                    this,
                    "La solicitud fue registrada correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            controlNavegacion.mostrarMenuPrincipal();
            dispose();

        } catch (Exception ex) {
            throw new PresentacionException("Error al registrar la solicitud: " + ex.getMessage());
        }
    }

    private void eliminarAnalisisSeleccionado() {
        int fila = tablaAnalisis.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un analisis para eliminar.");
            return;
        }

        int filaModelo = tablaAnalisis.convertRowIndexToModel(fila);
        analisisAgregados.remove(filaModelo);
        cargarTablaAnalisis();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelTitulo = new JPanel(new FlowLayout());
        panelTitulo.add(new JLabel("Laboratorio Clinico Salud Total - Registro de Solicitud de Prueba"));

        JPanel panelDatos = new JPanel(new FlowLayout());

        txtFolio = new JTextField(10);
        txtFechaHora = new JTextField(18);
        txtCliente = new JTextField(15);
        txtDoctor = new JTextField(15);

        btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarDoctor = new JButton("Buscar Doctor");

        btnBuscarCliente.addActionListener(e -> controlNavegacion.mostrarCatalogoClientes());
        btnBuscarDoctor.addActionListener(e -> controlNavegacion.mostrarCatalogoDoctores());

        panelDatos.add(new JLabel("Folio:"));
        panelDatos.add(txtFolio);
        panelDatos.add(new JLabel("Fecha Hora:"));
        panelDatos.add(txtFechaHora);
        panelDatos.add(new JLabel("Cliente:"));
        panelDatos.add(txtCliente);
        panelDatos.add(btnBuscarCliente);
        panelDatos.add(new JLabel("Doctor:"));
        panelDatos.add(txtDoctor);
        panelDatos.add(btnBuscarDoctor);

        JPanel panelBusqueda = new JPanel(new FlowLayout());

        txtBuscador = new JTextField(18);
        comboBoxFiltro = new JComboBox<>(new String[]{"Todos", "ID", "Nombre", "Tipo Muestra"});
        btnAgregarAnalisis = new JButton("Agregar Analisis");

        btnAgregarAnalisis.addActionListener(e -> controlNavegacion.mostrarCatalogoAnalisisPrueba());

        panelBusqueda.add(txtBuscador);
        panelBusqueda.add(comboBoxFiltro);
        panelBusqueda.add(btnAgregarAnalisis);

        tablaAnalisis = new JTable(new DefaultTableModel(
                new Object[]{"ID", "Analisis", "Tipo Muestra", "Accion"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tablaAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int columna = tablaAnalisis.getSelectedColumn();

                if (columna == 3) {
                    eliminarAnalisisSeleccionado();
                }
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaAnalisis);

        JPanel panelBotones = new JPanel(new FlowLayout());

        btnCancelar = new JButton("Cancelar");
        btnRegistrar = new JButton("Registrar");

        btnCancelar.addActionListener(e -> {
            controlNavegacion.mostrarMenuPrincipal();
            dispose();
        });

        btnRegistrar.addActionListener(e -> {
            try {
                registrarSolicitud();
            } catch (PresentacionException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBotones.add(btnCancelar);
        panelBotones.add(btnRegistrar);

        JPanel panelArriba = new JPanel(new BorderLayout());
        panelArriba.add(panelTitulo, BorderLayout.NORTH);
        panelArriba.add(panelDatos, BorderLayout.CENTER);
        panelArriba.add(panelBusqueda, BorderLayout.SOUTH);

        panelPrincipal.add(panelArriba, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
        pack();
    }
}
