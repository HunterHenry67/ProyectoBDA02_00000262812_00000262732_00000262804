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
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class EmisionReporteFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;

    private PruebaBO pruebaBO;
    private ResultadoBO resultadoBO;
    private ParametroBO parametroBO;
    private RangoBO rangoBO;

    private List<PruebaDTO> pruebas;
    private List<PruebaDTO> pruebasMostradas;

    public EmisionReporteFORM() {
        initComponents();
    }

    public EmisionReporteFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();

        this.controlNavegacion = controlNavegacion;
        this.pruebas = new ArrayList<>();
        this.pruebasMostradas = new ArrayList<>();

        inicializarBOs();
        cargarPruebas();

        setExtendedState(MAXIMIZED_BOTH);
    }

    private void inicializarBOs() {
        IConexionBD conexion = new ConexionBD();

        this.pruebaBO = new PruebaBO();

        this.resultadoBO = new ResultadoBO(
                new ResultadoDAO(conexion),
                new PruebaDAO(),
                new ParametroDAO(conexion)
        );

        this.parametroBO = new ParametroBO(
                new ParametroDAO(conexion),
                new AnalisisDAO()
        );

        this.rangoBO = new RangoBO();
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
            mostrarError("Error al cargar pruebas: " + ex.getMessage());
        }
    }

    private void filtrarPruebas() {
        String texto = txtFieldBuscar.getText().trim().toLowerCase();
        String filtro = comboBoxFiltros.getSelectedItem().toString();

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

            if (filtro.equals("Analisis")) {
                mostrar = false;
            }

            if (mostrar) {
                pruebasMostradas.add(prueba);
            }
        }

        llenarTabla(pruebasMostradas);
    }

    private void llenarTabla(List<PruebaDTO> lista) {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
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
        int fila = jTable1.getSelectedRow();

        if (fila == -1) {
            mostrarAdvertencia("Selecciona una prueba para imprimir el reporte.");
            return;
        }

        PruebaDTO pruebaSeleccionada = pruebasMostradas.get(fila);

        try {
            InputStream archivoReporte = getClass().getResourceAsStream("/reportes.jrxml");

            if (archivoReporte == null) {
                mostrarError("No se encontró reportes.jrxml en src/main/resources.");
                return;
            }

            JasperReport reporteCompilado = JasperCompileManager.compileReport(archivoReporte);

            Map<String, Object> parametrosReporte = crearParametrosReporte(pruebaSeleccionada);
            Collection<Map<String, ?>> datosReporte = crearDatosReporte(pruebaSeleccionada.getIdPrueba());

            if (datosReporte.isEmpty()) {
                mostrarAdvertencia("La prueba seleccionada no tiene resultados registrados.");
                return;
            }

            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(datosReporte);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    reporteCompilado,
                    parametrosReporte,
                    dataSource
            );

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception ex) {
            mostrarError("Error al generar reporte: " + ex.getMessage());
        }
    }

    private Map<String,Object> crearParametrosReporte(PruebaDTO prueba){

    Map<String,Object> parametros = new HashMap<>();

    parametros.put("folio", prueba.getIdPrueba());
    parametros.put("nombre", prueba.getNombreCliente());
    parametros.put("edad", "");
    parametros.put("sexo", "");
    parametros.put("fecha", prueba.getFechaHora());
    parametros.put("analisis", prueba.get());
    parametros.put("medico", prueba.getNombreDoctor());

    return parametros;
}

    private Collection<Map<String, ?>> crearDatosReporte(Integer idPrueba) throws NegocioException {
        List<Map<String, ?>> datos = new ArrayList<>();

        List resultados = resultadoBO.consultarTablaPorPrueba(idPrueba);

        for (Object obj : resultados) {
            ResultadoDTO resultado = (ResultadoDTO) obj;

            Parametro parametro = parametroBO.consultarParametroPorID(resultado.getIdParametro());

            String nombreParametro = "N/A";
            String unidad = "N/A";
            String rangoNormal = obtenerRangoNormal(resultado.getIdParametro());
            String observacion = "";

            if (parametro != null) {
                if (parametro.getNombre() != null) {
                    nombreParametro = parametro.getNombre();
                }

                if (parametro.getUnidadMedida() != null) {
                    unidad = parametro.getUnidadMedida();
                }
            }

            if (resultado.getObservacion() != null) {
                observacion = resultado.getObservacion();
            }

            Map<String, Object> fila = new HashMap<>();

            fila.put("parametro", nombreParametro);
            fila.put("unidad", unidad);
            fila.put("rangoNormal", rangoNormal);
            fila.put("resultado", resultado.getResultadoObtenido());
            fila.put("observacion", observacion);

            fila.put("PARAMETRO", nombreParametro);
            fila.put("UNIDAD", unidad);
            fila.put("RANGO_NORMAL", rangoNormal);
            fila.put("RESULTADO", resultado.getResultadoObtenido());
            fila.put("OBSERVACION", observacion);

            datos.add(fila);
        }

        return datos;
    }

    private String obtenerRangoNormal(Integer idParametro) {
        try {
            List rangos = rangoBO.buscarRangosPorParametro(idParametro);

            if (rangos == null) {
                return "N/A";
            }

            if (rangos.isEmpty()) {
                return "N/A";
            }

            RangoDTO rango = (RangoDTO) rangos.get(0);

            if (rango.getRangoInicial() == null) {
                return "N/A";
            }

            if (rango.getRangoFinal() == null) {
                return "N/A";
            }

            return rango.getRangoInicial() + " - " + rango.getRangoFinal();

        } catch (Exception ex) {
            return "N/A";
        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        txtFieldBuscar = new javax.swing.JTextField();
        comboBoxFiltros = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Emision Reporte");

        btnBuscar1.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        comboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Folio", "Paciente", "Analisis", " " }));

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Paciente", "Analisis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnImprimir.setBackground(new java.awt.Color(0, 0, 0));
        btnImprimir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("Imprimir Reporte");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtro Búsqueda");

        btnRegresar.setBackground(new java.awt.Color(0, 0, 0));
        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(755, 755, 755))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(851, 851, 851))))
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnImprimir)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(371, 371, 371)
                .addComponent(jLabel6)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFieldBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 36, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        filtrarPruebas();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimirReporte();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        controlNavegacion.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxFiltros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFieldBuscar;
    // End of variables declaration//GEN-END:variables

}
