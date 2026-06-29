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
import DTO.PruebaDTO;
import DTO.RangoDTO;
import DTO.ResultadoDTO;
import Entidades.Cliente;
import Entidades.Parametro;
import Negocio.AnalisisBO;
import Negocio.ClienteBO;
import Negocio.NegocioException;
import Negocio.ParametroBO;
import Negocio.PruebaBO;
import Negocio.RangoBO;
import Negocio.ResultadoBO;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Pantalla para buscar pruebas registradas y generar reportes clínicos en PDF
 * @author Andre
 */
public class EmisionReporteFORM extends JFrame {

    private ControlNavegacionForms controlNavegacion;
    private JasperReport reporteCompilado;
    private PruebaBO pruebaBO;
    private ResultadoBO resultadoBO;
    private ParametroBO parametroBO;
    private RangoBO rangoBO;
    private AnalisisBO analisisBO;
    private ClienteBO clienteBO;
    private int paginaActual = 1;
    private final int registrosPorPagina = 5;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JLabel lblPagina;
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
        cargarReporte();
    }

    /**
     * Crea los objetos de lógica de negocio para acceder a datos
     */
    private void inicializarBOs() {
        IConexionBD conexion = new ConexionBD();

        pruebaBO = new PruebaBO();

        resultadoBO = new ResultadoBO(
                new ResultadoDAO(conexion),
                new PruebaDAO(),
                new ParametroDAO()
        );

        parametroBO = new ParametroBO(
                new ParametroDAO(),
                new AnalisisDAO()
        );

        rangoBO = new RangoBO();
        analisisBO = new AnalisisBO();
        clienteBO = new ClienteBO();
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
        btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(170, 590, 120, 35);
        btnAnterior.addActionListener(e -> {
            if (paginaActual > 1) {
                paginaActual--;
                llenarTabla(pruebasMostradas);
            }
        });
        add(btnAnterior);

        lblPagina = new JLabel("Página 1/1");
        lblPagina.setBounds(310, 590, 120, 35);
        add(lblPagina);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(430, 590, 120, 35);
        btnSiguiente.addActionListener(e -> {
            int totalPaginas = obtenerTotalPaginas(pruebasMostradas.size());
            if (paginaActual < totalPaginas) {
                paginaActual++;
                llenarTabla(pruebasMostradas);
            }
        });
        add(btnSiguiente);
    }

    /**
     * Carga todas las pruebas desde la base de datos y las pone en la tabla
     */
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

    /**
     * Filtra la lista de pruebas basándose en el texto de búsqueda
     */
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
        paginaActual = 1;
        llenarTabla(pruebasMostradas);
        llenarTabla(pruebasMostradas);
    }

    
    private void llenarTabla(List<PruebaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        int totalPaginas = obtenerTotalPaginas(lista.size());

        if (paginaActual > totalPaginas) {
            paginaActual = totalPaginas;
        }

        int inicio = (paginaActual - 1) * registrosPorPagina;
        int fin = Math.min(inicio + registrosPorPagina, lista.size());

        for (int i = inicio; i < fin; i++) {
            PruebaDTO prueba = lista.get(i);

            modelo.addRow(new Object[]{
                prueba.getIdPrueba(),
                prueba.getNombreCliente(),
                obtenerAnalisisTabla(prueba.getIdPrueba())
            });
        }

        lblPagina.setText("Página " + paginaActual + "/" + totalPaginas);
        btnAnterior.setEnabled(paginaActual > 1);
        btnSiguiente.setEnabled(paginaActual < totalPaginas);
    }

    
    private int obtenerTotalPaginas(int totalRegistros) {
        if (totalRegistros == 0) {
            return 1;
        }

        return (int) Math.ceil((double) totalRegistros / registrosPorPagina);
    }

    
    private String obtenerAnalisisTabla(Integer idPrueba) {
        try {
            return analisisBO.obtenerNombreAnalisisPorPrueba(idPrueba);
        } catch (Exception e) {
            return "N/A";
        }
    }

    /**
     * Carga el archivo .jasper desde los recursos del proyecto
     */
    private void cargarReporte() {
        try {
            InputStream reporte = getClass().getResourceAsStream("/reportes.jasper");

            if (reporte == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el reporte en:\n/reportes.jasper");
                return;
            }

            reporteCompilado = (JasperReport) JRLoader.loadObject(reporte);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el reporte:\n" + e.getMessage());
        }
    }

    
    private int calcularEdad(LocalDateTime fechaNacimiento) {
        return java.time.Period.between(
                fechaNacimiento.toLocalDate(),
                java.time.LocalDate.now()
        ).getYears();
    }

    /**
     * Recopila los datos y muestra el reporte clínico en pantalla
     */
    private void imprimirReporte() {
        try {
            if (reporteCompilado == null) {
                JOptionPane.showMessageDialog(this, "El reporte no está cargado correctamente.");
                return;
            }

            int filaVista = tabla.getSelectedRow();

            if (filaVista == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una prueba primero");
                return;
            }

            int filaModelo = tabla.convertRowIndexToModel(filaVista);

            Integer idPrueba = Integer.parseInt(
                    tabla.getModel().getValueAt(filaModelo, 0).toString()
            );

            PruebaDTO pruebaSeleccionada = null;

            for (PruebaDTO prueba : pruebasMostradas) {
                if (prueba.getIdPrueba().equals(idPrueba)) {
                    pruebaSeleccionada = prueba;
                    break;
                }
            }

            if (pruebaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la prueba seleccionada");
                return;
            }

            Map<String, Object> parametros = crearParametrosReporte(pruebaSeleccionada);

            Collection<Map<String, ?>> datos = crearDatosReporte(idPrueba);

            if (datos == null || datos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La prueba seleccionada no tiene resultados registrados");
                return;
            }

            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(datos);

            JasperPrint print = JasperFillManager.fillReport(
                    reporteCompilado,
                    parametros,
                    dataSource
            );

            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            e.printStackTrace();

            Throwable causa = e;

            while (causa.getCause() != null) {
                causa = causa.getCause();
            }

            JOptionPane.showMessageDialog(this, causa.toString());
        }
    }

    /*
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
    }*/
    
    
    private Map<String, Object> crearParametrosReporte(PruebaDTO prueba) {
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("folio", prueba.getIdPrueba());
        parametros.put("nombre", prueba.getNombreCliente() != null ? prueba.getNombreCliente() : "N/A");

        try {
            if (prueba.getIdCliente() != null) {
                ClienteDTO cliente = clienteBO.buscarClienteId(prueba.getIdCliente());

                if (cliente != null) {
                    if (cliente.getFechaNacimiento() != null) {
                        parametros.put("edad", calcularEdad(cliente.getFechaNacimiento()) + " años");
                    } else {
                        parametros.put("edad", "N/A");
                    }

                    if (cliente.getSexo() != null) {
                        parametros.put("sexo", cliente.getSexo().toString());
                    } else {
                        parametros.put("sexo", "N/A");
                    }
                } else {
                    parametros.put("edad", "N/A");
                    parametros.put("sexo", "N/A");
                }
            } else {
                parametros.put("edad", "N/A");
                parametros.put("sexo", "N/A");
            }
        } catch (Exception e) {
            parametros.put("edad", "N/A");
            parametros.put("sexo", "N/A");
        }

        if (prueba.getFechaHora() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            parametros.put("fecha", prueba.getFechaHora().format(formatter));
        } else {
            parametros.put("fecha", "N/A");
        }

        try {
            parametros.put("analisis", analisisBO.obtenerNombreAnalisisPorPrueba(prueba.getIdPrueba()));
        } catch (Exception e) {
            parametros.put("analisis", "N/A");
        }

        parametros.put("medico", prueba.getNombreDoctor() != null ? prueba.getNombreDoctor() : "N/A");

        return parametros;
    }

    
    private Collection<Map<String, ?>> crearDatosReporte(Integer idPrueba) throws NegocioException {

        List<Map<String, ?>> datos = new ArrayList<>();

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

    /**
     * Cierra esta pantalla y vuelve al menú principal
     */
    private void regresar() {
        controlNavegacion.mostrarMenuPrincipal();
        dispose();
    }

}
