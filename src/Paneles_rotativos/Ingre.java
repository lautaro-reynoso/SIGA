/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles_rotativos;

import Main.Controlador;
import Main.Main;
import static Main.Main.controlador;
import Main.Modelo;
import Paneles_principales.Login;
import Paneles_principales.Principal;

import com.formdev.flatlaf.json.ParseException;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Clases_tiketera.ConectorPluginV3;

/**
 *
 * @author ig:lauti_reynosoo
 */
public class Ingre extends javax.swing.JPanel {

    int filas = 0;
    Modelo modelo = new Modelo();
    public static Calendar calendario = Calendar.getInstance();

    public Ingre() {
        initComponents();
        tarifa1.setEnabled(false);
        fecha_ingreso_p.setEnabled(true);
        fecha_egreso_p.setEnabled(true);
        fecha_ingreso_p1.setEnabled(true);
        fecha_egreso_p1.setEnabled(true);
        fecha_ingreso.setEnabled(true);
        fecha_egreso.setEnabled(true);

    }

    Controlador controlador = new Controlador();

    public void setearnullparticular() {
        documento_p.setText("");
        nombre_p.setText("");

        Parsela_p.setText("Parsela");

    }

    public void setearnullaportante() {
        documento_a.setText("");
        nombre_a.setText("");

        Parsela_a.setText("Parsela");
        cod_aportante.setText("");
        apellido_a.setText("");

    }

    public void setearnullalumno() {
        documento_e.setText("");
        nombre_e.setText("");

        Parcela.setText("Parsela");
        apellido_e.setText("");
        carrera_e.setText("");
        facultad_e.setText("");

    }

    public void BuscarEstudiante() throws SQLException {
        ResultSet res;

        int c = controlador.Controldnirepetidoingreso(Documento.getText());
        if (c == 0) {
            res = controlador.BuscarAlumno(Documento.getText());
            if (res.next() == true) {
                nombre_e.setText(res.getString("nombre"));
                apellido_e.setText(res.getString("apellido"));
                documento_e.setText(res.getString("documento"));
                carrera_e.setText(res.getString("carrera"));
                facultad_e.setText(res.getString("facultad"));
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se encontro el documento solicitado.\n Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void RegistrarIngresoParticular() throws java.text.ParseException, java.lang.NullPointerException, SQLException {

        try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
            int c;
            if (fecha_egreso_p.getDate() != null && fecha_ingreso_p.getDate() != null) {

                c = controlador.IngresoParticular(documento_p.getText(), nombre_p.getText(), "3", calc_fecha(fecha_ingreso_p), calc_fecha(fecha_egreso_p), Parsela_p.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                    String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
                    String segundos = String.valueOf(calendario.get(Calendar.SECOND));

                    String hora_actual = hora + ":" + minutos;
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo particular acampante", Main.DiaActual, hora_actual);
                    modelo.insertardinerocaja(importe);
                    setearnullparticular();
                    javax.swing.JOptionPane.showMessageDialog(this, "Registro exitoso", "Exitoso", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe de ingresar una fecha de ingreso y egreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.lang.NullPointerException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos de ingreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public void RegistrarIngresoAportante() throws java.text.ParseException, SQLException {
        try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
            int c;
            if (fecha_egreso_p1.getDate() != null && fecha_ingreso_p1.getDate() != null) {

                c = controlador.IngresoParticular(documento_a.getText(), nombre_a.getText(), "2", calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), Parsela_a.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                    String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
                    

                    String hora_actual = hora + ":" + minutos;
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo aportante acampante", Main.DiaActual, hora_actual);

                    modelo.insertardinerocaja(importe);
                    Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");
                    System.out.println(result);
                    if (result == 0) {
                        
                        if (!pasar_dia1.isSelected()) {
                            
                            imprimirtiketacampante(calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), "Aportante", importe);
                        } else {

                            importe = Main.tarifa_dia_aportantes;
                            imprimirtiketdia("Aportante", importe);
                        }
                    }
                }

            } else {

                setearnullaportante();
                javax.swing.JOptionPane.showMessageDialog(this, "Debe de ingresar una fecha de ingreso y egreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.lang.NullPointerException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos de ingreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void BuscarAportante() throws SQLException {

        ResultSet res;
        res = controlador.BuscarAportante(Documento_a.getText());
        if (res.next() == true) {
            nombre_a.setText(res.getString("nombre"));
            documento_a.setText(res.getString("doc"));
            cod_aportante.setText(res.getString("cod_aportante"));
            apellido_a.setText(res.getString("apellido"));

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontro el documento solicitado.\n Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void TablaParselas() throws SQLException {
        int f = 0;
        ResultSet res;
        res = controlador.MostraParselas();
        //   System.out.println(res.getString("documento"));
        while (res.next()) {
            f++;
            String documento = res.getString("documento");
            String parsela = res.getString("parsela");
            String quincho = String.valueOf(res.getInt("quincho"));
            String fecha_egreso = res.getString("fecha_egreso");
            String tab[] = {documento, parsela, quincho, fecha_egreso};

        }

    }

    public void RegistrarIngreso() throws java.text.ParseException, SQLException {
        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);
        int c;
        //   System.out.println("fecha:" + fecha_egreso);

        if (fecha_egreso.getDate() != null && fecha_ingreso.getDate() != null) {

            c = controlador.IngresoParticular(documento_e.getText(), nombre_e.getText(), "1", calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), Parcela.getText(), importe);

            if (c != 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
                String segundos = String.valueOf(calendario.get(Calendar.SECOND));

                String hora_actual = hora + ":" + minutos;
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo alumno acampante", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(importe);
                setearnullalumno();
                Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");
                    System.out.println(result);
                    if (result == 0) {
                        
                        if (!pasar_dia.isSelected()) {
                            
                            imprimirtiketacampante(calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), "Alumno", importe);
                        } else {

                            importe = Main.tarifa_dia_alumnos;
                            imprimirtiketdia("Alumno", importe);
                        }
                    }
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        vehiculos = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        Documento_a = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Buscar_a = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        documento_a = new javax.swing.JTextField();
        cod_aportante = new javax.swing.JTextField();
        apellido_a = new javax.swing.JTextField();
        nombre_a = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        fecha_ingreso_p1 = new com.toedter.calendar.JDateChooser();
        fecha_egreso_p1 = new com.toedter.calendar.JDateChooser();
        Obtener2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        tarifa2 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        Boton_ingreso_a = new javax.swing.JLabel();
        Parsela_a = new javax.swing.JTextField();
        pasar_dia1 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        fecha_ingreso = new com.toedter.calendar.JDateChooser();
        fecha_egreso = new com.toedter.calendar.JDateChooser();
        Obtener = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tarifa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Boton_ingreso = new javax.swing.JLabel();
        Parcela = new javax.swing.JTextField();
        pasar_dia = new javax.swing.JCheckBox();
        jLabel36 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        documento_e = new javax.swing.JTextField();
        apellido_e = new javax.swing.JTextField();
        nombre_e = new javax.swing.JTextField();
        carrera_e = new javax.swing.JTextField();
        facultad_e = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Documento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Buscar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        documento_p = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        nombre_p = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        fecha_ingreso_p = new com.toedter.calendar.JDateChooser();
        fecha_egreso_p = new com.toedter.calendar.JDateChooser();
        Obtener1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        tarifa1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        Boton_ingreso_p = new javax.swing.JLabel();
        Parsela_p = new javax.swing.JTextField();
        pasar_dia2 = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        dni_buscado = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_egreso = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_vehiculo = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        marca = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        patente = new javax.swing.JTextField();
        ingreso_vehiculo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        j10 = new javax.swing.JLabel();
        j12 = new javax.swing.JLabel();
        j5 = new javax.swing.JLabel();
        j27 = new javax.swing.JLabel();
        j7 = new javax.swing.JLabel();
        j29 = new javax.swing.JLabel();
        j20 = new javax.swing.JLabel();
        j3 = new javax.swing.JLabel();
        j31 = new javax.swing.JLabel();
        j40 = new javax.swing.JLabel();
        j49 = new javax.swing.JLabel();
        j45 = new javax.swing.JLabel();
        j50 = new javax.swing.JLabel();
        j6 = new javax.swing.JLabel();
        j15 = new javax.swing.JLabel();
        j2 = new javax.swing.JLabel();
        j25 = new javax.swing.JLabel();
        j33 = new javax.swing.JLabel();
        j37 = new javax.swing.JLabel();
        j36 = new javax.swing.JLabel();
        j48 = new javax.swing.JLabel();
        j39 = new javax.swing.JLabel();
        j38 = new javax.swing.JLabel();
        j34 = new javax.swing.JLabel();
        j43 = new javax.swing.JLabel();
        j30 = new javax.swing.JLabel();
        j42 = new javax.swing.JLabel();
        j24 = new javax.swing.JLabel();
        j23 = new javax.swing.JLabel();
        j21 = new javax.swing.JLabel();
        j19 = new javax.swing.JLabel();
        j18 = new javax.swing.JLabel();
        j4 = new javax.swing.JLabel();
        j16 = new javax.swing.JLabel();
        j17 = new javax.swing.JLabel();
        j14 = new javax.swing.JLabel();
        j13 = new javax.swing.JLabel();
        j8 = new javax.swing.JLabel();
        j9 = new javax.swing.JLabel();
        j11 = new javax.swing.JLabel();
        j46 = new javax.swing.JLabel();
        j35 = new javax.swing.JLabel();
        j41 = new javax.swing.JLabel();
        j32 = new javax.swing.JLabel();
        j28 = new javax.swing.JLabel();
        j26 = new javax.swing.JLabel();
        j1 = new javax.swing.JLabel();
        j22 = new javax.swing.JLabel();
        j44 = new javax.swing.JLabel();
        j47 = new javax.swing.JLabel();
        j51 = new javax.swing.JLabel();
        j52 = new javax.swing.JLabel();
        j53 = new javax.swing.JLabel();
        j54 = new javax.swing.JLabel();
        j55 = new javax.swing.JLabel();
        j56 = new javax.swing.JLabel();
        j57 = new javax.swing.JLabel();
        j58 = new javax.swing.JLabel();
        j59 = new javax.swing.JLabel();
        j60 = new javax.swing.JLabel();
        j61 = new javax.swing.JLabel();
        j62 = new javax.swing.JLabel();
        j63 = new javax.swing.JLabel();
        j64 = new javax.swing.JLabel();
        j65 = new javax.swing.JLabel();
        j66 = new javax.swing.JLabel();
        j67 = new javax.swing.JLabel();
        j68 = new javax.swing.JLabel();
        j69 = new javax.swing.JLabel();
        j70 = new javax.swing.JLabel();
        j71 = new javax.swing.JLabel();
        j72 = new javax.swing.JLabel();
        j73 = new javax.swing.JLabel();
        j74 = new javax.swing.JLabel();
        j75 = new javax.swing.JLabel();
        j76 = new javax.swing.JLabel();
        j77 = new javax.swing.JLabel();
        j78 = new javax.swing.JLabel();
        j79 = new javax.swing.JLabel();
        j80 = new javax.swing.JLabel();
        j81 = new javax.swing.JLabel();
        j82 = new javax.swing.JLabel();
        j83 = new javax.swing.JLabel();
        j84 = new javax.swing.JLabel();
        j85 = new javax.swing.JLabel();
        j86 = new javax.swing.JLabel();
        j87 = new javax.swing.JLabel();
        j88 = new javax.swing.JLabel();
        j89 = new javax.swing.JLabel();
        j90 = new javax.swing.JLabel();
        j91 = new javax.swing.JLabel();
        j92 = new javax.swing.JLabel();
        j93 = new javax.swing.JLabel();
        j94 = new javax.swing.JLabel();
        j95 = new javax.swing.JLabel();
        j96 = new javax.swing.JLabel();
        j97 = new javax.swing.JLabel();
        j98 = new javax.swing.JLabel();
        j99 = new javax.swing.JLabel();
        j100 = new javax.swing.JLabel();
        j101 = new javax.swing.JLabel();
        j102 = new javax.swing.JLabel();
        j103 = new javax.swing.JLabel();
        j104 = new javax.swing.JLabel();
        j105 = new javax.swing.JLabel();
        j106 = new javax.swing.JLabel();
        j107 = new javax.swing.JLabel();
        j108 = new javax.swing.JLabel();
        j109 = new javax.swing.JLabel();
        j110 = new javax.swing.JLabel();
        j111 = new javax.swing.JLabel();
        j112 = new javax.swing.JLabel();
        j113 = new javax.swing.JLabel();
        j114 = new javax.swing.JLabel();
        j115 = new javax.swing.JLabel();
        j116 = new javax.swing.JLabel();
        j117 = new javax.swing.JLabel();
        j118 = new javax.swing.JLabel();
        j119 = new javax.swing.JLabel();
        j120 = new javax.swing.JLabel();
        j121 = new javax.swing.JLabel();
        j122 = new javax.swing.JLabel();
        j123 = new javax.swing.JLabel();
        j124 = new javax.swing.JLabel();
        j125 = new javax.swing.JLabel();
        j126 = new javax.swing.JLabel();
        j127 = new javax.swing.JLabel();
        j128 = new javax.swing.JLabel();
        boton_v = new javax.swing.JLabel();
        boton_r = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        vehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                vehiculosMousePressed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso Aportante"));

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda de Aportante"));

        Documento_a.setForeground(new java.awt.Color(0, 0, 0));
        Documento_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Documento_a.setText(null);
        Documento_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Documento_aActionPerformed(evt);
            }
        });
        Documento_a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Documento_aKeyPressed(evt);
            }
        });

        jLabel3.setText("DOCUMENTO DE APORTANTE");

        Buscar_a.setBackground(new java.awt.Color(255, 255, 255));
        Buscar_a.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_search_black_24dp.png"))); // NOI18N
        Buscar_a.setText("BUSCAR");
        Buscar_a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Buscar_a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscar_a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Buscar_aMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Buscar_a, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Documento_a, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Documento_a, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar_a)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion del Aportante"));

        jLabel4.setText("Nombre:");

        jLabel15.setText("Apellido:");

        jLabel16.setText("Cod. aportante:");

        jLabel20.setText("Documento:");

        documento_a.setForeground(new java.awt.Color(0, 0, 0));
        documento_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_a.setText(null);
        documento_a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                documento_aKeyPressed(evt);
            }
        });

        cod_aportante.setForeground(new java.awt.Color(0, 0, 0));
        cod_aportante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cod_aportante.setText(null);

        apellido_a.setForeground(new java.awt.Color(0, 0, 0));
        apellido_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido_a.setText(null);

        nombre_a.setForeground(new java.awt.Color(0, 0, 0));
        nombre_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_a.setText(null);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20))
                .addGap(90, 90, 90)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(documento_a)
                    .addComponent(cod_aportante)
                    .addComponent(apellido_a)
                    .addComponent(nombre_a, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nombre_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cod_aportante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(documento_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso"));

        jLabel27.setText("FECHA DE EGRESO");

        jLabel28.setText("FECHA DE INGRESO");

        fecha_ingreso_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_ingreso_p1KeyPressed(evt);
            }
        });

        fecha_egreso_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_egreso_p1KeyPressed(evt);
            }
        });

        Obtener2.setText("Obtener Total");
        Obtener2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Obtener2MousePressed(evt);
            }
        });

        jPanel14.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel14ComponentHidden(evt);
            }
        });

        jLabel29.setText("Total");

        tarifa2.setForeground(new java.awt.Color(0, 0, 0));
        tarifa2.setText("Tarifa");
        tarifa.setEnabled(false);
        tarifa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifa2ActionPerformed(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_attach_money_black_24dp.png"))); // NOI18N

        Boton_ingreso_a.setText(" REGISTRAR INGRESO ");
        Boton_ingreso_a.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Boton_ingreso_a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_ingreso_a.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Boton_ingreso_aMousePressed(evt);
            }
        });

        Parsela_a.setText("Parcela");
        Parsela_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Parsela_aActionPerformed(evt);
            }
        });
        Parsela_a.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Parsela_aKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Parsela_a)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 17, Short.MAX_VALUE)
                        .addComponent(Boton_ingreso_a))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(tarifa2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Parsela_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(2, 2, 2)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(tarifa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Boton_ingreso_a, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fecha_ingreso_p1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                        .addComponent(fecha_egreso_p1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Obtener2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingreso_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_egreso_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener2)))
                .addContainerGap())
        );

        pasar_dia1.setText("Pasar el dia");
        pasar_dia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasar_dia1ActionPerformed(evt);
            }
        });
        pasar_dia1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasar_dia1KeyPressed(evt);
            }
        });

        jLabel17.setText("Selecione si solo pasa el dia:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(pasar_dia1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(pasar_dia1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        vehiculos.addTab("APORTANTES", jPanel4);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso Alumno"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso"));

        jLabel1.setText("FECHA DE EGRESO");

        jLabel2.setText("FECHA DE INGRESO");

        fecha_ingreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_ingresoKeyPressed(evt);
            }
        });

        fecha_egreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_egresoKeyPressed(evt);
            }
        });

        Obtener.setText("Obtener Total");
        Obtener.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ObtenerMousePressed(evt);
            }
        });

        jPanel8.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel8ComponentHidden(evt);
            }
        });

        jLabel11.setText("Total");

        tarifa.setForeground(new java.awt.Color(0, 0, 0));
        tarifa.setText("Tarifa");
        tarifa.setEnabled(false);
        tarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifaActionPerformed(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_attach_money_black_24dp.png"))); // NOI18N

        Boton_ingreso.setText(" REGISTRAR INGRESO ");
        Boton_ingreso.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Boton_ingreso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_ingreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Boton_ingresoMousePressed(evt);
            }
        });

        Parcela.setText("Parcela");
        Parcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParcelaActionPerformed(evt);
            }
        });
        Parcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ParcelaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Parcela)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Boton_ingreso))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(tarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Parcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(tarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Boton_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fecha_ingreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                        .addComponent(fecha_egreso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Obtener, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_egreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pasar_dia.setText("Pasar el dia");
        pasar_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasar_diaActionPerformed(evt);
            }
        });
        pasar_dia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasar_diaKeyPressed(evt);
            }
        });

        jLabel36.setText("Selecione si solo pasa el dia:");

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de Alumno"));

        jLabel7.setText("DOCUMENTO:");

        documento_e.setForeground(new java.awt.Color(0, 0, 0));
        documento_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_e.setText(null);
        documento_e.setEnabled(false);
        documento_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_eActionPerformed(evt);
            }
        });

        apellido_e.setForeground(new java.awt.Color(0, 0, 0));
        apellido_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido_e.setText(null);
        apellido_e.setEnabled(false);

        nombre_e.setForeground(new java.awt.Color(0, 0, 0));
        nombre_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_e.setText(null);
        nombre_e.setEnabled(false);

        carrera_e.setForeground(new java.awt.Color(0, 0, 0));
        carrera_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carrera_e.setText(null);
        carrera_e.setEnabled(false);
        carrera_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrera_eActionPerformed(evt);
            }
        });

        facultad_e.setForeground(new java.awt.Color(0, 0, 0));
        facultad_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facultad_e.setText(null);
        facultad_e.setEnabled(false);
        facultad_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultad_eActionPerformed(evt);
            }
        });

        jLabel13.setText("FACULTAD:");

        jLabel12.setText("CARRERA:");

        jLabel6.setText("NOMBRE:");

        jLabel8.setText("APELLIDO:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(facultad_e, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carrera_e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre_e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellido_e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(documento_e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(documento_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carrera_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facultad_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda por Documento"));

        Documento.setForeground(new java.awt.Color(0, 0, 0));
        Documento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Documento.setText(null);
        Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentoActionPerformed(evt);
            }
        });
        Documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DocumentoKeyPressed(evt);
            }
        });

        jLabel10.setText("Ingrese un Documento");

        Buscar.setBackground(new java.awt.Color(255, 255, 255));
        Buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_search_black_24dp.png"))); // NOI18N
        Buscar.setText("BUSCAR");
        Buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BuscarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(pasar_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(pasar_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        vehiculos.addTab("ALUMNOS", jPanel2);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar un Particular"));

        documento_p.setForeground(new java.awt.Color(0, 0, 0));
        documento_p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_p.setText("Documento");
        documento_e.setEnabled(false);
        documento_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_pActionPerformed(evt);
            }
        });
        documento_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                documento_pKeyPressed(evt);
            }
        });

        jLabel18.setText("NOMBRE");

        nombre_p.setForeground(new java.awt.Color(0, 0, 0));
        nombre_p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_p.setText("Nombre");
        nombre_e.setEnabled(false);
        nombre_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombre_pKeyPressed(evt);
            }
        });

        jLabel19.setText("DOCUMENTO");

        jLabel23.setText("FECHA DE EGRESO");

        jLabel24.setText("FECHA DE INGRESO");

        fecha_ingreso_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_ingreso_pKeyPressed(evt);
            }
        });

        fecha_egreso_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_egreso_pKeyPressed(evt);
            }
        });

        Obtener1.setText("Obtener Total");
        Obtener1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Obtener1MousePressed(evt);
            }
        });

        jPanel12.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel12ComponentHidden(evt);
            }
        });

        jLabel25.setText("Total");

        tarifa1.setText("Tarifa");
        tarifa.setEnabled(false);
        tarifa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifa1ActionPerformed(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_attach_money_black_24dp.png"))); // NOI18N

        Boton_ingreso_p.setText(" REGISTRAR INGRESO ");
        Boton_ingreso_p.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Boton_ingreso_p.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_ingreso_p.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Boton_ingreso_pMousePressed(evt);
            }
        });
        Boton_ingreso_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Boton_ingreso_pKeyPressed(evt);
            }
        });

        Parsela_p.setText("Parcela");
        Parsela_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Parsela_pActionPerformed(evt);
            }
        });
        Parsela_p.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Parsela_pKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Parsela_p)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Boton_ingreso_p))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(tarifa1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Parsela_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(2, 2, 2)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(tarifa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Boton_ingreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Obtener1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fecha_egreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fecha_ingreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24))
                        .addGap(75, 75, 75))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecha_egreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener1))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pasar_dia2.setText("Pasar el dia");
        pasar_dia2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pasar_dia2MousePressed(evt);
            }
        });
        pasar_dia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasar_dia2ActionPerformed(evt);
            }
        });
        pasar_dia2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasar_dia2KeyPressed(evt);
            }
        });

        jLabel37.setText("Selecione si solo pasa el dia:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(documento_p)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(367, 367, 367))
                            .addComponent(nombre_p)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pasar_dia2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documento_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasar_dia2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(43, 43, 43)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        vehiculos.addTab("PARTICULAR", jPanel3);

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Acampante"));

        jLabel31.setText("Ingrese el documento");

        dni_buscado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dni_buscadoActionPerformed(evt);
            }
        });
        dni_buscado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dni_buscadoKeyPressed(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("BUSCAR");
        jLabel32.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel32MousePressed(evt);
            }
        });
        jLabel32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel32KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dni_buscado, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dni_buscado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar un Egreso Anticipado de Acampante"));

        tabla_egreso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Nombre", "Categoria", "Importe", "Fecha de egreso", "Fecha de ingreso", "Parcela"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_egreso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_egresoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_egreso);

        jLabel34.setText("ELIMINAR");
        jLabel34.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel34MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel34MouseReleased(evt);
            }
        });

        jLabel33.setText("ACTUALIZAR ");
        jLabel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel33MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel33)
                .addContainerGap())
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(461, 461, 461))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        vehiculos.addTab("EGRESOS", jPanel15);

        tabla_vehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marca", "Patente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_vehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_vehiculoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_vehiculo);

        jLabel35.setText("Vehiculos registrados en el camping");

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso de Vehiculo"));

        jLabel21.setText("Marca-Modelo");

        jLabel22.setText("Patente");

        patente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patenteActionPerformed(evt);
            }
        });
        patente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                patenteKeyPressed(evt);
            }
        });

        ingreso_vehiculo.setText("Ingresar vehiculo");
        ingreso_vehiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ingreso_vehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ingreso_vehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ingreso_vehiculoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ingreso_vehiculo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(87, 87, 87))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ingreso_vehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jLabel5.setText("Eliminar");
        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        vehiculos.addTab("VEHICULOS", jPanel10);

        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel22MouseEntered(evt);
            }
        });
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        j10.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j10.setText("10");
        j10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j10MousePressed(evt);
            }
        });
        jPanel22.add(j10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, -1, -1));

        j12.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j12.setText("12");
        j12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j12MousePressed(evt);
            }
        });
        jPanel22.add(j12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, -1, -1));

        j5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j5.setText("5");
        j5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j5MousePressed(evt);
            }
        });
        jPanel22.add(j5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        j27.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j27.setText("27");
        j27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j27MousePressed(evt);
            }
        });
        jPanel22.add(j27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, -1, -1));

        j7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j7.setText("7");
        j7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j7MousePressed(evt);
            }
        });
        jPanel22.add(j7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        j29.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j29.setText("29");
        j29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j29MousePressed(evt);
            }
        });
        jPanel22.add(j29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        j20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j20.setText("20");
        j20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j20MousePressed(evt);
            }
        });
        jPanel22.add(j20, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, -1, -1));

        j3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j3.setText("3");
        j3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j3MousePressed(evt);
            }
        });
        jPanel22.add(j3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        j31.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j31.setText("31");
        j31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j31MousePressed(evt);
            }
        });
        jPanel22.add(j31, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, -1, -1));

        j40.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j40.setText("40");
        j40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j40MousePressed(evt);
            }
        });
        jPanel22.add(j40, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        j49.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j49.setText("49");
        j49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j49MousePressed(evt);
            }
        });
        jPanel22.add(j49, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        j45.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j45.setText("45");
        j45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j45MousePressed(evt);
            }
        });
        jPanel22.add(j45, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, -1, -1));

        j50.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j50.setText("50");
        j50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j50MousePressed(evt);
            }
        });
        jPanel22.add(j50, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        j6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j6.setText("6");
        j6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j6MousePressed(evt);
            }
        });
        jPanel22.add(j6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, -1, -1));

        j15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j15.setText("15");
        j15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j15MousePressed(evt);
            }
        });
        jPanel22.add(j15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, -1, -1));

        j2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j2.setText("2");
        j2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        j2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j2MousePressed(evt);
            }
        });
        jPanel22.add(j2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, -1, -1));

        j25.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j25.setText("25");
        j25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j25MousePressed(evt);
            }
        });
        jPanel22.add(j25, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, -1, -1));

        j33.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j33.setText("33");
        j33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j33MousePressed(evt);
            }
        });
        jPanel22.add(j33, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

        j37.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j37.setText("37");
        j37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j37MousePressed(evt);
            }
        });
        jPanel22.add(j37, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, -1, -1));

        j36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j36.setText("36");
        j36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j36MousePressed(evt);
            }
        });
        jPanel22.add(j36, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        j48.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j48.setText("48");
        j48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j48MousePressed(evt);
            }
        });
        jPanel22.add(j48, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, -1, -1));

        j39.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j39.setText("39");
        j39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j39MousePressed(evt);
            }
        });
        jPanel22.add(j39, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, -1, -1));

        j38.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j38.setText("38");
        j38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j38MousePressed(evt);
            }
        });
        jPanel22.add(j38, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, -1, -1));

        j34.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j34.setText("34");
        j34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j34MousePressed(evt);
            }
        });
        jPanel22.add(j34, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, -1, -1));

        j43.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j43.setText("43");
        j43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j43MousePressed(evt);
            }
        });
        jPanel22.add(j43, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, -1, -1));

        j30.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j30.setText("30");
        j30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j30MousePressed(evt);
            }
        });
        jPanel22.add(j30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, -1, -1));

        j42.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j42.setText("42");
        j42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j42MousePressed(evt);
            }
        });
        jPanel22.add(j42, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 420, -1, -1));

        j24.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j24.setText("24");
        j24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j24MousePressed(evt);
            }
        });
        jPanel22.add(j24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, -1, -1));

        j23.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j23.setText("23");
        j23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j23MousePressed(evt);
            }
        });
        jPanel22.add(j23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

        j21.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j21.setText("21");
        j21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j21MousePressed(evt);
            }
        });
        jPanel22.add(j21, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, -1, -1));

        j19.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j19.setText("19");
        j19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j19MousePressed(evt);
            }
        });
        jPanel22.add(j19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, -1, -1));

        j18.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j18.setText("18");
        j18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j18MousePressed(evt);
            }
        });
        jPanel22.add(j18, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, -1, -1));

        j4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j4.setText("4");
        j4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j4MousePressed(evt);
            }
        });
        jPanel22.add(j4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        j16.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j16.setText("16");
        j16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j16MousePressed(evt);
            }
        });
        jPanel22.add(j16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        j17.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j17.setText("17");
        j17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j17MousePressed(evt);
            }
        });
        jPanel22.add(j17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, -1, -1));

        j14.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j14.setText("14");
        j14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j14MousePressed(evt);
            }
        });
        jPanel22.add(j14, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, -1));

        j13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j13.setText("13");
        j13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j13MousePressed(evt);
            }
        });
        jPanel22.add(j13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, -1, -1));

        j8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j8.setText("8");
        j8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j8MousePressed(evt);
            }
        });
        jPanel22.add(j8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, -1, -1));

        j9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j9.setText("9");
        j9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j9MousePressed(evt);
            }
        });
        jPanel22.add(j9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, -1, -1));

        j11.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j11.setText("11");
        j11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j11MousePressed(evt);
            }
        });
        jPanel22.add(j11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        j46.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j46.setText("46");
        j46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j46MousePressed(evt);
            }
        });
        jPanel22.add(j46, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 570, -1, -1));

        j35.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j35.setText("35");
        j35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j35MousePressed(evt);
            }
        });
        jPanel22.add(j35, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, -1, -1));

        j41.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j41.setText("41");
        j41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j41MousePressed(evt);
            }
        });
        jPanel22.add(j41, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, -1, -1));

        j32.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j32.setText("32");
        j32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j32MousePressed(evt);
            }
        });
        jPanel22.add(j32, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        j28.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j28.setText("28");
        j28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j28MousePressed(evt);
            }
        });
        jPanel22.add(j28, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, -1, -1));

        j26.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j26.setText("26");
        j26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j26MousePressed(evt);
            }
        });
        jPanel22.add(j26, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, -1));

        j1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j1.setText("1");
        j1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j1MousePressed(evt);
            }
        });
        jPanel22.add(j1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        j22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j22.setText("22");
        j22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j22MousePressed(evt);
            }
        });
        jPanel22.add(j22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, -1, -1));

        j44.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j44.setText("44");
        j44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j44MousePressed(evt);
            }
        });
        jPanel22.add(j44, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, -1, -1));

        j47.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j47.setText("47");
        j47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j47MousePressed(evt);
            }
        });
        jPanel22.add(j47, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, -1, -1));

        j51.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j51.setText("51");
        j51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j51MousePressed(evt);
            }
        });
        jPanel22.add(j51, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, -1));

        j52.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j52.setText("52");
        j52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j52MousePressed(evt);
            }
        });
        jPanel22.add(j52, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, -1, -1));

        j53.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j53.setText("53");
        j53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j53MousePressed(evt);
            }
        });
        jPanel22.add(j53, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, -1, -1));

        j54.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j54.setText("54");
        j54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j54MousePressed(evt);
            }
        });
        jPanel22.add(j54, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        j55.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j55.setText("55");
        j55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j55MousePressed(evt);
            }
        });
        jPanel22.add(j55, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, -1, -1));

        j56.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j56.setText("56");
        j56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j56MousePressed(evt);
            }
        });
        jPanel22.add(j56, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, -1, -1));

        j57.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j57.setText("57");
        j57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j57MousePressed(evt);
            }
        });
        jPanel22.add(j57, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));

        j58.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j58.setText("58");
        j58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j58MousePressed(evt);
            }
        });
        jPanel22.add(j58, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, -1));

        j59.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j59.setText("59");
        j59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j59MousePressed(evt);
            }
        });
        jPanel22.add(j59, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        j60.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j60.setText("60");
        j60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j60MousePressed(evt);
            }
        });
        jPanel22.add(j60, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, -1, -1));

        j61.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j61.setText("61");
        j61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j61MousePressed(evt);
            }
        });
        jPanel22.add(j61, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, -1, -1));

        j62.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j62.setText("62");
        j62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j62MousePressed(evt);
            }
        });
        jPanel22.add(j62, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

        j63.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j63.setText("63");
        j63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j63MousePressed(evt);
            }
        });
        jPanel22.add(j63, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        j64.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j64.setText("64");
        j64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j64MousePressed(evt);
            }
        });
        jPanel22.add(j64, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        j65.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j65.setText("65");
        j65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j65MousePressed(evt);
            }
        });
        jPanel22.add(j65, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        j66.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j66.setText("78");
        j66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j66MousePressed(evt);
            }
        });
        jPanel22.add(j66, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        j67.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j67.setText("67");
        j67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j67MousePressed(evt);
            }
        });
        jPanel22.add(j67, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, -1, -1));

        j68.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j68.setText("66");
        j68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j68MousePressed(evt);
            }
        });
        jPanel22.add(j68, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        j69.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j69.setText("68");
        j69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j69MousePressed(evt);
            }
        });
        jPanel22.add(j69, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        j70.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j70.setText("69");
        j70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j70MousePressed(evt);
            }
        });
        jPanel22.add(j70, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        j71.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j71.setText("71");
        j71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j71MousePressed(evt);
            }
        });
        jPanel22.add(j71, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        j72.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j72.setText("72");
        j72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j72MousePressed(evt);
            }
        });
        jPanel22.add(j72, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        j73.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j73.setText("73");
        j73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j73MousePressed(evt);
            }
        });
        jPanel22.add(j73, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        j74.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j74.setText("74");
        j74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j74MousePressed(evt);
            }
        });
        jPanel22.add(j74, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        j75.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j75.setText("75");
        j75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j75MousePressed(evt);
            }
        });
        jPanel22.add(j75, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, -1, -1));

        j76.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j76.setText("76");
        j76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j76MousePressed(evt);
            }
        });
        jPanel22.add(j76, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        j77.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j77.setText("77");
        j77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j77MousePressed(evt);
            }
        });
        jPanel22.add(j77, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        j78.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j78.setText("70");
        j78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j78MousePressed(evt);
            }
        });
        jPanel22.add(j78, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, -1));

        j79.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j79.setText("79");
        j79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j79MousePressed(evt);
            }
        });
        jPanel22.add(j79, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, -1, -1));

        j80.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j80.setText("80");
        j80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j80MousePressed(evt);
            }
        });
        jPanel22.add(j80, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, -1, -1));

        j81.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j81.setText("81");
        j81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j81MousePressed(evt);
            }
        });
        jPanel22.add(j81, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        j82.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j82.setText("82");
        j82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j82MousePressed(evt);
            }
        });
        jPanel22.add(j82, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        j83.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j83.setText("83");
        j83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j83MousePressed(evt);
            }
        });
        jPanel22.add(j83, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, -1, -1));

        j84.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j84.setText("84");
        j84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j84MousePressed(evt);
            }
        });
        jPanel22.add(j84, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        j85.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j85.setText("85");
        j85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j85MousePressed(evt);
            }
        });
        jPanel22.add(j85, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, -1, -1));

        j86.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j86.setText("86");
        j86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j86MousePressed(evt);
            }
        });
        jPanel22.add(j86, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 530, -1, -1));

        j87.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j87.setText("87");
        j87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j87MousePressed(evt);
            }
        });
        jPanel22.add(j87, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, -1, -1));

        j88.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j88.setText("88");
        j88.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j88MousePressed(evt);
            }
        });
        jPanel22.add(j88, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, -1, -1));

        j89.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j89.setText("89");
        j89.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j89MousePressed(evt);
            }
        });
        jPanel22.add(j89, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 370, -1, -1));

        j90.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j90.setText("90");
        j90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j90MousePressed(evt);
            }
        });
        jPanel22.add(j90, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        j91.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j91.setText("91");
        j91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j91MousePressed(evt);
            }
        });
        jPanel22.add(j91, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

        j92.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j92.setText("92");
        j92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j92MousePressed(evt);
            }
        });
        jPanel22.add(j92, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        j93.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j93.setText("93");
        j93.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j93MousePressed(evt);
            }
        });
        jPanel22.add(j93, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, -1));

        j94.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j94.setText("94");
        j94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j94MousePressed(evt);
            }
        });
        jPanel22.add(j94, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        j95.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j95.setText("95");
        j95.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j95MousePressed(evt);
            }
        });
        jPanel22.add(j95, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        j96.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j96.setText("96");
        j96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j96MousePressed(evt);
            }
        });
        jPanel22.add(j96, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));

        j97.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j97.setText("97");
        j97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j97MousePressed(evt);
            }
        });
        jPanel22.add(j97, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        j98.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j98.setText("98");
        j98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j98MousePressed(evt);
            }
        });
        jPanel22.add(j98, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, -1, -1));

        j99.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j99.setText("99");
        j99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j99MousePressed(evt);
            }
        });
        jPanel22.add(j99, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, -1, -1));

        j100.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j100.setText("100");
        j100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j100MousePressed(evt);
            }
        });
        jPanel22.add(j100, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));

        j101.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j101.setText("101");
        j101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j101MousePressed(evt);
            }
        });
        jPanel22.add(j101, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, -1));

        j102.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j102.setText("102");
        j102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j102MousePressed(evt);
            }
        });
        jPanel22.add(j102, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        j103.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j103.setText("103");
        j103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j103MousePressed(evt);
            }
        });
        jPanel22.add(j103, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, -1, -1));

        j104.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j104.setText("104");
        j104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j104MousePressed(evt);
            }
        });
        jPanel22.add(j104, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, -1, -1));

        j105.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j105.setText("105");
        j105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j105MousePressed(evt);
            }
        });
        jPanel22.add(j105, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, -1));

        j106.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j106.setText("106");
        j106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j106MousePressed(evt);
            }
        });
        jPanel22.add(j106, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        j107.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j107.setText("107");
        j107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j107MousePressed(evt);
            }
        });
        jPanel22.add(j107, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        j108.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j108.setText("108");
        j108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j108MousePressed(evt);
            }
        });
        jPanel22.add(j108, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        j109.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j109.setText("109");
        j109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j109MousePressed(evt);
            }
        });
        jPanel22.add(j109, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        j110.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j110.setText("110");
        j110.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j110MousePressed(evt);
            }
        });
        jPanel22.add(j110, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, -1, -1));

        j111.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j111.setText("111");
        j111.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j111MousePressed(evt);
            }
        });
        jPanel22.add(j111, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        j112.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j112.setText("112");
        j112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j112MousePressed(evt);
            }
        });
        jPanel22.add(j112, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        j113.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j113.setText("113");
        j113.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j113MousePressed(evt);
            }
        });
        jPanel22.add(j113, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));

        j114.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j114.setText("114");
        j114.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j114MousePressed(evt);
            }
        });
        jPanel22.add(j114, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 270, -1, -1));

        j115.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j115.setText("115");
        j115.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j115MousePressed(evt);
            }
        });
        jPanel22.add(j115, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, -1));

        j116.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j116.setText("116");
        j116.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j116MousePressed(evt);
            }
        });
        jPanel22.add(j116, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, -1, -1));

        j117.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j117.setText("117");
        j117.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j117MousePressed(evt);
            }
        });
        jPanel22.add(j117, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        j118.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j118.setText("118");
        j118.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j118MousePressed(evt);
            }
        });
        jPanel22.add(j118, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        j119.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j119.setText("119");
        j119.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j119MousePressed(evt);
            }
        });
        jPanel22.add(j119, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        j120.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j120.setText("120");
        j120.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j120MousePressed(evt);
            }
        });
        jPanel22.add(j120, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        j121.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j121.setText("121");
        j121.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j121MousePressed(evt);
            }
        });
        jPanel22.add(j121, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, -1, -1));

        j122.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j122.setText("122");
        j122.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j122MousePressed(evt);
            }
        });
        jPanel22.add(j122, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        j123.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j123.setText("123");
        j123.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j123MousePressed(evt);
            }
        });
        jPanel22.add(j123, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, -1, -1));

        j124.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j124.setText("124");
        j124.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j124MousePressed(evt);
            }
        });
        jPanel22.add(j124, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 670, -1, -1));

        j125.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j125.setText("125");
        j125.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j125MousePressed(evt);
            }
        });
        jPanel22.add(j125, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 600, -1, -1));

        j126.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j126.setText("126");
        j126.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j126MousePressed(evt);
            }
        });
        jPanel22.add(j126, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 630, -1, -1));

        j127.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j127.setText("127");
        j127.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j127MousePressed(evt);
            }
        });
        jPanel22.add(j127, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 690, -1, -1));

        j128.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j128.setText("128");
        j128.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j128MousePressed(evt);
            }
        });
        jPanel22.add(j128, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, -1, -1));

        boton_v.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        boton_v.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        boton_v.setText("Libre");
        boton_v.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton_vMousePressed(evt);
            }
        });
        jPanel22.add(boton_v, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        boton_r.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        boton_r.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
        boton_r.setText("Ocupado");
        boton_r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton_rMousePressed(evt);
            }
        });
        jPanel22.add(boton_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/mapa-4.jpg"))); // NOI18N
        jPanel22.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 21, 630, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Documento_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Documento_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Documento_aActionPerformed

    private void Buscar_aMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Buscar_aMousePressed
        try {
            BuscarAportante();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Buscar_aMousePressed

    private void documento_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_eActionPerformed

    private void carrera_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carrera_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_carrera_eActionPerformed

    private void facultad_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_facultad_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_facultad_eActionPerformed

    private void BuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscarMousePressed
        try {
            BuscarEstudiante();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarMousePressed

    private void DocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DocumentoActionPerformed
    public void ingre_p() throws NullPointerException, SQLException {

        if (!documento_p.getText().isEmpty() || !nombre_p.getText().isEmpty()) {

            int parsela = 0;
            try {

                //controlar que en parsela no haya un string
                try {
                    parsela = Integer.parseInt(Parsela_p.getText());
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("es un string y no se puede pasar");
                }

                //control que parsela este entre 128 y 1
                if (parsela <= 128 && parsela >= 1) {

                    //  Ocupacion ocupacion = new Ocupacion();
                    // RegistrarParsela();
                    //ocupacion.BuscarParsela(Parsela.getText());
                    RegistrarIngresoParticular();

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (java.text.ParseException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (java.lang.NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Ingreso un texto donde se esperaba un numero.", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR, complete todos los campos antes de registrar un ingreso. \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ingre() throws SQLException {
        int parcela = 0;
        try {

            //controlar que en parsela no haya un string
            try {
                parcela = Integer.parseInt(Parcela.getText());
            } catch (java.lang.NumberFormatException e) {
                System.out.println("es un string y no se puede pasar");
            }

            //control que parsela este entre 128 y 1
            if (parcela <= 128 && parcela >= 1) {

                RegistrarIngreso();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.text.ParseException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ingre_a() throws SQLException {
        int parsela = 0;
        try {

            //controlar que en parsela no haya un string
            try {
                parsela = Integer.parseInt(Parsela_a.getText());
            } catch (java.lang.NumberFormatException e) {
                System.out.println("es un string y no se puede pasar");
            }

            //control que parsela este entre 128 y 1
            if (parsela <= 128 && parsela >= 1) {

                //  Ocupacion ocupacion = new Ocupacion();
                // RegistrarParsela();
                //ocupacion.BuscarParsela(Parsela.getText());
                RegistrarIngresoAportante();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.text.ParseException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Boton_ingreso_alumno() {
        try {
            if (controlador.ControlarCajaAbierta() == 1) {
                int c = controlador.Controldnirepetidoingreso(documento_e.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_e.getText());

                if (documento_e.getText().equals("") || nombre_e.getText().equals("")) {
                    throw new Exception();
                }
                if (c == 0 && b == 0) {
                    if (pasar_dia.isSelected()) {

                        ingre_dia("alumno", documento_e.getText(), nombre_e.getText());
                    } else {
                        ingre();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe abrir la caja primero", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }
    private void Boton_ingresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingresoMousePressed
        Boton_ingreso_alumno();

    }//GEN-LAST:event_Boton_ingresoMousePressed

    public long cant_dias(JDateChooser fecha_1, JDateChooser fecha_2) throws java.text.ParseException, java.lang.NullPointerException {

        try {
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = myFormat.parse(calc_fecha(fecha_1));
            Date date2 = myFormat.parse(calc_fecha(fecha_2));
            long diff = date2.getTime() - date1.getTime();

            long p = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            return p;
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } catch (java.lang.NullPointerException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error, complete todos los campos. \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        return 0;
    }

    public String calc_fecha(com.toedter.calendar.JDateChooser fecha) {
        String arr[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        char c1, c2;
        if (fecha.getDate() != null) {

            c1 = fecha.getDate().toString().charAt(8);
            c2 = fecha.getDate().toString().charAt(9);

            if ("".equals(fecha.getDate().toString())) {
                System.out.println("fecha nula:");
            }

            int cont = 0;
            String l = "";

            //     System.out.println(fecha.getDate().toString().length());
            for (int i = fecha.getDate().toString().length(); i > 0; i--) {
                cont++;
                l += fecha.getDate().toString().charAt(i - 1);
                if (cont == 4) {
                    break;
                }
            }
            char k;
            String anio = "";
            for (int i = l.length(); i > 0; i--) {
                k = l.charAt(i - 1);
                anio += k;
            }
            String z = "";
            for (int i = 4; i < 7; i++) {
                z += fecha.getDate().toString().charAt(i);
            }
            int x = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(z)) {
                    x = i + 1;
                }
            }
            String mes = "";

            if (x < 10) {
                mes = String.format("0%d", x);
            } else {
                mes = Integer.toString(x);
            }

            int dia = Integer.parseInt((c1 - '0') + "" + (c2 - '0'));
            String fecha_t;
            if (dia < 10) {
                fecha_t = anio + "-" + mes + "-" + String.format("0%d", dia);
                //      dia = Integer.parseInt(str);
            } else {
                fecha_t = anio + "-" + mes + "-" + dia;
            }
            return fecha_t;

        } else {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de ingreso y egreso.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
        return null;
    }

    public void ingre_dia(String categoria, String dni, String nombre) throws SQLException, java.text.ParseException {

        int c;
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));

        String hora_actual = hora + ":" + minutos;

        c = controlador.IngresoDiario(dni, nombre, hora_actual, categoria);

        if (c != 1) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (categoria.equals("alumno")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo alumno por el dia", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(Main.tarifa_dia_alumnos);
            }
            if (categoria.equals("aportante")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo aportante por el dia", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(Main.tarifa_dia_aportantes);
                 Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");
                    System.out.println(result);
                    if (result == 0) {
                        System.out.println("entro");
                        if (!pasar_dia1.isSelected()) {
                            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
                            imprimirtiketacampante(calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), "Aportante", importe);
                        } else {
                            
                            float importe = Main.tarifa_dia_aportantes;
                            imprimirtiketdia("Aportante", importe);
                        }
                    }
                
            }
            if (categoria.equals("particular")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo particular por el dia", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(Main.tarifa_dia_particular);
            }
            setearnullalumno();
            setearnullparticular();
            setearnullaportante();
            
        }
    }

    private void ObtenerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ObtenerMousePressed
        try {
            if (!pasar_dia.isSelected()) {
                float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);

                tarifa.setText(String.format("%.2f", importe));

            } else {
                tarifa.setText(String.format("%.2f", Main.tarifa_dia_alumnos));
            }

        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_ObtenerMousePressed

    private void tarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifaActionPerformed

    private void jPanel8ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel8ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8ComponentHidden

    private void ParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ParcelaActionPerformed

    private void documento_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_pActionPerformed

    private void Obtener1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener1MousePressed

        try {
            if (!pasar_dia2.isSelected()) {
                float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
                tarifa1.setText(String.format("%.2f", importe));
            } else {
                tarifa1.setText(String.format("%.2f", Main.tarifa_dia_particular));

            }
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Obtener1MousePressed

    private void tarifa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa1ActionPerformed
    public void Boton_ingreso_particular() {
        try {
            if (controlador.ControlarCajaAbierta() == 1) {
                int c = controlador.Controldnirepetidoingreso(documento_p.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_p.getText());

                if (documento_p.getText().equals("") || nombre_p.getText().equals("")) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia2.isSelected()) {

                        ingre_dia("particular", documento_p.getText(), nombre_p.getText());
                    } else {
                        ingre_p();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe abrir la caja primero", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void Boton_ingreso_pMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingreso_pMousePressed
        Boton_ingreso_particular();

    }//GEN-LAST:event_Boton_ingreso_pMousePressed

    private void jPanel12ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel12ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12ComponentHidden

    private void Obtener2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener2MousePressed
        try {
            if (!pasar_dia1.isSelected()) {
                float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
                tarifa2.setText(String.format("%.2f", importe));
            } else {
                tarifa2.setText(String.format("%.2f", Main.tarifa_dia_aportantes));
            }

        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Obtener2MousePressed

    private void tarifa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa2ActionPerformed
    public void botoningresoaportante() {
        try {
            if (controlador.ControlarCajaAbierta() == 1) {
                int c = controlador.Controldnirepetidoingreso(documento_a.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_a.getText());

                if (documento_a.getText().equals("") || nombre_a.getText().equals("") || apellido_a.getText().isEmpty() || cod_aportante.getText().isEmpty()) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia1.isSelected()) {
                        ingre_dia("aportante", documento_a.getText(), nombre_a.getText());
                    } else {
                        ingre_a();
                    }

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe primero abrir la caja", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void Boton_ingreso_aMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingreso_aMousePressed
        botoningresoaportante();
        try {
            prueba();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Boton_ingreso_aMousePressed
    public void BusquedaVehiculos() throws SQLException {
        int f = 0;
        ResultSet res_1;
        res_1 = controlador.MostarVehiculos();
        while (res_1.next()) {

            f++;
            String documento = res_1.getString("marca");
            String nombre = res_1.getString("patente");

            String tab[] = {documento, nombre};
            DefaultTableModel tablamodelo = (DefaultTableModel) tabla_vehiculo.getModel();

            tablamodelo.addRow(tab);
            filas = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }
    }
    private void Parsela_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Parsela_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Parsela_aActionPerformed

    private void jPanel14ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel14ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel14ComponentHidden

    private void Parsela_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Parsela_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Parsela_pActionPerformed

    private void vehiculosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehiculosMousePressed

    }//GEN-LAST:event_vehiculosMousePressed

    private void pasar_dia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasar_dia2ActionPerformed
        if (pasar_dia2.isSelected()) {
            fecha_ingreso_p.setEnabled(false);
            fecha_egreso_p.setEnabled(false);
            Parsela_p.setEnabled(false);
        } else {
            Parsela_p.setEnabled(true);
            fecha_ingreso_p.setEnabled(true);
            fecha_egreso_p.setEnabled(true);
        }
    }//GEN-LAST:event_pasar_dia2ActionPerformed

    private void pasar_dia2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasar_dia2MousePressed

    }//GEN-LAST:event_pasar_dia2MousePressed

    private void pasar_dia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasar_dia1ActionPerformed
        if (pasar_dia1.isSelected()) {
            fecha_ingreso_p1.setEnabled(false);
            fecha_egreso_p1.setEnabled(false);
            Parsela_a.setEnabled(false);
        } else {
            Parsela_a.setEnabled(true);
            fecha_ingreso_p1.setEnabled(true);
            fecha_egreso_p1.setEnabled(true);
        }
    }//GEN-LAST:event_pasar_dia1ActionPerformed

    private void pasar_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasar_diaActionPerformed
        if (pasar_dia.isSelected()) {
            fecha_ingreso.setEnabled(false);
            fecha_egreso.setEnabled(false);
            Parcela.setEnabled(false);
        } else {
            fecha_ingreso.setEnabled(true);
            fecha_egreso.setEnabled(true);
            Parcela.setEnabled(true);
        }
    }//GEN-LAST:event_pasar_diaActionPerformed

    private void documento_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_documento_pKeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
    }//GEN-LAST:event_documento_pKeyPressed

    private void Documento_aKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Documento_aKeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                BuscarAportante();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_Documento_aKeyPressed

    private void DocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DocumentoKeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                BuscarEstudiante();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_DocumentoKeyPressed

    private void patenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patenteActionPerformed

    private void ingreso_vehiculoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingreso_vehiculoMousePressed
        try {
            modelo.insertarvehiculo(patente.getText(), marca.getText(), controlador.calcularimportevehiculo());
            marca.setText(null);
            patente.setText(null);
            BusquedaVehiculos();
        } catch (SQLException ex) {
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Problemas al ingresar un vehiculo, vuelve a intentarlo.  ");
        }
    }//GEN-LAST:event_ingreso_vehiculoMousePressed

    private void dni_buscadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dni_buscadoActionPerformed
        if (dni_buscado.getText().isEmpty()) {
            try {
                Tabla();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dni_buscadoActionPerformed

    private void jLabel32KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel32KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel32KeyPressed
    public void buscar_egreso() throws SQLException {
        ResultSet res;
        int f = 0;
        res = controlador.MostarDocumento(dni_buscado.getText());

        if (res.next() == true) {
            f++;
            String documento = res.getString("documento");
            String nombre = res.getString("nombre");
            String categoria = res.getString("categoria");
            String importe = String.valueOf(res.getFloat("importe"));
            String fecha_egreso = res.getString("fecha_egreso");
            String fecha_ingreso = res.getString("fecha_ingreso");
            String parcela = res.getString("parsela");
            String tab[] = {documento, nombre, categoria, importe, fecha_egreso, fecha_ingreso, parcela};
            DefaultTableModel tablamodelo = (DefaultTableModel) tabla_egreso.getModel();

            tablamodelo.addRow(tab);
            filas = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontro el documento solicitado.\n Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void prueba() throws SQLException {
        javax.swing.JLabel[] label = {j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42, j43, j44, j45, j46, j47, j48, j49, j50,
            j51, j52, j53, j54, j55, j56, j57, j68, j59, j60, j61, j62, j63, j64, j65, j66, j67, j68, j69, j70, j71, j72, j73, j74, j75, j76, j77, j78, j79, j80, j81, j82, j83, j84, j85, j86, j87, j88, j89, j90, j91, j92, j93, j94, j95, j96, j97, j98, j99, j100, j101, j102, j103, j104, j105, j106, j107, j108, j109, j110, j111, j112, j113, j114,
            j115, j116, j117, j118, j119, j120, j121, j122, j123, j124, j125, j126, j127, j128

        };
        for (int i = 0; i < label.length; i++) {
            label[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
            label[i].setText(String.valueOf(i + 1));
            label[i].repaint();
        }

        ResultSet res;
        res = modelo.MostarOcupacionActual();

        while (res.next() == true) {

            for (int i = 0; i < label.length; i++) {

                if (String.valueOf(i + 1).equals(res.getString("parsela"))) {
                    label[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                    label[i].setText(String.valueOf(i + 1));
                    label[i].repaint();
                    i = 128;

                }

            }

            //    jLabel6.paintImmediately(jLabel6.getVisibleRect());
        }
    }

    public void traerinfo(int parsela) throws SQLException {
        Modelo modelo = new Modelo();
        ResultSet res;

        res = modelo.traerinfo(parsela);

        int i = 0;
        while (res.next()) {

            javax.swing.JOptionPane.showMessageDialog(this, " documento : '" + res.getString("documento") + "'" + "\n"
                    + "nombre: " + "'" + res.getString("nombre") + "'" + "\n"
                    + "categoria: " + "'" + res.getString("categoria") + "'" + "\n"
                    + "importe: " + "'" + res.getString("importe") + "'" + "\n"
                    + "fecha de ingreso: " + "'" + res.getString("fecha_ingreso") + "'" + "\n"
                    + "fecha de egreso: " + "'" + res.getString("fecha_egreso") + "'" + "\n",
                    "Persona nro " + (i + 1), javax.swing.JOptionPane.INFORMATION_MESSAGE);
            i++;

        }

        if (i == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encuentra nadie en la parcela seleccionada.\n Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void Tabla() throws SQLException {
        ResultSet res_1;
        res_1 = controlador.MostarOcupacionActual();

        Busqueda(res_1);
    }

    public void Busqueda(ResultSet res) throws SQLException {
        int f = 0;
        while (res.next()) {

            f++;
            String documento = res.getString("documento");
            String nombre = res.getString("nombre");
            String categoria = res.getString("categoria");
            String importe = String.valueOf(res.getFloat("importe"));
            String fecha_egreso = res.getString("fecha_egreso");
            String fecha_ingreso = res.getString("fecha_ingreso");
            String parcela = res.getString("parsela");
            String tab[] = {documento, nombre, categoria, importe, fecha_egreso, fecha_ingreso, parcela};
            DefaultTableModel tablamodelo = (DefaultTableModel) tabla_egreso.getModel();

            tablamodelo.addRow(tab);
            filas = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }
    }
    private void jLabel32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MousePressed
        try {
            buscar_egreso();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel32MousePressed

    private void jLabel33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MousePressed

        try {
            Tabla();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel33MousePressed

    private void dni_buscadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dni_buscadoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                buscar_egreso();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evt.getKeyChar() == KeyEvent.VK_SUBTRACT) {
            dni_buscado.setText(null);
            try {
                Tabla();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_dni_buscadoKeyPressed

    private void nombre_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_pKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_pKeyPressed

    private void patenteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patenteKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                modelo.insertarvehiculo(patente.getText(), marca.getText(), controlador.calcularimportevehiculo());
                marca.setText(null);
                patente.setText(null);

            } catch (SQLException ex) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "Problemas al ingresar un vehiculo, vuelve a intentarlo.  ");
            }
        }
    }//GEN-LAST:event_patenteKeyPressed
    public void eliminar_acampante() throws SQLException {
        int fila = tabla_egreso.getSelectedRow();
        String valor = tabla_egreso.getValueAt(fila, 0).toString();
        JFrame jFrame = new JFrame();
        int result = JOptionPane.showConfirmDialog(jFrame, "Este es el acampante que desea eliminar");
        if (result == 0) {
            int control = modelo.EliminarAcampante(valor);
            if (control == 1) {
                Component rootPane = null;

                JOptionPane.showMessageDialog(rootPane, "Se elimino correctamente.");
                Tabla();

            } else {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "Ocurrio un problema, vuelva a intentar");
            }

        }

    }
    private void jLabel34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MousePressed

        try {
            eliminar_acampante();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error, ingrese un acampante de la lista.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jLabel34MousePressed

    private void tabla_egresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_egresoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                eliminar_acampante();
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_tabla_egresoKeyPressed

    private void pasar_diaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasar_diaKeyPressed

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Boton_ingreso_alumno();
        }
    }//GEN-LAST:event_pasar_diaKeyPressed

    private void ParcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ParcelaKeyPressed

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Boton_ingreso_alumno();
        }
    }//GEN-LAST:event_ParcelaKeyPressed

    private void fecha_egresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_egresoKeyPressed

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {

                int c = controlador.Controldnirepetidoingreso(documento_e.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_e.getText());

                if (documento_e.getText().equals("") || nombre_e.getText().equals("")) {
                    throw new Exception();
                }
                if (c == 0 && b == 0) {
                    if (pasar_dia.isSelected()) {

                        ingre_dia("alumno", documento_e.getText(), nombre_e.getText());
                    } else {
                        ingre();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_fecha_egresoKeyPressed

    private void fecha_ingresoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_ingresoKeyPressed

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {

                int c = controlador.Controldnirepetidoingreso(documento_e.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_e.getText());

                if (documento_e.getText().equals("") || nombre_e.getText().equals("")) {
                    throw new Exception();
                }
                if (c == 0 && b == 0) {
                    if (pasar_dia.isSelected()) {

                        ingre_dia("alumno", documento_e.getText(), nombre_e.getText());
                    } else {
                        ingre();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_fecha_ingresoKeyPressed

    private void Parsela_aKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Parsela_aKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            botoningresoaportante();
        }

    }//GEN-LAST:event_Parsela_aKeyPressed

    private void fecha_ingreso_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_ingreso_p1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                int c = controlador.Controldnirepetidoingreso(documento_a.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_a.getText());

                if (documento_a.getText().equals("") || nombre_a.getText().equals("") || apellido_a.getText().isEmpty() || cod_aportante.getText().isEmpty()) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia1.isSelected()) {
                        ingre_dia("aportante", documento_a.getText(), nombre_a.getText());
                    } else {
                        ingre_a();
                    }

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_fecha_ingreso_p1KeyPressed

    private void fecha_egreso_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_egreso_p1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            botoningresoaportante();
        }
    }//GEN-LAST:event_fecha_egreso_p1KeyPressed

    private void pasar_dia1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasar_dia1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                int c = controlador.Controldnirepetidoingreso(documento_a.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_a.getText());

                if (documento_a.getText().equals("") || nombre_a.getText().equals("") || apellido_a.getText().isEmpty() || cod_aportante.getText().isEmpty()) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia1.isSelected()) {
                        ingre_dia("aportante", documento_a.getText(), nombre_a.getText());
                    } else {
                        ingre_a();
                    }

                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_pasar_dia1KeyPressed

    private void Boton_ingreso_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Boton_ingreso_pKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Boton_ingreso_pKeyPressed

    private void pasar_dia2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasar_dia2KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            Boton_ingreso_particular();
        }
    }//GEN-LAST:event_pasar_dia2KeyPressed

    private void Parsela_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Parsela_pKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            Boton_ingreso_particular();
        }
    }//GEN-LAST:event_Parsela_pKeyPressed

    private void fecha_ingreso_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_ingreso_pKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            try {
                int c = controlador.Controldnirepetidoingreso(documento_p.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_p.getText());

                if (documento_p.getText().equals("") || nombre_p.getText().equals("")) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia2.isSelected()) {

                        ingre_dia("particular", documento_p.getText(), nombre_p.getText());
                    } else {
                        ingre_p();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_fecha_ingreso_pKeyPressed

    private void fecha_egreso_pKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_egreso_pKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            try {
                int c = controlador.Controldnirepetidoingreso(documento_p.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_p.getText());

                if (documento_p.getText().equals("") || nombre_p.getText().equals("")) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia2.isSelected()) {

                        ingre_dia("particular", documento_p.getText(), nombre_p.getText());
                    } else {
                        ingre_p();
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                javax.swing.JOptionPane.showMessageDialog(this, "Complete todos los campos", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_fecha_egreso_pKeyPressed

    private void j10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j10MousePressed

        try {
            traerinfo(10);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j10MousePressed

    private void j12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j12MousePressed

        try {
            traerinfo(12);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j12MousePressed

    private void j5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j5MousePressed

        try {
            traerinfo(5);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j5MousePressed

    private void j27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j27MousePressed

        try {
            traerinfo(27);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j27MousePressed

    private void j7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j7MousePressed

        try {
            traerinfo(7);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j7MousePressed

    private void j29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j29MousePressed

        try {
            traerinfo(29);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j29MousePressed

    private void j20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j20MousePressed

        try {
            traerinfo(20);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j20MousePressed

    private void j3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j3MousePressed
        try {
            traerinfo(3);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j3MousePressed

    private void j31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j31MousePressed

        try {
            traerinfo(31);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j31MousePressed

    private void j40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j40MousePressed

        try {
            traerinfo(40);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j40MousePressed

    private void j49MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j49MousePressed

        try {
            traerinfo(49);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j49MousePressed

    private void j45MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j45MousePressed

        try {
            traerinfo(45);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j45MousePressed

    private void j50MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j50MousePressed

        try {
            traerinfo(50);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j50MousePressed

    private void j6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j6MousePressed

        try {
            traerinfo(6);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j6MousePressed

    private void j15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j15MousePressed

        try {
            traerinfo(15);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j15MousePressed

    private void j2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j2MousePressed

    }//GEN-LAST:event_j2MousePressed

    private void j25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j25MousePressed

        try {
            traerinfo(25);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j25MousePressed

    private void j33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j33MousePressed

        try {
            traerinfo(33);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j33MousePressed

    private void j37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j37MousePressed

        try {
            traerinfo(37);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j37MousePressed

    private void j36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j36MousePressed

        try {
            traerinfo(36);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j36MousePressed

    private void j48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j48MousePressed

        try {
            traerinfo(48);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j48MousePressed

    private void j39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j39MousePressed

        try {
            traerinfo(39);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j39MousePressed

    private void j38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j38MousePressed

        try {
            traerinfo(38);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j38MousePressed

    private void j34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j34MousePressed

        try {
            traerinfo(34);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j34MousePressed

    private void j43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j43MousePressed

        try {
            traerinfo(43);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j43MousePressed

    private void j30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j30MousePressed

        try {
            traerinfo(30);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j30MousePressed

    private void j42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j42MousePressed

        try {
            traerinfo(42);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j42MousePressed

    private void j24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j24MousePressed

        try {
            traerinfo(24);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j24MousePressed

    private void j23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j23MousePressed

        try {
            traerinfo(23);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j23MousePressed

    private void j21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j21MousePressed

        try {
            traerinfo(21);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j21MousePressed

    private void j19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j19MousePressed

        try {
            traerinfo(19);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j19MousePressed

    private void j18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j18MousePressed

        try {
            traerinfo(18);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j18MousePressed

    private void j4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j4MousePressed

        try {
            traerinfo(4);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j4MousePressed

    private void j16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j16MousePressed

        try {
            traerinfo(16);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j16MousePressed

    private void j17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j17MousePressed

        try {
            traerinfo(17);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j17MousePressed

    private void j14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j14MousePressed
        try {
            traerinfo(14);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j14MousePressed

    private void j13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j13MousePressed

        try {
            traerinfo(13);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j13MousePressed

    private void j8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j8MousePressed

        try {
            traerinfo(8);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j8MousePressed

    private void j9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j9MousePressed

        try {
            traerinfo(9);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j9MousePressed

    private void j11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j11MousePressed

        try {
            traerinfo(11);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j11MousePressed

    private void j46MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j46MousePressed

        try {
            traerinfo(46);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j46MousePressed

    private void j35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j35MousePressed

        try {
            traerinfo(35);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j35MousePressed

    private void j41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j41MousePressed

        try {
            traerinfo(41);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j41MousePressed

    private void j32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j32MousePressed

        try {
            traerinfo(32);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j32MousePressed

    private void j28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j28MousePressed

        try {
            traerinfo(28);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j28MousePressed

    private void j26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j26MousePressed

        try {
            traerinfo(26);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j26MousePressed

    private void j1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j1MousePressed

        try {
            traerinfo(1);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j1MousePressed

    private void j22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j22MousePressed

        try {
            traerinfo(22);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j22MousePressed

    private void j44MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j44MousePressed

        try {
            traerinfo(44);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j44MousePressed

    private void j47MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j47MousePressed

        try {
            traerinfo(47);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j47MousePressed

    private void j51MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j51MousePressed

        try {
            traerinfo(51);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j51MousePressed

    private void j52MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j52MousePressed

        try {
            traerinfo(52);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j52MousePressed

    private void j53MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j53MousePressed

        try {
            traerinfo(53);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j53MousePressed

    private void j54MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j54MousePressed

        try {
            traerinfo(54);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j54MousePressed

    private void j55MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j55MousePressed

        try {
            traerinfo(55);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j55MousePressed

    private void j56MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j56MousePressed

        try {
            traerinfo(56);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j56MousePressed

    private void j57MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j57MousePressed

        try {
            traerinfo(57);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j57MousePressed

    private void j58MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j58MousePressed

        try {
            traerinfo(58);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j58MousePressed

    private void j59MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j59MousePressed

        try {
            traerinfo(59);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j59MousePressed

    private void j60MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j60MousePressed

        try {
            traerinfo(60);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j60MousePressed

    private void j61MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j61MousePressed

        try {
            traerinfo(61);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j61MousePressed

    private void j62MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j62MousePressed

        try {
            traerinfo(62);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j62MousePressed

    private void j63MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j63MousePressed

        try {
            traerinfo(63);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j63MousePressed

    private void j64MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j64MousePressed

        try {
            traerinfo(64);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j64MousePressed

    private void j65MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j65MousePressed

        try {
            traerinfo(65);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j65MousePressed

    private void j66MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j66MousePressed

        try {
            traerinfo(66);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j66MousePressed

    private void j67MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j67MousePressed

        try {
            traerinfo(67);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j67MousePressed

    private void j68MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j68MousePressed

        try {
            traerinfo(68);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j68MousePressed

    private void j69MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j69MousePressed

        try {
            traerinfo(69);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j69MousePressed

    private void j70MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j70MousePressed

        try {
            traerinfo(70);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j70MousePressed

    private void j71MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j71MousePressed

        try {
            traerinfo(71);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j71MousePressed

    private void j72MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j72MousePressed

        try {
            traerinfo(72);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j72MousePressed

    private void j73MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j73MousePressed

        try {
            traerinfo(73);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j73MousePressed

    private void j74MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j74MousePressed

        try {
            traerinfo(74);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j74MousePressed

    private void j75MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j75MousePressed

        try {
            traerinfo(75);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j75MousePressed

    private void j76MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j76MousePressed

        try {
            traerinfo(76);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j76MousePressed

    private void j77MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j77MousePressed

        try {
            traerinfo(77);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j77MousePressed

    private void j78MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j78MousePressed

        try {
            traerinfo(78);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j78MousePressed

    private void j79MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j79MousePressed

        try {
            traerinfo(79);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j79MousePressed

    private void j80MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j80MousePressed

        try {
            traerinfo(80);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j80MousePressed

    private void j81MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j81MousePressed

        try {
            traerinfo(81);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j81MousePressed

    private void j82MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j82MousePressed

        try {
            traerinfo(82);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j82MousePressed

    private void j83MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j83MousePressed

        try {
            traerinfo(83);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j83MousePressed

    private void j84MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j84MousePressed

        try {
            traerinfo(84);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j84MousePressed

    private void j85MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j85MousePressed

        try {
            traerinfo(85);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j85MousePressed

    private void j86MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j86MousePressed

        try {
            traerinfo(86);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j86MousePressed

    private void j87MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j87MousePressed

        try {
            traerinfo(87);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j87MousePressed

    private void j88MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j88MousePressed

        try {
            traerinfo(88);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j88MousePressed

    private void j89MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j89MousePressed

        try {
            traerinfo(89);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j89MousePressed

    private void j90MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j90MousePressed

        try {
            traerinfo(90);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j90MousePressed

    private void j91MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j91MousePressed

        try {
            traerinfo(91);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j91MousePressed

    private void j92MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j92MousePressed

        try {
            traerinfo(92);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j92MousePressed

    private void j93MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j93MousePressed

        try {
            traerinfo(93);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j93MousePressed

    private void j94MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j94MousePressed

        try {
            traerinfo(94);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j94MousePressed

    private void j95MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j95MousePressed

        try {
            traerinfo(95);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j95MousePressed

    private void j96MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j96MousePressed

        try {
            traerinfo(96);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j96MousePressed

    private void j97MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j97MousePressed

        try {
            traerinfo(97);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j97MousePressed

    private void j98MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j98MousePressed

        try {
            traerinfo(98);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j98MousePressed

    private void j99MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j99MousePressed

        try {
            traerinfo(99);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j99MousePressed

    private void j100MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j100MousePressed

        try {
            traerinfo(100);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j100MousePressed

    private void j101MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j101MousePressed

        try {
            traerinfo(101);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j101MousePressed

    private void j102MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j102MousePressed

        try {
            traerinfo(102);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j102MousePressed

    private void j103MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j103MousePressed

        try {
            traerinfo(103);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j103MousePressed

    private void j104MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j104MousePressed

        try {
            traerinfo(104);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j104MousePressed

    private void j105MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j105MousePressed

        try {
            traerinfo(105);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j105MousePressed

    private void j106MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j106MousePressed

        try {
            traerinfo(106);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j106MousePressed

    private void j107MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j107MousePressed

        try {
            traerinfo(107);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j107MousePressed

    private void j108MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j108MousePressed

        try {
            traerinfo(108);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j108MousePressed

    private void j109MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j109MousePressed

        try {
            traerinfo(109);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j109MousePressed

    private void j110MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j110MousePressed

        try {
            traerinfo(110);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j110MousePressed

    private void j111MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j111MousePressed

        try {
            traerinfo(111);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j111MousePressed

    private void j112MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j112MousePressed

        try {
            traerinfo(112);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j112MousePressed

    private void j113MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j113MousePressed

        try {
            traerinfo(113);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j113MousePressed

    private void j114MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j114MousePressed

        try {
            traerinfo(114);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j114MousePressed

    private void j115MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j115MousePressed

        try {
            traerinfo(115);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j115MousePressed

    private void j116MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j116MousePressed

        try {
            traerinfo(116);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j116MousePressed

    private void j117MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j117MousePressed

        try {
            traerinfo(117);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j117MousePressed

    private void j118MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j118MousePressed

        try {
            traerinfo(118);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j118MousePressed

    private void j119MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j119MousePressed

        try {
            traerinfo(119);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j119MousePressed

    private void j120MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j120MousePressed

        try {
            traerinfo(120);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j120MousePressed

    private void j121MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j121MousePressed

        try {
            traerinfo(121);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j121MousePressed

    private void j122MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j122MousePressed

        try {
            traerinfo(122);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j122MousePressed

    private void j123MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j123MousePressed

        try {
            traerinfo(123);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j123MousePressed

    private void j124MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j124MousePressed

        try {
            traerinfo(124);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j124MousePressed

    private void j125MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j125MousePressed

        try {
            traerinfo(125);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j125MousePressed

    private void j126MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j126MousePressed

        try {
            traerinfo(126);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j126MousePressed

    private void j127MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j127MousePressed

        try {
            traerinfo(127);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j127MousePressed

    private void j128MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j128MousePressed

        try {
            traerinfo(128);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j128MousePressed

    private void boton_vMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_vMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_vMousePressed

    private void boton_rMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_rMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_rMousePressed

    private void jPanel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseEntered
        /*
        try {
            int i = 1;
            while (i <= 50) {
                BuscarParsela(Integer.toString(i));
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Ocupacion.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }//GEN-LAST:event_jPanel22MouseEntered
    public void eliminarvehiculo() throws SQLException {
        int fila = tabla_vehiculo.getSelectedRow();
        String valor = tabla_vehiculo.getValueAt(fila, 1).toString();
        JFrame jFrame = new JFrame();
        int result = JOptionPane.showConfirmDialog(jFrame, "Este es el vehiculo que desea eliminar");
        if (result == 0) {
            int control = modelo.eliminarvehiculo(valor);
            if (control == 1) {
                Component rootPane = null;

                JOptionPane.showMessageDialog(rootPane, "Se elimino correctamente.");
                BusquedaVehiculos();

            } else {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "Ocurrio un problema, vuelva a intentar");
            }

        }
    }
    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        try {
            eliminarvehiculo();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel34MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel34MouseReleased

    private void tabla_vehiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_vehiculoKeyPressed
        try {
            eliminarvehiculo();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tabla_vehiculoKeyPressed
    public void imprimirtiketdia(String categoria, float importe) {
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));

        String hora_actual = hora + ":" + minutos;
        String amongUsComoCadena = "000001111000\n000010000100\n000100011110\n000100100001\n011100100001\n010100100001\n010100100001\n010100011110\n010100000010\n011100000010\n000100111010\n000100101010\n000111101110\n000000000000\n000000000000\n000000000000\n111010101110\n100010101000\n111010101110\n001010100010\n111011101110\n000000000000\n000000000000\n000000000000";
        // Aquí tu serial en caso de tener uno

        final String serial = "0006-00000001";

        ConectorPluginV3 tiket = new ConectorPluginV3(ConectorPluginV3.URL_PLUGIN_POR_DEFECTO, serial);

        tiket.Iniciar()
                .Corte(1)
                .DeshabilitarElModoDeCaracteresChinos()
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .CargarImagenLocalEImprimir("C:\\Users\\lauti\\OneDrive\\Escritorio\\SIGA\\src\\com\\images\\floridalogo.png", 0, 216)
                .Feed(1)
                .EscribirTexto("SAEBU\n")
                .EscribirTexto("Camping Universitario\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Serial: " + serial + "\nCajero: " + Login.usuario + "\n")
                .EscribirTexto("Fecha y hora: " + Main.DiaActual + " " + hora_actual + "hs")
                .Feed(1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_IZQUIERDA)
                .EscribirTexto("______________________________________________\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Categoria: " + categoria + "\n" + "Hora-ingreso: " + hora_actual + "\n" + "Solo por el dia\n")
                .EscribirTexto("______________________________________________\n")
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_DERECHA)
                .EscribirTexto("Sub total: $" + importe + "\n")
                .EscribirTexto("_____________________________________________\n")
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_DERECHA)
                .EscribirTexto("TOTAL:  $" + importe + "\n")
                .EstablecerEnfatizado(true)
                .EstablecerTamanoFuente(1, 1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .TextoSegunPaginaDeCodigos(2, "cp850", "¡Bienvenido, que tenga un buen dia!\nEscanee el qr y visite la web de SAEBU")
                .Feed(1)
                .ImprimirCodigoQr("http://saebu.unsl.edu.ar/camping-universitario", 160, ConectorPluginV3.RECUPERACION_QR_MEJOR,
                        ConectorPluginV3.TAMANO_IMAGEN_NORMAL)
                .Feed(1)
                .EstablecerTamanoFuente(1, 1)
                .EscribirTexto("Si.G.A.")
                .Feed(3)
                .Corte(1)
                .Pulso(48, 30, 120);

        try {
            System.out.println("hola");
            tiket.imprimirEn("impresora termica");

            System.out.println("Impreso correctamente");
        } catch (Exception e) {
            System.out.println("Error imprimiendo: " + e.getMessage());
        }
    }

    public void imprimirtiketacampante(String fecha_ingreso, String fecha_egreso, String categoria, float importe) {
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));

        String hora_actual = hora + ":" + minutos;
        String amongUsComoCadena = "000001111000\n000010000100\n000100011110\n000100100001\n011100100001\n010100100001\n010100100001\n010100011110\n010100000010\n011100000010\n000100111010\n000100101010\n000111101110\n000000000000\n000000000000\n000000000000\n111010101110\n100010101000\n111010101110\n001010100010\n111011101110\n000000000000\n000000000000\n000000000000";
        // Aquí tu serial en caso de tener uno

        final String serial = "0006-00000001";

        ConectorPluginV3 tiket = new ConectorPluginV3(ConectorPluginV3.URL_PLUGIN_POR_DEFECTO, serial);

        tiket.Iniciar()
                .Corte(1)
                .DeshabilitarElModoDeCaracteresChinos()
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .CargarImagenLocalEImprimir("C:\\Users\\lauti\\OneDrive\\Escritorio\\SIGA\\src\\com\\images\\floridalogo.png", 0, 216)
                .Feed(1)
                .EscribirTexto("SAEBU\n")
                .EscribirTexto("Camping Universitario\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Serial: " + serial + "\nCajero: " + Login.usuario + "\n")
                .EscribirTexto("Fecha y hora: " + Main.DiaActual + " " + hora_actual + "hs")
                .Feed(1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_IZQUIERDA)
                .EscribirTexto("______________________________________________\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Categoria:" + categoria + "\n" + "Fecha-ingreso: " + fecha_ingreso + "\n" + "Fecha-egreso: " + fecha_egreso + "\n")
                .EscribirTexto("______________________________________________\n")
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_DERECHA)
                .EscribirTexto("Sub total: $" + importe + "\n")
                .EscribirTexto("______________________________________________\n")
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_DERECHA)
                .EscribirTexto("TOTAL:  $" + importe + "\n")
                .EstablecerEnfatizado(true)
                .EstablecerTamanoFuente(1, 1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .TextoSegunPaginaDeCodigos(2, "cp850", "¡Bienvenido, que tenga un buen dia!\nEscanee el qr y visite la web de SAEBU")
                .Feed(1)
                .ImprimirCodigoQr("http://saebu.unsl.edu.ar/camping-universitario", 160, ConectorPluginV3.RECUPERACION_QR_MEJOR,
                        ConectorPluginV3.TAMANO_IMAGEN_NORMAL)
                .Feed(1)
                .EstablecerTamanoFuente(1, 1)
                .EscribirTexto("Si.G.A.")
                .Feed(3)
                .Corte(1)
                .Pulso(48, 30, 120);

        try {
            System.out.println("hola");
            tiket.imprimirEn("impresora termica");

            System.out.println("Impreso correctamente");
        } catch (Exception e) {
            System.out.println("Error imprimiendo: " + e.getMessage());
        }
    }
    private void documento_aKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_documento_aKeyPressed

    }//GEN-LAST:event_documento_aKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Boton_ingreso;
    private javax.swing.JLabel Boton_ingreso_a;
    private javax.swing.JLabel Boton_ingreso_p;
    private javax.swing.JLabel Buscar;
    private javax.swing.JLabel Buscar_a;
    private javax.swing.JTextField Documento;
    private javax.swing.JTextField Documento_a;
    private javax.swing.JButton Obtener;
    private javax.swing.JButton Obtener1;
    private javax.swing.JButton Obtener2;
    private javax.swing.JTextField Parcela;
    private javax.swing.JTextField Parsela_a;
    private javax.swing.JTextField Parsela_p;
    private javax.swing.JTextField apellido_a;
    private javax.swing.JTextField apellido_e;
    private javax.swing.JLabel boton_r;
    private javax.swing.JLabel boton_v;
    private javax.swing.JTextField carrera_e;
    private javax.swing.JTextField cod_aportante;
    private javax.swing.JTextField dni_buscado;
    private javax.swing.JTextField documento_a;
    private javax.swing.JTextField documento_e;
    private javax.swing.JTextField documento_p;
    private javax.swing.JTextField facultad_e;
    private com.toedter.calendar.JDateChooser fecha_egreso;
    private com.toedter.calendar.JDateChooser fecha_egreso_p;
    private com.toedter.calendar.JDateChooser fecha_egreso_p1;
    private com.toedter.calendar.JDateChooser fecha_ingreso;
    private com.toedter.calendar.JDateChooser fecha_ingreso_p;
    private com.toedter.calendar.JDateChooser fecha_ingreso_p1;
    private javax.swing.JLabel ingreso_vehiculo;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j10;
    private javax.swing.JLabel j100;
    private javax.swing.JLabel j101;
    private javax.swing.JLabel j102;
    private javax.swing.JLabel j103;
    private javax.swing.JLabel j104;
    private javax.swing.JLabel j105;
    private javax.swing.JLabel j106;
    private javax.swing.JLabel j107;
    private javax.swing.JLabel j108;
    private javax.swing.JLabel j109;
    private javax.swing.JLabel j11;
    private javax.swing.JLabel j110;
    private javax.swing.JLabel j111;
    private javax.swing.JLabel j112;
    private javax.swing.JLabel j113;
    private javax.swing.JLabel j114;
    private javax.swing.JLabel j115;
    private javax.swing.JLabel j116;
    private javax.swing.JLabel j117;
    private javax.swing.JLabel j118;
    private javax.swing.JLabel j119;
    private javax.swing.JLabel j12;
    private javax.swing.JLabel j120;
    private javax.swing.JLabel j121;
    private javax.swing.JLabel j122;
    private javax.swing.JLabel j123;
    private javax.swing.JLabel j124;
    private javax.swing.JLabel j125;
    private javax.swing.JLabel j126;
    private javax.swing.JLabel j127;
    private javax.swing.JLabel j128;
    private javax.swing.JLabel j13;
    private javax.swing.JLabel j14;
    private javax.swing.JLabel j15;
    private javax.swing.JLabel j16;
    private javax.swing.JLabel j17;
    private javax.swing.JLabel j18;
    private javax.swing.JLabel j19;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j20;
    private javax.swing.JLabel j21;
    private javax.swing.JLabel j22;
    private javax.swing.JLabel j23;
    private javax.swing.JLabel j24;
    private javax.swing.JLabel j25;
    private javax.swing.JLabel j26;
    private javax.swing.JLabel j27;
    private javax.swing.JLabel j28;
    private javax.swing.JLabel j29;
    private javax.swing.JLabel j3;
    private javax.swing.JLabel j30;
    private javax.swing.JLabel j31;
    private javax.swing.JLabel j32;
    private javax.swing.JLabel j33;
    private javax.swing.JLabel j34;
    private javax.swing.JLabel j35;
    private javax.swing.JLabel j36;
    private javax.swing.JLabel j37;
    private javax.swing.JLabel j38;
    private javax.swing.JLabel j39;
    private javax.swing.JLabel j4;
    private javax.swing.JLabel j40;
    private javax.swing.JLabel j41;
    private javax.swing.JLabel j42;
    private javax.swing.JLabel j43;
    private javax.swing.JLabel j44;
    private javax.swing.JLabel j45;
    private javax.swing.JLabel j46;
    private javax.swing.JLabel j47;
    private javax.swing.JLabel j48;
    private javax.swing.JLabel j49;
    private javax.swing.JLabel j5;
    private javax.swing.JLabel j50;
    private javax.swing.JLabel j51;
    private javax.swing.JLabel j52;
    private javax.swing.JLabel j53;
    private javax.swing.JLabel j54;
    private javax.swing.JLabel j55;
    private javax.swing.JLabel j56;
    private javax.swing.JLabel j57;
    private javax.swing.JLabel j58;
    private javax.swing.JLabel j59;
    private javax.swing.JLabel j6;
    private javax.swing.JLabel j60;
    private javax.swing.JLabel j61;
    private javax.swing.JLabel j62;
    private javax.swing.JLabel j63;
    private javax.swing.JLabel j64;
    private javax.swing.JLabel j65;
    private javax.swing.JLabel j66;
    private javax.swing.JLabel j67;
    private javax.swing.JLabel j68;
    private javax.swing.JLabel j69;
    private javax.swing.JLabel j7;
    private javax.swing.JLabel j70;
    private javax.swing.JLabel j71;
    private javax.swing.JLabel j72;
    private javax.swing.JLabel j73;
    private javax.swing.JLabel j74;
    private javax.swing.JLabel j75;
    private javax.swing.JLabel j76;
    private javax.swing.JLabel j77;
    private javax.swing.JLabel j78;
    private javax.swing.JLabel j79;
    private javax.swing.JLabel j8;
    private javax.swing.JLabel j80;
    private javax.swing.JLabel j81;
    private javax.swing.JLabel j82;
    private javax.swing.JLabel j83;
    private javax.swing.JLabel j84;
    private javax.swing.JLabel j85;
    private javax.swing.JLabel j86;
    private javax.swing.JLabel j87;
    private javax.swing.JLabel j88;
    private javax.swing.JLabel j89;
    private javax.swing.JLabel j9;
    private javax.swing.JLabel j90;
    private javax.swing.JLabel j91;
    private javax.swing.JLabel j92;
    private javax.swing.JLabel j93;
    private javax.swing.JLabel j94;
    private javax.swing.JLabel j95;
    private javax.swing.JLabel j96;
    private javax.swing.JLabel j97;
    private javax.swing.JLabel j98;
    private javax.swing.JLabel j99;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField nombre_a;
    private javax.swing.JTextField nombre_e;
    private javax.swing.JTextField nombre_p;
    private javax.swing.JCheckBox pasar_dia;
    private javax.swing.JCheckBox pasar_dia1;
    private javax.swing.JCheckBox pasar_dia2;
    private javax.swing.JTextField patente;
    private javax.swing.JTable tabla_egreso;
    private javax.swing.JTable tabla_vehiculo;
    private javax.swing.JTextField tarifa;
    private javax.swing.JTextField tarifa1;
    private javax.swing.JTextField tarifa2;
    private javax.swing.JTabbedPane vehiculos;
    // End of variables declaration//GEN-END:variables

}
