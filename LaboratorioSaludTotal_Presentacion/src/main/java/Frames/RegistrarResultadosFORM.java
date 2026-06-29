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
 *
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

    private void cargarDatos() {
        txtPaciente.setText(nombreCompletoCliente(cliente));
        txtAnalisis.setText("Folio: " + prueba.getIdPrueba());
    }

    private void cargarParametros() {
        try {
            parametros = parametroBO.listarTodos();

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
    
    private void registrarResultados() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object valorResultado = modelo.getValueAt(i, 3);

                if (valorResultado != null) {
                    String textoResultado = valorResultado.toString().trim();

                    if (!textoResultado.isEmpty()) {
                        double resultadoNumero = Double.parseDouble(textoResultado);

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
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los resultados deben ser numéricos.");
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
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

    private void regresarBusqueda() {
        BusquedaPacienteFORM pantalla = new BusquedaPacienteFORM(controlNavegacion);
        pantalla.setVisible(true);
        dispose();
    }
}