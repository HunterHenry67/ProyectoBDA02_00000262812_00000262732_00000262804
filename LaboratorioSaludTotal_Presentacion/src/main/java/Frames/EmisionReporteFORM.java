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
import DTO.PruebaDTO;
import DTO.RangoDTO;
import DTO.ResultadoDTO;
import Entidades.Parametro;
import Negocio.NegocioException;
import Negocio.ParametroBO;
import Negocio.PruebaBO;
import Negocio.RangoBO;
import Negocio.ResultadoBO;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Andre
 */
public class EmisionReporteFORM extends JFrame {

    private ControlNavegacionForms controlNavegacion;

    private PruebaBO pruebaBO;
    private ResultadoBO resultadoBO;
    private ParametroBO parametroBO;
    private RangoBO rangoBO;

    private List<PruebaDTO> pruebas = new ArrayList<>();
    private List<PruebaDTO> pruebasMostradas = new ArrayList<>();

    private JTextField txtBuscar;
    private JComboBox<String> comboFiltros;
    private JTable tabla;

    public EmisionReporteFORM(ControlNavegacionForms controlNavegacion) {
        this.controlNavegacion = controlNavegacion;

        inicializarBOs();
        initComponents();
        cargarPruebas();
    }

    private void inicializarBOs() {
        IConexionBD conexion = new ConexionBD();

        pruebaBO = new PruebaBO();

        resultadoBO = new ResultadoBO(
                new ResultadoDAO(conexion),
                new PruebaDAO(),
                new ParametroDAO(conexion)
        );

        parametroBO = new ParametroBO(
                new ParametroDAO(conexion),
                new AnalisisDAO()
        );

        rangoBO = new RangoBO();
    }

    private void initComponents() {
        setTitle("Emisión de Reporte");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Laboratorio Clínico Salud Total");
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitulo.setBounds(390, 70, 450, 40);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Emisión de Reporte");
        lblSubtitulo.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblSubtitulo.setBounds(515, 125, 250, 30);
        add(lblSubtitulo);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(170, 300, 120, 45);
        btnBuscar.addActionListener(e -> filtrarPruebas());
        add(btnBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(310, 300, 280, 45);
        add(txtBuscar);

        JLabel lblFiltro = new JLabel("Filtro Búsqueda");
        lblFiltro.setFont(new Font("Dialog", Font.BOLD, 12));
        lblFiltro.setBounds(630, 275, 160, 25);
        add(lblFiltro);

        comboFiltros = new JComboBox<>();
        comboFiltros.setModel(new DefaultComboBoxModel<>(new String[]{"Folio", "Paciente", "Analisis"}));
        comboFiltros.setBounds(630, 300, 140, 45);
        add(comboFiltros);

        JButton btnImprimir = new JButton("Imprimir Reporte");
        btnImprimir.setBounds(820, 300, 180, 45);
        btnImprimir.addActionListener(e -> imprimirReporte());
        add(btnImprimir);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(1030, 590, 120, 45);
        btnRegresar.setBackground(Color.BLACK);
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.addActionListener(e -> regresar());
        add(btnRegresar);

        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Folio", "Paciente", "Análisis"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false};

            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(170, 360, 830, 220);
        add(scroll);
    }

    private void cargarPruebas() {
        try {
            pruebas.clear();
            pruebasMostradas.clear();

            List lista = pruebaBO.consultarTodasLasPruebas();

            for (Object obj : lista) {
                PruebaDTO prueba = (PruebaDTO) obj;
                pruebas.add(prueba);
            }

            pruebasMostradas.addAll(pruebas);
            llenarTabla(pruebasMostradas);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void filtrarPruebas() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String filtro = comboFiltros.getSelectedItem().toString();

        pruebasMostradas.clear();

        for (PruebaDTO prueba : pruebas) {
            String folio = "";
            String paciente = "";

            if (prueba.getIdPrueba() != null) {
                folio = prueba.getIdPrueba().toString();
            }

            if (prueba.getNombreCliente() != null) {
                paciente = prueba.getNombreCliente().toLowerCase();
            }

            boolean mostrar = false;

            if (texto.isEmpty()) {
                mostrar = true;
            }

            if (filtro.equals("Folio")) {
                if (folio.contains(texto)) {
                    mostrar = true;
                }
            }

            if (filtro.equals("Paciente")) {
                if (paciente.contains(texto)) {
                    mostrar = true;
                }
            }

            if (mostrar) {
                pruebasMostradas.add(prueba);
            }
        }

        llenarTabla(pruebasMostradas);
    }

    private void llenarTabla(List<PruebaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (PruebaDTO prueba : lista) {
            modelo.addRow(new Object[]{
                prueba.getIdPrueba(),
                prueba.getNombreCliente(),
                "N/A"
            });
        }
    }

    private void imprimirReporte() {
        try {
            int fila = tabla.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una prueba.");
                return;
            }

            PruebaDTO prueba = pruebasMostradas.get(fila);

            InputStream archivo = getClass().getResourceAsStream("/reportes.jrxml");

            JasperReport reporte = JasperCompileManager.compileReport(archivo);

            Collection datos = crearDatosReporte(prueba.getIdPrueba());

            JasperPrint print = JasperFillManager.fillReport(
                    reporte,
                    crearParametrosReporte(prueba),
                    new JRMapCollectionDataSource(datos)
            );

            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private Map<String, Object> crearParametrosReporte(PruebaDTO prueba) {
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("folio", prueba.getIdPrueba());

        if (prueba.getNombreCliente() != null) {
            parametros.put("nombre", prueba.getNombreCliente());
        } else {
            parametros.put("nombre", "N/A");
        }

        parametros.put("edad", "N/A");
        parametros.put("sexo", "N/A");

        if (prueba.getFechaHora() != null) {
            parametros.put("fecha", prueba.getFechaHora().toString());
        } else {
            parametros.put("fecha", "N/A");
        }

        parametros.put("analisis", "N/A");

        if (prueba.getNombreDoctor() != null) {
            parametros.put("medico", prueba.getNombreDoctor());
        } else {
            parametros.put("medico", "N/A");
        }

        return parametros;
    }

    private Collection<Map<String, Object>> crearDatosReporte(Integer idPrueba) throws NegocioException {
        List<Map<String, Object>> datos = new ArrayList<>();

        List resultados = resultadoBO.consultarTablaPorPrueba(idPrueba);

        for (Object obj : resultados) {
            ResultadoDTO resultado = (ResultadoDTO) obj;

            Parametro parametro = parametroBO.consultarParametroPorID(resultado.getIdParametro());

            Map<String, Object> fila = new HashMap<>();

            fila.put("parametro", parametro.getNombre());
            fila.put("unidad", parametro.getUnidadMedida());
            fila.put("rango", obtenerRangoNormal(resultado.getIdParametro()));
            fila.put("resultado", resultado.getResultadoObtenido());
            fila.put("observacion", resultado.getObservacion());

            datos.add(fila);
        }

        return datos;
    }

    private String obtenerRangoNormal(Integer idParametro) {
        try {
            List rangos = rangoBO.buscarRangosPorParametro(idParametro);

            if (rangos == null || rangos.isEmpty()) {
                return "N/A";
            }

            RangoDTO rango = (RangoDTO) rangos.get(0);

            return rango.getRangoInicial() + " - " + rango.getRangoFinal();

        } catch (Exception ex) {
            return "N/A";
        }
    }

    private void regresar() {
        controlNavegacion.mostrarMenuPrincipal();
        dispose();
    }
}