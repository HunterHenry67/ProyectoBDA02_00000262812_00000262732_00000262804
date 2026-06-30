/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

/**
 * Ventana principal que muestra todas las opciones del sistema para que el
 * usuario elija qué hacer
 *
 * @author BALAMRUSH
 */
public class MenuPrincipalFORM extends javax.swing.JFrame {

    private ControlNavegacionForms controlNavegacion;

    public MenuPrincipalFORM(ControlNavegacionForms controlNavegacion) {
        initComponents();
        this.controlNavegacion = controlNavegacion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegistroSoliPrueba = new javax.swing.JButton();
        btnCatalogoAnalisis = new javax.swing.JButton();
        btnEmisionReporte = new javax.swing.JButton();
        btnIngresoResultado = new javax.swing.JButton();
        btnCargarDatos = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Laboratorio Clínico Salud Total");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Menú Principal");

        btnRegistroSoliPrueba.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistroSoliPrueba.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegistroSoliPrueba.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistroSoliPrueba.setText("Registro de Solicitud de Prueba");
        btnRegistroSoliPrueba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroSoliPruebaActionPerformed(evt);
            }
        });

        btnCatalogoAnalisis.setBackground(new java.awt.Color(0, 0, 0));
        btnCatalogoAnalisis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCatalogoAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        btnCatalogoAnalisis.setText("Catálogo de Análisis");
        btnCatalogoAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatalogoAnalisisActionPerformed(evt);
            }
        });

        btnEmisionReporte.setBackground(new java.awt.Color(0, 0, 0));
        btnEmisionReporte.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnEmisionReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnEmisionReporte.setText("Emisión de Reporte");
        btnEmisionReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmisionReporteActionPerformed(evt);
            }
        });

        btnIngresoResultado.setBackground(new java.awt.Color(0, 0, 0));
        btnIngresoResultado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnIngresoResultado.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresoResultado.setText("Ingreso de Resultado");
        btnIngresoResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoResultadoActionPerformed(evt);
            }
        });

        btnCargarDatos.setBackground(new java.awt.Color(0, 0, 0));
        btnCargarDatos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnCargarDatos.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarDatos.setText("Cargar Datos");
        btnCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDatosActionPerformed(evt);
            }
        });

        btnSalir1.setBackground(new java.awt.Color(0, 0, 0));
        btnSalir1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSalir1.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
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
                        .addGap(541, 541, 541))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(662, 662, 662))))
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(250, 250, 250)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistroSoliPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCatalogoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresoResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEmisionReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCargarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDatosActionPerformed
        cargarDatosIniciales();
    }//GEN-LAST:event_btnCargarDatosActionPerformed

    private void btnEmisionReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmisionReporteActionPerformed
        controlNavegacion.mostrarEmisionReporte();
        this.dispose();
    }//GEN-LAST:event_btnEmisionReporteActionPerformed

    private void btnCatalogoAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoAnalisisActionPerformed
        controlNavegacion.mostrarCatalogoAnalisis();
        this.dispose();
    }//GEN-LAST:event_btnCatalogoAnalisisActionPerformed

    private void btnRegistroSoliPruebaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroSoliPruebaActionPerformed
        // TODO add your handling code here:
        controlNavegacion.mostrarRegistroSolicitudPrueba();
        this.dispose();
    }//GEN-LAST:event_btnRegistroSoliPruebaActionPerformed

    private void btnIngresoResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresoResultadoActionPerformed
        controlNavegacion.mostrarBusquedaPaciente();
        this.dispose();
    }//GEN-LAST:event_btnIngresoResultadoActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void cargarDatosIniciales() {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Esta accion eliminara la base de datos y la recreará desde cero.\n"
                + "Se perderán todos los registros actuales. ¿Desea continuar?",
                "Confirmar Reinicio Total", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        String urlSinBD = "jdbc:mysql://localhost:3306/?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String urlConBD = "jdbc:mysql://localhost:3306/laboratoriosaludtotal?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "Andreiy2006#";

        try (java.sql.Connection con = java.sql.DriverManager.getConnection(urlSinBD, user, pass); java.sql.Statement stmt = con.createStatement()) {

            stmt.execute("DROP DATABASE IF EXISTS laboratoriosaludtotal");
            stmt.execute("CREATE DATABASE laboratoriosaludtotal");

        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error al crear la base de datos:\n" + ex.getMessage());
            ex.printStackTrace();
            return;
        }

        String[] sentencias = {
            "CREATE TABLE muestra ("
            + "idMuestra INT NOT NULL AUTO_INCREMENT, "
            + "nombre VARCHAR(100) NOT NULL, "
            + "PRIMARY KEY (idMuestra))",
            "CREATE TABLE cliente ("
            + "idCliente INT NOT NULL AUTO_INCREMENT, "
            + "nombres VARCHAR(50) NOT NULL, "
            + "apellidoPaterno VARCHAR(50) NOT NULL, "
            + "apellidoMaterno VARCHAR(50) NOT NULL, "
            + "sexo VARCHAR(9) NOT NULL, "
            + "fechaNacimiento DATETIME NOT NULL, "
            + "tipoSangre VARCHAR(10) NOT NULL, "
            + "PRIMARY KEY (idCliente))",
            "CREATE TABLE doctor ("
            + "idDoctor INT NOT NULL AUTO_INCREMENT, "
            + "nombres VARCHAR(50) NOT NULL, "
            + "apellidoPaterno VARCHAR(50) NOT NULL, "
            + "apellidoMaterno VARCHAR(50) NOT NULL, "
            + "sexo VARCHAR(9) NOT NULL, "
            + "PRIMARY KEY (idDoctor))",
            "CREATE TABLE analisis ("
            + "idAnalisis INT NOT NULL AUTO_INCREMENT, "
            + "nombre VARCHAR(30) NOT NULL, "
            + "nota VARCHAR(100) NOT NULL, "
            + "idMuestra INT NOT NULL, "
            + "PRIMARY KEY (idAnalisis), "
            + "FOREIGN KEY (idMuestra) REFERENCES muestra(idMuestra))",
            "CREATE TABLE prueba ("
            + "idPrueba INT NOT NULL AUTO_INCREMENT, "
            + "fechaHora DATETIME NOT NULL, "
            + "idCliente INT NOT NULL, "
            + "idDoctor INT NOT NULL, "
            + "PRIMARY KEY (idPrueba), "
            + "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente), "
            + "FOREIGN KEY (idDoctor) REFERENCES doctor(idDoctor))",
            "CREATE TABLE parametro ("
            + "idParametro INT NOT NULL AUTO_INCREMENT, "
            + "nombre VARCHAR(70) NOT NULL, "
            + "ordenReporte INT NOT NULL, "
            + "notaDescriptiva VARCHAR(100) NOT NULL, "
            + "unidadMedida VARCHAR(10) NOT NULL, "
            + "idAnalisis INT NOT NULL, "
            + "PRIMARY KEY (idParametro), "
            + "FOREIGN KEY (idAnalisis) REFERENCES analisis(idAnalisis))",
            "CREATE TABLE rango ("
            + "idRango INT NOT NULL AUTO_INCREMENT, "
            + "sexo VARCHAR(20) NOT NULL, "
            + "edadIncial INT NOT NULL, "
            + "edadFinal INT NOT NULL, "
            + "rangoIncial FLOAT NOT NULL, "
            + "rangoFinal FLOAT NOT NULL, "
            + "idParametro INT NOT NULL, "
            + "PRIMARY KEY (idRango), "
            + "FOREIGN KEY (idParametro) REFERENCES parametro(idParametro))",
            "CREATE TABLE resultado ("
            + "idResultado INT NOT NULL AUTO_INCREMENT, "
            + "resultadoObtenido DOUBLE NOT NULL, "
            + "observacion VARCHAR(100) NOT NULL, "
            + "idPrueba INT NOT NULL, "
            + "idParametro INT NOT NULL, "
            + "PRIMARY KEY (idResultado), "
            + "FOREIGN KEY (idPrueba) REFERENCES prueba(idPrueba), "
            + "FOREIGN KEY (idParametro) REFERENCES parametro(idParametro))",
            "INSERT INTO muestra (idMuestra, nombre) VALUES "
            + "(1,'Sangre'),(2,'Orina'),(3,'Heces fecales'),(4,'Saliva'),"
            + "(5,'Esputo (flema)'),(6,'Hisopado nasal'),(7,'Hisopado faríngeo'),"
            + "(8,'Biopsia de tejido'),(9,'Líquido cefalorraquídeo'),(10,'Semen'),"
            + "(11,'Cabello'),(12,'Uñas')",
            "INSERT INTO cliente (idCliente,nombres,apellidoPaterno,apellidoMaterno,sexo,fechaNacimiento,tipoSangre) VALUES "
            + "(1,'Juan Carlos','Ramírez','López','MASCULINO','1998-03-15','O+'),"
            + "(2,'María Fernanda','Gómez','Torres','FEMENINO','2001-07-22','A+'),"
            + "(3,'Luis Ángel','Martínez','Castro','MASCULINO','1995-11-08','B+'),"
            + "(4,'Ana Sofía','Hernández','Ruiz','FEMENINO','1999-01-30','AB+'),"
            + "(5,'Carlos Eduardo','Pérez','Mendoza','MASCULINO','1988-05-12','O-'),"
            + "(6,'Valeria','Sánchez','Morales','FEMENINO','2003-09-18','A-'),"
            + "(7,'José Miguel','Flores','Navarro','MASCULINO','1992-12-04','B-'),"
            + "(8,'Diana Carolina','Vargas','Reyes','FEMENINO','1997-06-25','AB-'),"
            + "(9,'Fernando','Cruz','Ortega','MASCULINO','1985-10-10','O+'),"
            + "(10,'Paola Andrea','Ríos','Delgado','FEMENINO','2000-04-03','A+')",
            "INSERT INTO doctor (idDoctor,nombres,apellidoPaterno,apellidoMaterno,sexo) VALUES "
            + "(1,'Roberto','Molina','García','MASCULINO'),"
            + "(2,'Laura','Castillo','Núñez','FEMENINO'),"
            + "(3,'Héctor','Aguilar','Ponce','MASCULINO'),"
            + "(4,'Patricia','Salazar','Vega','FEMENINO'),"
            + "(5,'Miguel','Domínguez','Luna','MASCULINO'),"
            + "(6,'Claudia','Espinoza','Soto','FEMENINO'),"
            + "(7,'Ricardo','Valenzuela','Mejía','MASCULINO'),"
            + "(8,'Gabriela','Camacho','Peña','FEMENINO'),"
            + "(9,'Alejandro','Fuentes','León','MASCULINO'),"
            + "(10,'Natalia','Acosta','Ibarra','FEMENINO')"
        };

        try (java.sql.Connection con = java.sql.DriverManager.getConnection(urlConBD, user, pass); java.sql.Statement stmt = con.createStatement()) {

            for (String sql : sentencias) {
                stmt.execute(sql);
            }

            javax.swing.JOptionPane.showMessageDialog(this,
                    "Base de datos recreada y datos iniciales cargados con éxito."
                    );

        } catch (java.sql.SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Error en la construcción:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarDatos;
    private javax.swing.JButton btnCatalogoAnalisis;
    private javax.swing.JButton btnEmisionReporte;
    private javax.swing.JButton btnIngresoResultado;
    private javax.swing.JButton btnRegistroSoliPrueba;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
