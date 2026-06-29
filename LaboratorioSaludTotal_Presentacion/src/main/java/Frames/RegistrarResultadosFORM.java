/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import DTO.RangoDTO;
import DTO.RegistrarResultadoDTO;
import Negocio.NegocioException;
import Negocio.ParametroBO;
import Negocio.RangoBO;
import Negocio.ResultadoBO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla que permite al usuario capturar y registrar los resultados clínicos de una prueba específica
 * @author Andre
 */
public class RegistrarResultadosFORM extends JFrame {

    private ControlNavegacionForms controlNavegacion;

    private PruebaDTO prueba;
    private ClienteDTO cliente;
    private DoctorDTO doctor;

    private ParametroBO parametroBO;
    private ResultadoBO resultadoBO;
    private RangoBO rangoBO;

    private List<ParametroDTO> parametros;

    private JTextField txtPaciente;
    private JTextField txtAnalisis;
    private JTable tabla;

    public RegistrarResultadosFORM(ControlNavegacionForms controlNavegacion,
            PruebaDTO prueba,
            ClienteDTO cliente,
            DoctorDTO doctor) {

        this.controlNavegacion = controlNavegacion;
        this.prueba = prueba;
        this.cliente = cliente;
        this.doctor = doctor;
        this.parametros = new ArrayList<>();

        inicializarBOs();
        initComponents();
        cargarDatos();
        cargarParametros();
    }

    /**
     * Crea los objetos de lógica de negocio necesarios para procesar los datos
     */
    private void inicializarBOs() {
        IConexionBD conexion = new ConexionBD();

        parametroBO = new ParametroBO(
                new ParametroDAO(),
                new AnalisisDAO()
        );

        resultadoBO = new ResultadoBO(
                new ResultadoDAO(conexion),
                new PruebaDAO(),
                new ParametroDAO()
        );

        rangoBO = new RangoBO();
    }

    /**
     * Configura los elementos visuales de la ventana
     */
    private void initComponents() {
        setTitle("Registrar Resultados");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Laboratorio Clínico Salud Total");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 22));
        lblTitulo.setBounds(330, 35, 400, 35);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Registrar Resultados");
        lblSubtitulo.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblSubtitulo.setBounds(410, 80, 250, 30);
        add(lblSubtitulo);

        JLabel lblPaciente = new JLabel("Paciente:");
        lblPaciente.setFont(new Font("Dialog", Font.BOLD, 14));
        lblPaciente.setBounds(130, 145, 100, 30);
        add(lblPaciente);

        txtPaciente = new JTextField();
        txtPaciente.setBounds(230, 145, 300, 35);
        txtPaciente.setEditable(false);
        add(txtPaciente);

        JLabel lblAnalisis = new JLabel("Análisis:");
        lblAnalisis.setFont(new Font("Dialog", Font.BOLD, 14));
        lblAnalisis.setBounds(130, 195, 100, 30);
        add(lblAnalisis);

        txtAnalisis = new JTextField();
        txtAnalisis.setBounds(230, 195, 300, 35);
        txtAnalisis.setEditable(false);
        add(txtAnalisis);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Parámetro", "Unidad", "Rango Normal", "Resultado", "Observación"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, true, true};

            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(130, 270, 740, 250);
        add(scroll);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(600, 540, 120, 40);
        btnCancelar.addActionListener(e -> regresarBusqueda());
        add(btnCancelar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(750, 540, 120, 40);
        btnRegistrar.addActionListener(e -> registrarResultados());
        add(btnRegistrar);
    }

    /**
     * Muestra los datos básicos del paciente y la prueba en la pantalla
     */
    private void cargarDatos() {
        txtPaciente.setText(nombreCompletoCliente(cliente));
        txtAnalisis.setText("Folio: " + prueba.getIdPrueba());
    }

    /**
     * Obtiene los parámetros asociados a la prueba y llena la tabla de resultados
     */
    private void cargarParametros() {
        try {
            parametros = parametroBO.listarPorPrueba(prueba.getIdPrueba());

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);

            for (ParametroDTO parametro : parametros) {
                modelo.addRow(new Object[]{
                    parametro.getNombre(),
                    parametro.getUnidadMedida(),
                    obtenerRangoNormal(parametro.getIdParametro()),
                    "",
                    ""
                });
            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    /**
     * Busca y formatea el rango normal para un parámetro específico
     * @param idParametro ID del parámetro
     * @return El rango como texto o "N/A" si no hay
     */
    private String obtenerRangoNormal(Integer idParametro) {
        try {
            List<RangoDTO> rangos = rangoBO.buscarRangosPorParametro(idParametro);

            if (rangos == null || rangos.isEmpty()) {
                return "N/A";
            }

            RangoDTO rango = rangos.get(0);

            return rango.getRangoInicial() + " - " + rango.getRangoFinal();

        } catch (NegocioException ex) {
            return "N/A";
        }
    }

    /**
     * Recorre la tabla de resultados, valida los datos y los envía a guardar
     */
    private void registrarResultados() {
    if (tabla.isEditing()) {
        tabla.getCellEditor().stopCellEditing();
    }

    if (!validarTablaResultados()) {
        return;
    }

    try {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valorResultado = modelo.getValueAt(i, 3);

            String textoResultado = valorResultado.toString().trim();
            double resultadoNumero = Double.parseDouble(textoResultado);

            ParametroDTO parametro = parametros.get(i);

            String observacion;
            Object valorObservacion = modelo.getValueAt(i, 4);

            if (valorObservacion != null && !valorObservacion.toString().trim().isEmpty()) {
                observacion = valorObservacion.toString().trim();
            } else {
                observacion = "Sin observación";
            }

            RegistrarResultadoDTO dto = new RegistrarResultadoDTO();
            dto.setIdPrueba(prueba.getIdPrueba());
            dto.setIdParametro(parametro.getIdParametro());
            dto.setResultadoObtenido(resultadoNumero);
            dto.setObservacion(observacion);

            resultadoBO.registrarResultado(dto);
        }

        JOptionPane.showMessageDialog(this, "Resultados registrados correctamente.");
        controlNavegacion.mostrarMenuPrincipal();
        dispose();

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Los resultados deben ser numéricos.");
    } catch (NegocioException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage());
    }
}

    /**
     * Junta los nombres del cliente para mostrarlos en el campo de texto
     * @param cliente El cliente
     * @return El nombre completo como String
     */
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

    /**
     * Regresa a la ventana de búsqueda de pacientes
     */
    private void regresarBusqueda() {
        BusquedaPacienteFORM pantalla = new BusquedaPacienteFORM(controlNavegacion);
        pantalla.setVisible(true);
        dispose();
    }

    /**
     * Revisa que todos los resultados sean números válidos antes de guardar
     * @return true si todo está bien, false si hay errores
     */
    private boolean validarTablaResultados() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay parámetros para registrar.");
            return false;
        }

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object valorResultado = modelo.getValueAt(i, 3);

            if (valorResultado == null || valorResultado.toString().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Captura el resultado del parámetro: " + modelo.getValueAt(i, 0));
                return false;
            }

            try {
                double resultado = Double.parseDouble(valorResultado.toString().trim());

                if (resultado < 0) {
                    JOptionPane.showMessageDialog(this, "El resultado no puede ser negativo: " + modelo.getValueAt(i, 0));
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El resultado debe ser numérico: " + modelo.getValueAt(i, 0));
                return false;
            }
        }

        return true;
    }
}
