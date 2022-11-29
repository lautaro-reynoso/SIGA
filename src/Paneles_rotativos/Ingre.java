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
import java.util.ArrayList;

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

        Parsela_p.setText("Parcela");

    }

    public void setearnullinvitado() {
        documento_p1.setText("");
        nombre_p1.setText("");

        Parsela_p1.setText("Parcela");
    }

    public void setearnullaportante() {
        documento_a.setText("");
        nombre_a.setText("");

        Parsela_a.setText("Parcela");
        cod_aportante.setText("");
        apellido_a.setText("");

    }

    public void setearnullalumno() {
        documento_e.setText("");
        nombre_e.setText("");

        Parcela.setText("Parcela");
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

            float importe = 0;
            int c;
            if (Integer.parseInt(Parsela_p.getText()) >= 1 && Integer.parseInt(Parsela_p.getText()) <= 4) {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 6);
                tarifa1.setText(String.valueOf(importe));
            } else {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
                tarifa1.setText(String.valueOf(importe));
            }

            if (fecha_egreso_p.getDate() != null && fecha_ingreso_p.getDate() != null) {

                c = controlador.IngresoParticular(documento_p.getText(), nombre_p.getText(), "Particular", calc_fecha(fecha_ingreso_p), calc_fecha(fecha_egreso_p), Parsela_p.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                    String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
                    String segundos = String.valueOf(calendario.get(Calendar.SECOND));

                    String hora_actual = hora + ":" + minutos;
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo particular acampante", Main.DiaActual, hora_actual);
                    modelo.insertardinerocaja(importe);

                    Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                    if (result == 0) {

                        if (!pasar_dia2.isSelected()) {

                            imprimirtiketacampante(calc_fecha(fecha_ingreso_p), calc_fecha(fecha_egreso_p), "Particular", importe, nombre_p.getText(), documento_p.getText());
                        } else {

                            importe = Main.tarifa_dia_particular;
                            imprimirtiketdia("Particular", importe, nombre_p.getText(), documento_p.getText());
                        }
                    }
                    setearnullparticular();

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
            float importe = 0;

            if (Integer.parseInt(Parsela_a.getText()) >= 1 && Integer.parseInt(Parsela_a.getText()) <= 4) {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 5);
                tarifa2.setText(String.valueOf(importe));
            } else {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
                tarifa2.setText(String.valueOf(importe));
            }
            int c;
            if (fecha_egreso_p1.getDate() != null && fecha_ingreso_p1.getDate() != null) {

                c = controlador.IngresoParticular(documento_a.getText(), nombre_a.getText(), "Aportante", calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), Parsela_a.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                    String minutos = String.valueOf(calendario.get(Calendar.MINUTE));

                    String hora_actual = hora + ":" + minutos;
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo aportante acampante", Main.DiaActual, hora_actual);
                    if (!familiares.getText().equals("0")) {
                        modelo.InsertarRegistro(Login.usuario, "ha ingresado " + familiares.getText() + " familiares de aportantes acampar", Main.DiaActual, hora_actual);
                    }
                    modelo.insertardinerocaja(importe);
                    Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                    if (result == 0) {

                        if (!pasar_dia1.isSelected()) {

                            imprimirtiketacampante(calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), "Aportante", importe, nombre_a.getText(), documento_a.getText());
                        } else {

                            importe = Main.tarifa_dia_aportantes;
                            imprimirtiketdia("Aportante", importe, nombre_a.getText(), documento_a.getText());
                        }
                    }
                    familiares.setText("0");
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
        float importe = 0;
        int c;
        if (Integer.parseInt(Parcela.getText()) >= 1 && Integer.parseInt(Parcela.getText()) <= 4) {
            importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 6);
            tarifa.setText(String.valueOf(importe));
        } else {
            importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);
            tarifa.setText(String.valueOf(importe));
        }

        if (fecha_egreso.getDate() != null && fecha_ingreso.getDate() != null) {

            c = controlador.IngresoParticular(documento_e.getText(), nombre_e.getText(), "Alumno", calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), Parcela.getText(), importe);

            if (c != 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
                String segundos = String.valueOf(calendario.get(Calendar.SECOND));

                String hora_actual = hora + ":" + minutos;
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo alumno acampante", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(importe);
                
                Component jFrame = null;
                int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                if (result == 0) {

                    if (!pasar_dia.isSelected()) {

                        imprimirtiketacampante(calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), "Alumno", importe, nombre_e.getText(), documento_e.getText());
                    } else {

                        importe = Main.tarifa_dia_alumnos;
                        imprimirtiketdia("Alumno", importe, nombre_e.getText(), documento_e.getText());

                    }
                }
                setearnullalumno();
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
        menu_ingreso_egreso = new javax.swing.JTabbedPane();
        aportantes = new javax.swing.JPanel();
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
        jLabel9 = new javax.swing.JLabel();
        familiares = new javax.swing.JTextField();
        alumnoss = new javax.swing.JPanel();
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
        particular = new javax.swing.JPanel();
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
        invitados = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        documento_p1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        nombre_p1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        fecha_ingreso_p2 = new com.toedter.calendar.JDateChooser();
        fecha_egreso_p2 = new com.toedter.calendar.JDateChooser();
        Obtener3 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        tarifa3 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        Boton_ingreso_p1 = new javax.swing.JLabel();
        Parsela_p1 = new javax.swing.JTextField();
        pasar_dia3 = new javax.swing.JCheckBox();
        jLabel44 = new javax.swing.JLabel();
        egresos = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        dni_buscado = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_egreso = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        vahiculos = new javax.swing.JPanel();
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
        boton_v = new javax.swing.JLabel();
        boton_r = new javax.swing.JLabel();
        j124 = new javax.swing.JLabel();
        j125 = new javax.swing.JLabel();
        j126 = new javax.swing.JLabel();
        j127 = new javax.swing.JLabel();
        j128 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        menu_ingreso_egreso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menu_ingreso_egresoMousePressed(evt);
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
        cod_aportante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cod_aportanteKeyPressed(evt);
            }
        });

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
                .addContainerGap(46, Short.MAX_VALUE))
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

        tarifa2.setEditable(false);
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

        jLabel9.setText("Especifique los familiares:");

        familiares.setText("0");
        familiares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                familiaresKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGap(65, 65, 65))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pasar_dia1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(familiares)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(pasar_dia1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(familiares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aportantesLayout = new javax.swing.GroupLayout(aportantes);
        aportantes.setLayout(aportantesLayout);
        aportantesLayout.setHorizontalGroup(
            aportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aportantesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aportantesLayout.setVerticalGroup(
            aportantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aportantesLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("APORTANTES", aportantes);

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

        tarifa.setEditable(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
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
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(pasar_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(pasar_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout alumnossLayout = new javax.swing.GroupLayout(alumnoss);
        alumnoss.setLayout(alumnossLayout);
        alumnossLayout.setHorizontalGroup(
            alumnossLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alumnossLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        alumnossLayout.setVerticalGroup(
            alumnossLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alumnossLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("ALUMNOS", alumnoss);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar un Particular"));

        documento_p.setForeground(new java.awt.Color(0, 0, 0));
        documento_p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_p.setText(null);
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
        nombre_p.setText(null);
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

        tarifa1.setEditable(false);
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

        javax.swing.GroupLayout particularLayout = new javax.swing.GroupLayout(particular);
        particular.setLayout(particularLayout);
        particularLayout.setHorizontalGroup(
            particularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(particularLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        particularLayout.setVerticalGroup(
            particularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(particularLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("PARTICULAR", particular);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar un Invitado"));

        documento_p1.setForeground(new java.awt.Color(0, 0, 0));
        documento_p1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_p1.setText(null);
        documento_e.setEnabled(false);
        documento_p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_p1ActionPerformed(evt);
            }
        });
        documento_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                documento_p1KeyPressed(evt);
            }
        });

        jLabel38.setText("NOMBRE");

        nombre_p1.setForeground(new java.awt.Color(0, 0, 0));
        nombre_p1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_p1.setText(null);
        nombre_e.setEnabled(false);
        nombre_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombre_p1KeyPressed(evt);
            }
        });

        jLabel39.setText("DOCUMENTO");

        jLabel40.setText("FECHA DE EGRESO");

        jLabel41.setText("FECHA DE INGRESO");

        fecha_ingreso_p2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_ingreso_p2KeyPressed(evt);
            }
        });

        fecha_egreso_p2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecha_egreso_p2KeyPressed(evt);
            }
        });

        Obtener3.setText("Obtener Total");
        Obtener3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Obtener3MousePressed(evt);
            }
        });

        jPanel23.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel23ComponentHidden(evt);
            }
        });

        jLabel42.setText("Total");

        tarifa3.setEditable(false);
        tarifa3.setText("Tarifa");
        tarifa.setEnabled(false);
        tarifa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifa3ActionPerformed(evt);
            }
        });

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_attach_money_black_24dp.png"))); // NOI18N

        Boton_ingreso_p1.setText(" REGISTRAR INGRESO ");
        Boton_ingreso_p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Boton_ingreso_p1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Boton_ingreso_p1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Boton_ingreso_p1MousePressed(evt);
            }
        });
        Boton_ingreso_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Boton_ingreso_p1KeyPressed(evt);
            }
        });

        Parsela_p1.setText("Parcela");
        Parsela_p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Parsela_p1ActionPerformed(evt);
            }
        });
        Parsela_p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Parsela_p1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Parsela_p1)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Boton_ingreso_p1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(tarifa3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Parsela_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addGap(2, 2, 2)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(tarifa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Boton_ingreso_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Obtener3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fecha_egreso_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fecha_ingreso_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel41))
                        .addGap(75, 75, 75))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingreso_p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecha_egreso_p2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener3))
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pasar_dia3.setText("Pasar el dia");
        pasar_dia3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pasar_dia3MousePressed(evt);
            }
        });
        pasar_dia3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasar_dia3ActionPerformed(evt);
            }
        });
        pasar_dia3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasar_dia3KeyPressed(evt);
            }
        });

        jLabel44.setText("Selecione si solo pasa el dia:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(documento_p1)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(367, 367, 367))
                            .addComponent(nombre_p1)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pasar_dia3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documento_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre_p1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasar_dia3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(43, 43, 43)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout invitadosLayout = new javax.swing.GroupLayout(invitados);
        invitados.setLayout(invitadosLayout);
        invitadosLayout.setHorizontalGroup(
            invitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invitadosLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        invitadosLayout.setVerticalGroup(
            invitadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invitadosLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("INVITADOS", invitados);

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
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/baseline_search_black_24dp.png"))); // NOI18N
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
                    .addComponent(jLabel32)
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
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(361, 361, 361)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout egresosLayout = new javax.swing.GroupLayout(egresos);
        egresos.setLayout(egresosLayout);
        egresosLayout.setHorizontalGroup(
            egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(egresosLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, egresosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(589, 589, 589))
        );
        egresosLayout.setVerticalGroup(
            egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(egresosLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("EGRESOS", egresos);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout vahiculosLayout = new javax.swing.GroupLayout(vahiculos);
        vahiculos.setLayout(vahiculosLayout);
        vahiculosLayout.setHorizontalGroup(
            vahiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vahiculosLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(vahiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vahiculosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        vahiculosLayout.setVerticalGroup(
            vahiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vahiculosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        menu_ingreso_egreso.addTab("VEHICULOS", vahiculos);

        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel22MouseEntered(evt);
            }
        });
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        j10.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j10.setForeground(new java.awt.Color(255, 255, 255));
        j10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j10.setText("10");
        j10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j10MousePressed(evt);
            }
        });
        jPanel22.add(j10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, -1, -1));

        j12.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j12.setForeground(new java.awt.Color(255, 255, 255));
        j12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j12.setText("12");
        j12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j12MousePressed(evt);
            }
        });
        jPanel22.add(j12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, -1, -1));

        j5.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j5.setForeground(new java.awt.Color(255, 255, 255));
        j5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j5.setText("5");
        j5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j5MousePressed(evt);
            }
        });
        jPanel22.add(j5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, -1, -1));

        j27.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j27.setForeground(new java.awt.Color(255, 255, 255));
        j27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j27.setText("27");
        j27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j27MousePressed(evt);
            }
        });
        jPanel22.add(j27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, -1));

        j7.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j7.setForeground(new java.awt.Color(255, 255, 255));
        j7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j7.setText("7");
        j7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j7MousePressed(evt);
            }
        });
        jPanel22.add(j7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, -1, -1));

        j29.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j29.setForeground(new java.awt.Color(255, 255, 255));
        j29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j29.setText("29");
        j29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j29MousePressed(evt);
            }
        });
        jPanel22.add(j29, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, -1, -1));

        j20.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j20.setForeground(new java.awt.Color(255, 255, 255));
        j20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j20.setText("20");
        j20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j20MousePressed(evt);
            }
        });
        jPanel22.add(j20, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, -1));

        j3.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j3.setForeground(new java.awt.Color(255, 255, 255));
        j3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j3.setText("3");
        j3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j3MousePressed(evt);
            }
        });
        jPanel22.add(j3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 670, -1, -1));

        j31.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j31.setForeground(new java.awt.Color(255, 255, 255));
        j31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j31.setText("31");
        j31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j31MousePressed(evt);
            }
        });
        jPanel22.add(j31, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, -1, -1));

        j40.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j40.setForeground(new java.awt.Color(255, 255, 255));
        j40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j40.setText("40");
        j40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j40MousePressed(evt);
            }
        });
        jPanel22.add(j40, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, -1, -1));

        j49.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j49.setForeground(new java.awt.Color(255, 255, 255));
        j49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j49.setText("49");
        j49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j49MousePressed(evt);
            }
        });
        jPanel22.add(j49, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, -1, -1));

        j45.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j45.setForeground(new java.awt.Color(255, 255, 255));
        j45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j45.setText("45");
        j45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j45MousePressed(evt);
            }
        });
        jPanel22.add(j45, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        j50.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j50.setForeground(new java.awt.Color(255, 255, 255));
        j50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j50.setText("50");
        j50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j50MousePressed(evt);
            }
        });
        jPanel22.add(j50, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, -1, -1));

        j6.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j6.setForeground(new java.awt.Color(255, 255, 255));
        j6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j6.setText("6");
        j6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j6MousePressed(evt);
            }
        });
        jPanel22.add(j6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, -1, -1));

        j15.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j15.setForeground(new java.awt.Color(255, 255, 255));
        j15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j15.setText("15");
        j15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j15MousePressed(evt);
            }
        });
        jPanel22.add(j15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, -1, -1));

        j2.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j2.setForeground(new java.awt.Color(255, 255, 255));
        j2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j2.setText("2");
        j2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        j2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j2MousePressed(evt);
            }
        });
        jPanel22.add(j2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 660, -1, -1));

        j25.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j25.setForeground(new java.awt.Color(255, 255, 255));
        j25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j25.setText("25");
        j25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j25MousePressed(evt);
            }
        });
        jPanel22.add(j25, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, -1, -1));

        j33.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j33.setForeground(new java.awt.Color(255, 255, 255));
        j33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j33.setText("33");
        j33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j33MousePressed(evt);
            }
        });
        jPanel22.add(j33, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        j37.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j37.setForeground(new java.awt.Color(255, 255, 255));
        j37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j37.setText("37");
        j37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j37MousePressed(evt);
            }
        });
        jPanel22.add(j37, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, -1));

        j36.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j36.setForeground(new java.awt.Color(255, 255, 255));
        j36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j36.setText("36");
        j36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j36MousePressed(evt);
            }
        });
        jPanel22.add(j36, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        j48.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j48.setForeground(new java.awt.Color(255, 255, 255));
        j48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j48.setText("48");
        j48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j48MousePressed(evt);
            }
        });
        jPanel22.add(j48, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, -1, -1));

        j39.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j39.setForeground(new java.awt.Color(255, 255, 255));
        j39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j39.setText("39");
        j39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j39MousePressed(evt);
            }
        });
        jPanel22.add(j39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        j38.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j38.setForeground(new java.awt.Color(255, 255, 255));
        j38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j38.setText("38");
        j38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j38MousePressed(evt);
            }
        });
        jPanel22.add(j38, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        j34.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j34.setForeground(new java.awt.Color(255, 255, 255));
        j34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j34.setText("34");
        j34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j34MousePressed(evt);
            }
        });
        jPanel22.add(j34, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        j43.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j43.setForeground(new java.awt.Color(255, 255, 255));
        j43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j43.setText("43");
        j43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j43MousePressed(evt);
            }
        });
        jPanel22.add(j43, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));

        j30.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j30.setForeground(new java.awt.Color(255, 255, 255));
        j30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j30.setText("30");
        j30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j30MousePressed(evt);
            }
        });
        jPanel22.add(j30, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, -1, -1));

        j42.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j42.setForeground(new java.awt.Color(255, 255, 255));
        j42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j42.setText("42");
        j42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j42MousePressed(evt);
            }
        });
        jPanel22.add(j42, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, -1, -1));

        j24.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j24.setForeground(new java.awt.Color(255, 255, 255));
        j24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j24.setText("24");
        j24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j24MousePressed(evt);
            }
        });
        jPanel22.add(j24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        j23.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j23.setForeground(new java.awt.Color(255, 255, 255));
        j23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j23.setText("23");
        j23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j23MousePressed(evt);
            }
        });
        jPanel22.add(j23, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, -1, 10));

        j21.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j21.setForeground(new java.awt.Color(255, 255, 255));
        j21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j21.setText("21");
        j21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j21MousePressed(evt);
            }
        });
        jPanel22.add(j21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, -1, -1));

        j19.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j19.setForeground(new java.awt.Color(255, 255, 255));
        j19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j19.setText("19");
        j19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j19MousePressed(evt);
            }
        });
        jPanel22.add(j19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));

        j18.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j18.setForeground(new java.awt.Color(255, 255, 255));
        j18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j18.setText("18");
        j18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j18MousePressed(evt);
            }
        });
        jPanel22.add(j18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, -1));

        j4.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j4.setForeground(new java.awt.Color(255, 255, 255));
        j4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j4.setText("4");
        j4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j4MousePressed(evt);
            }
        });
        jPanel22.add(j4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 680, -1, -1));

        j16.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j16.setForeground(new java.awt.Color(255, 255, 255));
        j16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j16.setText("16");
        j16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j16MousePressed(evt);
            }
        });
        jPanel22.add(j16, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, -1, -1));

        j17.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j17.setForeground(new java.awt.Color(255, 255, 255));
        j17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j17.setText("17");
        j17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j17MousePressed(evt);
            }
        });
        jPanel22.add(j17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, -1, -1));

        j14.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j14.setForeground(new java.awt.Color(255, 255, 255));
        j14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j14.setText("14");
        j14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j14MousePressed(evt);
            }
        });
        jPanel22.add(j14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, -1, -1));

        j13.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j13.setForeground(new java.awt.Color(255, 255, 255));
        j13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j13.setText("13");
        j13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j13MousePressed(evt);
            }
        });
        jPanel22.add(j13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, -1, -1));

        j8.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j8.setForeground(new java.awt.Color(255, 255, 255));
        j8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j8.setText("8");
        j8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j8MousePressed(evt);
            }
        });
        jPanel22.add(j8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, -1, -1));

        j9.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j9.setForeground(new java.awt.Color(255, 255, 255));
        j9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j9.setText("9");
        j9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j9MousePressed(evt);
            }
        });
        jPanel22.add(j9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, -1, -1));

        j11.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j11.setForeground(new java.awt.Color(255, 255, 255));
        j11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j11.setText("11");
        j11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j11MousePressed(evt);
            }
        });
        jPanel22.add(j11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, -1, -1));

        j46.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j46.setForeground(new java.awt.Color(255, 255, 255));
        j46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j46.setText("46");
        j46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j46MousePressed(evt);
            }
        });
        jPanel22.add(j46, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, -1));

        j35.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j35.setForeground(new java.awt.Color(255, 255, 255));
        j35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j35.setText("35");
        j35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j35MousePressed(evt);
            }
        });
        jPanel22.add(j35, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, -1, -1));

        j41.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j41.setForeground(new java.awt.Color(255, 255, 255));
        j41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j41.setText("41");
        j41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j41MousePressed(evt);
            }
        });
        jPanel22.add(j41, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, -1, -1));

        j32.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j32.setForeground(new java.awt.Color(255, 255, 255));
        j32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j32.setText("32");
        j32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j32MousePressed(evt);
            }
        });
        jPanel22.add(j32, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, -1, -1));

        j28.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j28.setForeground(new java.awt.Color(255, 255, 255));
        j28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j28.setText("28");
        j28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j28MousePressed(evt);
            }
        });
        jPanel22.add(j28, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, -1));

        j26.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j26.setForeground(new java.awt.Color(255, 255, 255));
        j26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j26.setText("26");
        j26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j26MousePressed(evt);
            }
        });
        jPanel22.add(j26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, -1, -1));

        j1.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j1.setForeground(new java.awt.Color(255, 255, 255));
        j1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j1.setText("1");
        j1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j1MousePressed(evt);
            }
        });
        jPanel22.add(j1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 660, -1, -1));

        j22.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j22.setForeground(new java.awt.Color(255, 255, 255));
        j22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j22.setText("22");
        j22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j22MousePressed(evt);
            }
        });
        jPanel22.add(j22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, -1, -1));

        j44.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j44.setForeground(new java.awt.Color(255, 255, 255));
        j44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j44.setText("44");
        j44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j44MousePressed(evt);
            }
        });
        jPanel22.add(j44, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        j47.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j47.setForeground(new java.awt.Color(255, 255, 255));
        j47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j47.setText("47");
        j47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j47MousePressed(evt);
            }
        });
        jPanel22.add(j47, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, -1, -1));

        j51.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j51.setForeground(new java.awt.Color(255, 255, 255));
        j51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j51.setText("51");
        j51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j51MousePressed(evt);
            }
        });
        jPanel22.add(j51, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        j52.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j52.setForeground(new java.awt.Color(255, 255, 255));
        j52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j52.setText("52");
        j52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j52MousePressed(evt);
            }
        });
        jPanel22.add(j52, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, -1, -1));

        j53.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j53.setForeground(new java.awt.Color(255, 255, 255));
        j53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j53.setText("53");
        j53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j53MousePressed(evt);
            }
        });
        jPanel22.add(j53, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        j54.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j54.setForeground(new java.awt.Color(255, 255, 255));
        j54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j54.setText("54");
        j54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j54MousePressed(evt);
            }
        });
        jPanel22.add(j54, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, -1, -1));

        j55.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j55.setForeground(new java.awt.Color(255, 255, 255));
        j55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j55.setText("55");
        j55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j55MousePressed(evt);
            }
        });
        jPanel22.add(j55, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, -1, -1));

        j56.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j56.setForeground(new java.awt.Color(255, 255, 255));
        j56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j56.setText("56");
        j56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j56MousePressed(evt);
            }
        });
        jPanel22.add(j56, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 280, -1, -1));

        j57.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j57.setForeground(new java.awt.Color(255, 255, 255));
        j57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j57.setText("57");
        j57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j57MousePressed(evt);
            }
        });
        jPanel22.add(j57, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        j58.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j58.setForeground(new java.awt.Color(255, 255, 255));
        j58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j58.setText("58");
        j58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j58MousePressed(evt);
            }
        });
        jPanel22.add(j58, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        j59.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j59.setForeground(new java.awt.Color(255, 255, 255));
        j59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j59.setText("59");
        j59.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j59MousePressed(evt);
            }
        });
        jPanel22.add(j59, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        j60.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j60.setForeground(new java.awt.Color(255, 255, 255));
        j60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j60.setText("60");
        j60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j60MousePressed(evt);
            }
        });
        jPanel22.add(j60, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, -1));

        j61.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j61.setForeground(new java.awt.Color(255, 255, 255));
        j61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j61.setText("61");
        j61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j61MousePressed(evt);
            }
        });
        jPanel22.add(j61, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, -1, -1));

        j62.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j62.setForeground(new java.awt.Color(255, 255, 255));
        j62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j62.setText("62");
        j62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j62MousePressed(evt);
            }
        });
        jPanel22.add(j62, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, -1, -1));

        j63.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j63.setForeground(new java.awt.Color(255, 255, 255));
        j63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j63.setText("63");
        j63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j63MousePressed(evt);
            }
        });
        jPanel22.add(j63, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, -1));

        j64.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j64.setForeground(new java.awt.Color(255, 255, 255));
        j64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j64.setText("64");
        j64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j64MousePressed(evt);
            }
        });
        jPanel22.add(j64, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, -1, -1));

        j65.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j65.setForeground(new java.awt.Color(255, 255, 255));
        j65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j65.setText("65");
        j65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j65MousePressed(evt);
            }
        });
        jPanel22.add(j65, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, -1));

        j66.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j66.setForeground(new java.awt.Color(255, 255, 255));
        j66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j66.setText("66");
        j66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j66MousePressed(evt);
            }
        });
        jPanel22.add(j66, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, -1, -1));

        j67.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j67.setForeground(new java.awt.Color(255, 255, 255));
        j67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j67.setText("67");
        j67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j67MousePressed(evt);
            }
        });
        jPanel22.add(j67, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, -1));

        j68.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j68.setForeground(new java.awt.Color(255, 255, 255));
        j68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j68.setText("68");
        j68.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j68MousePressed(evt);
            }
        });
        jPanel22.add(j68, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, -1, -1));

        j69.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j69.setForeground(new java.awt.Color(255, 255, 255));
        j69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j69.setText("69");
        j69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j69MousePressed(evt);
            }
        });
        jPanel22.add(j69, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, -1, -1));

        j70.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j70.setForeground(new java.awt.Color(255, 255, 255));
        j70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j70.setText("70");
        j70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j70MousePressed(evt);
            }
        });
        jPanel22.add(j70, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 30, -1));

        j71.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j71.setForeground(new java.awt.Color(255, 255, 255));
        j71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j71.setText("71");
        j71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j71MousePressed(evt);
            }
        });
        jPanel22.add(j71, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

        j72.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j72.setForeground(new java.awt.Color(255, 255, 255));
        j72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j72.setText("72");
        j72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j72MousePressed(evt);
            }
        });
        jPanel22.add(j72, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        j73.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j73.setForeground(new java.awt.Color(255, 255, 255));
        j73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j73.setText("73");
        j73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j73MousePressed(evt);
            }
        });
        jPanel22.add(j73, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        j74.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j74.setForeground(new java.awt.Color(255, 255, 255));
        j74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j74.setText("74");
        j74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j74MousePressed(evt);
            }
        });
        jPanel22.add(j74, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, -1, -1));

        j75.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j75.setForeground(new java.awt.Color(255, 255, 255));
        j75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j75.setText("75");
        j75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j75MousePressed(evt);
            }
        });
        jPanel22.add(j75, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, -1, -1));

        j76.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j76.setForeground(new java.awt.Color(255, 255, 255));
        j76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j76.setText("76");
        j76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j76MousePressed(evt);
            }
        });
        jPanel22.add(j76, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, -1, -1));

        j77.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j77.setForeground(new java.awt.Color(255, 255, 255));
        j77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j77.setText("77");
        j77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j77MousePressed(evt);
            }
        });
        jPanel22.add(j77, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, -1, -1));

        j78.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j78.setForeground(new java.awt.Color(255, 255, 255));
        j78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j78.setText("78");
        j78.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j78MousePressed(evt);
            }
        });
        jPanel22.add(j78, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, -1, -1));

        j79.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j79.setForeground(new java.awt.Color(255, 255, 255));
        j79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j79.setText("79");
        j79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j79MousePressed(evt);
            }
        });
        jPanel22.add(j79, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 210, -1, -1));

        j80.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j80.setForeground(new java.awt.Color(255, 255, 255));
        j80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j80.setText("80");
        j80.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j80MousePressed(evt);
            }
        });
        jPanel22.add(j80, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, -1));

        j81.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j81.setForeground(new java.awt.Color(255, 255, 255));
        j81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j81.setText("81");
        j81.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j81MousePressed(evt);
            }
        });
        jPanel22.add(j81, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, -1, -1));

        j82.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j82.setForeground(new java.awt.Color(255, 255, 255));
        j82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j82.setText("82");
        j82.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j82MousePressed(evt);
            }
        });
        jPanel22.add(j82, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        j83.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j83.setForeground(new java.awt.Color(255, 255, 255));
        j83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j83.setText("83");
        j83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j83MousePressed(evt);
            }
        });
        jPanel22.add(j83, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, -1, -1));

        j84.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j84.setForeground(new java.awt.Color(255, 255, 255));
        j84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j84.setText("84");
        j84.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j84MousePressed(evt);
            }
        });
        jPanel22.add(j84, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        j85.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j85.setForeground(new java.awt.Color(255, 255, 255));
        j85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j85.setText("85");
        j85.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j85MousePressed(evt);
            }
        });
        jPanel22.add(j85, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, -1, -1));

        j86.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j86.setForeground(new java.awt.Color(255, 255, 255));
        j86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j86.setText("86");
        j86.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j86MousePressed(evt);
            }
        });
        jPanel22.add(j86, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, -1, -1));

        j87.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j87.setForeground(new java.awt.Color(255, 255, 255));
        j87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j87.setText("87");
        j87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j87MousePressed(evt);
            }
        });
        jPanel22.add(j87, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, -1, -1));

        j88.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j88.setForeground(new java.awt.Color(255, 255, 255));
        j88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j88.setText("88");
        j88.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j88MousePressed(evt);
            }
        });
        jPanel22.add(j88, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, -1, -1));

        j89.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j89.setForeground(new java.awt.Color(255, 255, 255));
        j89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j89.setText("89");
        j89.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j89MousePressed(evt);
            }
        });
        jPanel22.add(j89, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 220, -1, -1));

        j90.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j90.setForeground(new java.awt.Color(255, 255, 255));
        j90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j90.setText("90");
        j90.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j90MousePressed(evt);
            }
        });
        jPanel22.add(j90, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, -1));

        j91.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j91.setForeground(new java.awt.Color(255, 255, 255));
        j91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j91.setText("91");
        j91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j91MousePressed(evt);
            }
        });
        jPanel22.add(j91, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, -1, -1));

        j92.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j92.setForeground(new java.awt.Color(255, 255, 255));
        j92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j92.setText("92");
        j92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j92MousePressed(evt);
            }
        });
        jPanel22.add(j92, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, -1, -1));

        j93.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j93.setForeground(new java.awt.Color(255, 255, 255));
        j93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j93.setText("93");
        j93.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j93MousePressed(evt);
            }
        });
        jPanel22.add(j93, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, -1, -1));

        j94.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j94.setForeground(new java.awt.Color(255, 255, 255));
        j94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j94.setText("94");
        j94.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j94MousePressed(evt);
            }
        });
        jPanel22.add(j94, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        j95.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j95.setForeground(new java.awt.Color(255, 255, 255));
        j95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j95.setText("95");
        j95.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j95MousePressed(evt);
            }
        });
        jPanel22.add(j95, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        j96.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j96.setForeground(new java.awt.Color(255, 255, 255));
        j96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j96.setText("96");
        j96.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j96MousePressed(evt);
            }
        });
        jPanel22.add(j96, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, -1, -1));

        j97.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j97.setForeground(new java.awt.Color(255, 255, 255));
        j97.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j97.setText("97");
        j97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j97MousePressed(evt);
            }
        });
        jPanel22.add(j97, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        j98.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j98.setForeground(new java.awt.Color(255, 255, 255));
        j98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j98.setText("98");
        j98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j98MousePressed(evt);
            }
        });
        jPanel22.add(j98, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        j99.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j99.setForeground(new java.awt.Color(255, 255, 255));
        j99.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j99.setText("99");
        j99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j99MousePressed(evt);
            }
        });
        jPanel22.add(j99, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        j100.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j100.setForeground(new java.awt.Color(255, 255, 255));
        j100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j100.setText("100");
        j100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j100MousePressed(evt);
            }
        });
        jPanel22.add(j100, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        j101.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j101.setForeground(new java.awt.Color(255, 255, 255));
        j101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j101.setText("101");
        j101.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j101MousePressed(evt);
            }
        });
        jPanel22.add(j101, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        j102.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j102.setForeground(new java.awt.Color(255, 255, 255));
        j102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j102.setText("102");
        j102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j102MousePressed(evt);
            }
        });
        jPanel22.add(j102, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        j103.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j103.setForeground(new java.awt.Color(255, 255, 255));
        j103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j103.setText("103");
        j103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j103MousePressed(evt);
            }
        });
        jPanel22.add(j103, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        j104.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j104.setForeground(new java.awt.Color(255, 255, 255));
        j104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j104.setText("104");
        j104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j104MousePressed(evt);
            }
        });
        jPanel22.add(j104, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, -1, -1));

        j105.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j105.setForeground(new java.awt.Color(255, 255, 255));
        j105.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j105.setText("105");
        j105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j105MousePressed(evt);
            }
        });
        jPanel22.add(j105, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        j106.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j106.setForeground(new java.awt.Color(255, 255, 255));
        j106.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j106.setText("106");
        j106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j106MousePressed(evt);
            }
        });
        jPanel22.add(j106, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        j107.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j107.setForeground(new java.awt.Color(255, 255, 255));
        j107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j107.setText("107");
        j107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j107MousePressed(evt);
            }
        });
        jPanel22.add(j107, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        j108.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j108.setForeground(new java.awt.Color(255, 255, 255));
        j108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j108.setText("108");
        j108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j108MousePressed(evt);
            }
        });
        jPanel22.add(j108, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, -1, -1));

        j109.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j109.setForeground(new java.awt.Color(255, 255, 255));
        j109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j109.setText("109");
        j109.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j109MousePressed(evt);
            }
        });
        jPanel22.add(j109, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        j110.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j110.setForeground(new java.awt.Color(255, 255, 255));
        j110.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j110.setText("110");
        j110.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j110MousePressed(evt);
            }
        });
        jPanel22.add(j110, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));

        j111.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j111.setForeground(new java.awt.Color(255, 255, 255));
        j111.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j111.setText("111");
        j111.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j111MousePressed(evt);
            }
        });
        jPanel22.add(j111, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        j112.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j112.setForeground(new java.awt.Color(255, 255, 255));
        j112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j112.setText("112");
        j112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j112MousePressed(evt);
            }
        });
        jPanel22.add(j112, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        j113.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j113.setForeground(new java.awt.Color(255, 255, 255));
        j113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j113.setText("113");
        j113.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j113MousePressed(evt);
            }
        });
        jPanel22.add(j113, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        j114.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j114.setForeground(new java.awt.Color(255, 255, 255));
        j114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j114.setText("114");
        j114.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j114MousePressed(evt);
            }
        });
        jPanel22.add(j114, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        j115.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j115.setForeground(new java.awt.Color(255, 255, 255));
        j115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j115.setText("115");
        j115.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j115MousePressed(evt);
            }
        });
        jPanel22.add(j115, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, -1, -1));

        j116.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j116.setForeground(new java.awt.Color(255, 255, 255));
        j116.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j116.setText("116");
        j116.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j116MousePressed(evt);
            }
        });
        jPanel22.add(j116, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        j117.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j117.setForeground(new java.awt.Color(255, 255, 255));
        j117.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j117.setText("117");
        j117.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j117MousePressed(evt);
            }
        });
        jPanel22.add(j117, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        j118.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j118.setForeground(new java.awt.Color(255, 255, 255));
        j118.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j118.setText("118");
        j118.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j118MousePressed(evt);
            }
        });
        jPanel22.add(j118, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        j119.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j119.setForeground(new java.awt.Color(255, 255, 255));
        j119.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j119.setText("119");
        j119.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j119MousePressed(evt);
            }
        });
        jPanel22.add(j119, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, -1, -1));

        j120.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j120.setForeground(new java.awt.Color(255, 255, 255));
        j120.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j120.setText("120");
        j120.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j120MousePressed(evt);
            }
        });
        jPanel22.add(j120, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 420, -1, -1));

        j121.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j121.setForeground(new java.awt.Color(255, 255, 255));
        j121.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j121.setText("121");
        j121.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j121MousePressed(evt);
            }
        });
        jPanel22.add(j121, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, -1, -1));

        j122.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j122.setForeground(new java.awt.Color(255, 255, 255));
        j122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j122.setText("122");
        j122.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j122MousePressed(evt);
            }
        });
        jPanel22.add(j122, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));

        j123.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j123.setForeground(new java.awt.Color(255, 255, 255));
        j123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j123.setText("123");
        j123.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j123MousePressed(evt);
            }
        });
        jPanel22.add(j123, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, -1, -1));

        boton_v.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        boton_v.setForeground(new java.awt.Color(255, 255, 255));
        boton_v.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        boton_v.setText("Libre");
        boton_v.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton_vMousePressed(evt);
            }
        });
        jPanel22.add(boton_v, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        boton_r.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        boton_r.setForeground(new java.awt.Color(255, 255, 255));
        boton_r.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
        boton_r.setText("Ocupado");
        boton_r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton_rMousePressed(evt);
            }
        });
        jPanel22.add(boton_r, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        j124.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j124.setForeground(new java.awt.Color(255, 255, 255));
        j124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j124.setText("124");
        j124.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j124MousePressed(evt);
            }
        });
        jPanel22.add(j124, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 490, -1, -1));

        j125.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j125.setForeground(new java.awt.Color(255, 255, 255));
        j125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j125.setText("125");
        j125.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j125MousePressed(evt);
            }
        });
        jPanel22.add(j125, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, -1, -1));

        j126.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j126.setForeground(new java.awt.Color(255, 255, 255));
        j126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j126.setText("126");
        j126.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j126MousePressed(evt);
            }
        });
        jPanel22.add(j126, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 530, -1, -1));

        j127.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j127.setForeground(new java.awt.Color(255, 255, 255));
        j127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j127.setText("127");
        j127.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j127MousePressed(evt);
            }
        });
        jPanel22.add(j127, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, -1, -1));

        j128.setFont(new java.awt.Font("Segoe UI Black", 0, 10)); // NOI18N
        j128.setForeground(new java.awt.Color(255, 255, 255));
        j128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j128.setText("128");
        j128.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j128MousePressed(evt);
            }
        });
        jPanel22.add(j128, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/Mapa Coloringre (1).png"))); // NOI18N
        jPanel22.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 710));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu_ingreso_egreso, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu_ingreso_egreso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        try {
            Tabla();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void Boton_ingresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingresoMousePressed
        Boton_ingreso_alumno();
        try {
            prueba();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                Component jFrame = null;
                int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                if (result == 0) {

                    if (!pasar_dia.isSelected()) {
                        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 2);
                        imprimirtiketacampante(calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), "Alumno", importe, nombre_e.getText(), documento_e.getText());
                    } else {

                        float importe = Main.tarifa_dia_alumnos;
                        imprimirtiketdia("Alumno", importe, nombre_e.getText(), documento_e.getText());
                    }
                }
            }
            if (categoria.equals("aportante")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo aportante por el dia", Main.DiaActual, hora_actual);
                if (!familiares.getText().equals("0")) {
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado " + familiares.getText() + " familiares de aportantes dia", Main.DiaActual, hora_actual);
                }
                modelo.insertardinerocaja(Main.tarifa_dia_aportantes);
                Component jFrame = null;
                int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                if (result == 0) {

                    if (!pasar_dia1.isSelected()) {
                        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
                        imprimirtiketacampante(calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), "Aportante", importe, nombre_a.getText(), documento_a.getText());
                    } else {

                        float importe = Main.tarifa_dia_aportantes;
                        imprimirtiketdia("Aportante", importe, nombre_a.getText(), documento_a.getText());
                    }
                }
                familiares.setText("0");

            }
            if (categoria.equals("particular")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo particular por el dia", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(Main.tarifa_dia_particular);
                Component jFrame = null;
                int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                if (result == 0) {

                    if (!pasar_dia2.isSelected()) {
                        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 2);
                        imprimirtiketacampante(calc_fecha(fecha_ingreso_p), calc_fecha(fecha_egreso_p), "Particular", importe, nombre_p.getText(), documento_p.getText());
                    } else {

                        float importe = Main.tarifa_dia_particular;
                        imprimirtiketdia("Particular", importe, nombre_p.getText(), documento_p.getText());
                    }
                }
            }
            if (categoria.equals("invitado")) {
                modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo invitado por el dia", Main.DiaActual, hora_actual);
                modelo.insertardinerocaja(Main.tarifa_dia_invitados);
                Component jFrame = null;
                int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                if (result == 0) {

                    if (!pasar_dia3.isSelected()) {
                        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p2, fecha_egreso_p2), 4);
                        imprimirtiketacampante(calc_fecha(fecha_ingreso_p2), calc_fecha(fecha_egreso_p2), "Invitado", importe, nombre_p1.getText(), documento_p1.getText());
                    } else {

                        float importe = Main.tarifa_dia_invitados;

                        imprimirtiketdia("Invitado", importe, nombre_p1.getText(), documento_p1.getText());
                    }
                }
            }
            setearnullalumno();
            setearnullparticular();
            setearnullaportante();
            setearnullinvitado();

        }
    }

    private void ObtenerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ObtenerMousePressed
        try {
            if (!pasar_dia.isSelected()) {
                if (!Parcela.getText().isEmpty()) {
                    float importe = 0;

                    if (Integer.parseInt(Parcela.getText()) >= 1 && Integer.parseInt(Parcela.getText()) <= 4) {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 6);
                        tarifa.setText(String.format("%.2f", importe));
                    } else {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);
                        tarifa.setText(String.format("%.2f", importe));
                    }

                    
                }
                

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
                if (!Parsela_p.getText().isEmpty()) {
                    float importe = 0;

                    if (Integer.parseInt(Parsela_p.getText()) >= 1 && Integer.parseInt(Parsela_p.getText()) <= 4) {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 6);
                        tarifa1.setText(String.format("%.2f", importe));
                    } else {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
                        tarifa1.setText(String.format("%.2f", importe));
                    }

                    
                }

            } else {
                tarifa1.setText(String.format("%.2f", Main.tarifa_dia_particular));

            }
        } catch (java.text.ParseException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
            javax.swing.JOptionPane.showMessageDialog(this, "Ingreso una letra donde se espera numero \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } catch (NullPointerException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            prueba();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_ingreso_pMousePressed

    private void jPanel12ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel12ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12ComponentHidden

    private void Obtener2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener2MousePressed
        try {
            if (!pasar_dia1.isSelected()) {
                if (!Parsela_a.getText().isEmpty()) {
                    float importe = 0;

                    if (Integer.parseInt(Parsela_a.getText()) >= 1 && Integer.parseInt(Parsela_a.getText()) <= 4) {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 5);
                        tarifa2.setText(String.format("%.2f", importe));
                    } else {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
                        tarifa2.setText(String.format("%.2f", importe));
                    }

                    
                }
                
                
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

                if (documento_a.getText().equals("") || nombre_a.getText().equals("") || apellido_a.getText().isEmpty() || cod_aportante.getText().isEmpty() || familiares.getText().isEmpty()) {
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
        try {
            Tabla();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
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

    private void menu_ingreso_egresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_ingreso_egresoMousePressed

    }//GEN-LAST:event_menu_ingreso_egresoMousePressed

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
        DefaultTableModel modelo = new DefaultTableModel();

        ArrayList<Object> nombrecolumna = new ArrayList<Object>();
        nombrecolumna.add("Documento");
        nombrecolumna.add("Nombre");
        nombrecolumna.add("Categoria");
        nombrecolumna.add("Importe");
        nombrecolumna.add("Fecha de egreso");
        nombrecolumna.add("Fecha de ingreso");
        nombrecolumna.add("Parcela");

        for (Object columna : nombrecolumna) {
            modelo.addColumn(columna);
        }

        this.tabla_egreso.setModel(modelo);

        while (res.next()) {

            String documento = res.getString("documento");
            String nombre = res.getString("nombre");
            String categoria = res.getString("categoria");
            String importe = String.valueOf(res.getFloat("importe"));
            String fecha_egreso = res.getString("fecha_egreso");
            String fecha_ingreso = res.getString("fecha_ingreso");
            String parcela = res.getString("parsela");
            String tab[] = {documento, nombre, categoria, importe, fecha_egreso, fecha_ingreso, parcela};
            modelo.addRow(tab);

        }

        tabla_egreso.setModel(modelo);
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
        try {
            prueba();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
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
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

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
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

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
        try {
            traerinfo(2);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void boton_vMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_vMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_vMousePressed

    private void boton_rMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_rMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_rMousePressed

    private void jPanel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel22MouseEntered

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
    public void imprimirtiketdia(String categoria, float importe, String nombre, String documento) throws SQLException {
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));
        String n_serial = "0";
        String hora_actual = hora + ":" + minutos;
        ResultSet res = modelo.mostrarregistros1();
        // Aquí tu serial en caso de tener uno
        if (res.next()) {
            n_serial = res.getString("id");
        }

        String serial;
        String nro_serial = String.format("%08d", Integer.parseInt(n_serial));

        ConectorPluginV3 tiket = new ConectorPluginV3(ConectorPluginV3.URL_PLUGIN_POR_DEFECTO, "0006-" + nro_serial);

        tiket.Iniciar()
                .Corte(1)
                .DeshabilitarElModoDeCaracteresChinos()
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .CargarImagenLocalEImprimir("C:\\Users\\lauti\\OneDrive\\Escritorio\\SIGA\\src\\com\\images\\icon-2_1.png", 0, 216)
                .Feed(1)
                .EscribirTexto("SAEBU\n")
                .EscribirTexto("Camping Universitario\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Serial: " + "0006-" + nro_serial + "\nCajero: " + Login.usuario + "\n")
                .EscribirTexto("Fecha y hora: " + Main.DiaActual + " " + hora_actual + "hs")
                .Feed(1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_IZQUIERDA)
                .EscribirTexto("______________________________________________\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Nombre: " + nombre + "        DNI: " + documento + "\n" + "Categoria: " + categoria + "\n" + "Hora-ingreso: " + hora_actual + "\n" + "Solo por el dia\n")
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
                .EscribirTexto("PROHIBIDO EL INGRESO CON ANIMALES\nSi.G.A.")
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

    public void imprimirtiketacampante(String fecha_ingreso, String fecha_egreso, String categoria, float importe, String nombre, String documento) throws SQLException {
        String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
        String minutos = String.valueOf(calendario.get(Calendar.MINUTE));
        String segundos = String.valueOf(calendario.get(Calendar.SECOND));
        String n_serial = "0";
        String hora_actual = hora + ":" + minutos;
        ResultSet res = modelo.mostrarregistros1();

        // Aquí tu serial en caso de tener uno
        if (res.next()) {
            n_serial = res.getString("id");
        }

        String serial;
        String nro_serial = String.format("%08d", Integer.parseInt(n_serial));

        ConectorPluginV3 tiket = new ConectorPluginV3(ConectorPluginV3.URL_PLUGIN_POR_DEFECTO, "0006-" + nro_serial);

        tiket.Iniciar()
                .Corte(1)
                .DeshabilitarElModoDeCaracteresChinos()
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_CENTRO)
                .CargarImagenLocalEImprimir("C:\\Users\\lauti\\OneDrive\\Escritorio\\SIGA\\src\\com\\images\\icon-2_1.png", 0, 216)
                .Feed(1)
                .EscribirTexto("SAEBU\n")
                .EscribirTexto("Camping Universitario\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Serial: " + "0006-" + nro_serial + "\nCajero: " + Login.usuario + "\n")
                .EscribirTexto("Fecha y hora: " + Main.DiaActual + " " + hora_actual + "hs")
                .Feed(1)
                .EstablecerAlineacion(ConectorPluginV3.ALINEACION_IZQUIERDA)
                .EscribirTexto("______________________________________________\n")
                .TextoSegunPaginaDeCodigos(2, "cp850", "Nombre: " + nombre + "        DNI: " + documento + "\n" + "Categoria:" + categoria + "\n" + "Fecha-ingreso: " + fecha_ingreso + "\n" + "Fecha-egreso: " + fecha_egreso + "\n")
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
                .EscribirTexto("PROHIBIDO EL INGRESO CON ANIMALES\nSi.G.A.")
                .Feed(3)
                .Corte(1)
                .Pulso(48, 30, 120);

        try {

            tiket.imprimirEn("impresora termica");

            System.out.println("Impreso correctamente");
        } catch (Exception e) {
            System.out.println("Error imprimiendo: " + e.getMessage());
        }
    }
    private void documento_aKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_documento_aKeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
    }//GEN-LAST:event_documento_aKeyPressed

    private void documento_p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_p1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_p1ActionPerformed

    private void documento_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_documento_p1KeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
    }//GEN-LAST:event_documento_p1KeyPressed

    private void nombre_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_p1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_p1KeyPressed

    private void fecha_ingreso_p2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_ingreso_p2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_ingreso_p2KeyPressed

    private void fecha_egreso_p2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecha_egreso_p2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha_egreso_p2KeyPressed

    private void Obtener3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener3MousePressed
        if (!pasar_dia3.isSelected()) {
            if (!Parsela_p1.getText().isEmpty()) {
                float importe = 0;
                try {

                    if (Integer.parseInt(Parsela_p1.getText()) >= 1 && Integer.parseInt(Parsela_p1.getText()) <= 4) {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p2, fecha_egreso_p2), 6);
                        tarifa3.setText(String.valueOf(importe));
                    } else {
                        importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p2, fecha_egreso_p2), 4);
                        tarifa3.setText(String.valueOf(importe));
                    }

                } catch (java.text.ParseException ex) {
                    Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
                }
                tarifa3.setText(Float.toString(importe));
            }

        } else {

            float importe = Main.tarifa_dia_invitados;
            tarifa3.setText(Float.toString(importe));
        }
    }//GEN-LAST:event_Obtener3MousePressed

    private void tarifa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa3ActionPerformed
    public void RegistrarIngresoInvitados() throws java.text.ParseException, java.lang.NullPointerException, SQLException {

        try {

            float importe = 0;
            int c;
            if (Integer.parseInt(Parsela_p1.getText()) >= 1 && Integer.parseInt(Parsela_p1.getText()) <= 4) {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p2, fecha_egreso_p2), 6);
                tarifa3.setText(String.valueOf(importe));
            } else {
                importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p2, fecha_egreso_p2), 4);
                tarifa3.setText(String.valueOf(importe));
            }
            if (fecha_egreso_p2.getDate() != null && fecha_ingreso_p2.getDate() != null) {

                c = controlador.IngresoParticular(documento_p1.getText(), nombre_p1.getText(), "Invitado", calc_fecha(fecha_ingreso_p2), calc_fecha(fecha_egreso_p2), Parsela_p1.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
                    String minutos = String.valueOf(calendario.get(Calendar.MINUTE));

                    String hora_actual = hora + ":" + minutos;
                    modelo.InsertarRegistro(Login.usuario, "ha ingresado un nuevo invitado acampante", Main.DiaActual, hora_actual);
                    modelo.insertardinerocaja(importe);

                    Component jFrame = null;
                    int result = JOptionPane.showConfirmDialog(jFrame, "Registro exitoso, desea imprimir?");

                    if (result == 0) {

                        if (!pasar_dia3.isSelected()) {

                            imprimirtiketacampante(calc_fecha(fecha_ingreso_p2), calc_fecha(fecha_egreso_p2), "Invitado", importe, nombre_p1.getText(), documento_p1.getText());
                        } else {

                            importe = Main.tarifa_dia_invitados;
                            imprimirtiketdia("Invitado", importe, nombre_p1.getText(), documento_p1.getText());
                        }
                    }
                    setearnullinvitado();

                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe de ingresar una fecha de ingreso y egreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.lang.NullPointerException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos de ingreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public void ingre_i() throws NullPointerException, SQLException {

        if (!documento_p1.getText().isEmpty() || !nombre_p1.getText().isEmpty()) {

            int parsela = 0;
            try {

                try {
                    parsela = Integer.parseInt(Parsela_p1.getText());
                } catch (java.lang.NumberFormatException e) {
                    System.out.println("es un string y no se puede pasar");
                }

                //control que parsela este entre 128 y 1
                if (parsela <= 128 && parsela >= 1) {

                    RegistrarIngresoInvitados();

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

    public void botoningresoinvitado() {
        try {
            if (controlador.ControlarCajaAbierta() == 1) {
                int c = controlador.Controldnirepetidoingreso(documento_p1.getText());
                int b = controlador.Controldnirepetidoingresodiario(documento_p1.getText());

                if (documento_p1.getText().equals("") || nombre_p1.getText().equals("")) {
                    throw new Exception();
                }

                if (c == 0 && b == 0) {
                    if (pasar_dia3.isSelected()) {

                        ingre_dia("invitado", documento_p1.getText(), nombre_p1.getText());
                    } else {
                        ingre_i();
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
        try {
            Tabla();
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Boton_ingreso_p1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingreso_p1MousePressed
        botoningresoinvitado();
        try {
            prueba();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Boton_ingreso_p1MousePressed

    private void Boton_ingreso_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Boton_ingreso_p1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Boton_ingreso_p1KeyPressed

    private void Parsela_p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Parsela_p1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Parsela_p1ActionPerformed

    private void Parsela_p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Parsela_p1KeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");

        }
    }//GEN-LAST:event_Parsela_p1KeyPressed

    private void jPanel23ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel23ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel23ComponentHidden

    private void pasar_dia3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasar_dia3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pasar_dia3MousePressed

    private void pasar_dia3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasar_dia3ActionPerformed
        if (pasar_dia3.isSelected()) {
            fecha_ingreso_p2.setEnabled(false);
            fecha_egreso_p2.setEnabled(false);
            Parsela_p.setEnabled(false);
        } else {
            fecha_ingreso_p2.setEnabled(true);
            fecha_egreso_p2.setEnabled(true);
            Parsela_p.setEnabled(true);
        }

    }//GEN-LAST:event_pasar_dia3ActionPerformed

    private void pasar_dia3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasar_dia3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pasar_dia3KeyPressed

    private void familiaresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_familiaresKeyPressed
        char validador = evt.getKeyChar();

        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");
            familiares.setText("0");

        }
    }//GEN-LAST:event_familiaresKeyPressed

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

    private void cod_aportanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cod_aportanteKeyPressed
        
    }//GEN-LAST:event_cod_aportanteKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Boton_ingreso;
    private javax.swing.JLabel Boton_ingreso_a;
    private javax.swing.JLabel Boton_ingreso_p;
    private javax.swing.JLabel Boton_ingreso_p1;
    private javax.swing.JLabel Buscar;
    private javax.swing.JLabel Buscar_a;
    private javax.swing.JTextField Documento;
    private javax.swing.JTextField Documento_a;
    private javax.swing.JButton Obtener;
    private javax.swing.JButton Obtener1;
    private javax.swing.JButton Obtener2;
    private javax.swing.JButton Obtener3;
    private javax.swing.JTextField Parcela;
    private javax.swing.JTextField Parsela_a;
    private javax.swing.JTextField Parsela_p;
    private javax.swing.JTextField Parsela_p1;
    private javax.swing.JPanel alumnoss;
    private javax.swing.JTextField apellido_a;
    private javax.swing.JTextField apellido_e;
    private javax.swing.JPanel aportantes;
    private javax.swing.JLabel boton_r;
    private javax.swing.JLabel boton_v;
    private javax.swing.JTextField carrera_e;
    private javax.swing.JTextField cod_aportante;
    private javax.swing.JTextField dni_buscado;
    private javax.swing.JTextField documento_a;
    private javax.swing.JTextField documento_e;
    private javax.swing.JTextField documento_p;
    private javax.swing.JTextField documento_p1;
    private javax.swing.JPanel egresos;
    private javax.swing.JTextField facultad_e;
    private javax.swing.JTextField familiares;
    private com.toedter.calendar.JDateChooser fecha_egreso;
    private com.toedter.calendar.JDateChooser fecha_egreso_p;
    private com.toedter.calendar.JDateChooser fecha_egreso_p1;
    private com.toedter.calendar.JDateChooser fecha_egreso_p2;
    private com.toedter.calendar.JDateChooser fecha_ingreso;
    private com.toedter.calendar.JDateChooser fecha_ingreso_p;
    private com.toedter.calendar.JDateChooser fecha_ingreso_p1;
    private com.toedter.calendar.JDateChooser fecha_ingreso_p2;
    private javax.swing.JLabel ingreso_vehiculo;
    private javax.swing.JPanel invitados;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField marca;
    private javax.swing.JTabbedPane menu_ingreso_egreso;
    private javax.swing.JTextField nombre_a;
    private javax.swing.JTextField nombre_e;
    private javax.swing.JTextField nombre_p;
    private javax.swing.JTextField nombre_p1;
    private javax.swing.JPanel particular;
    private javax.swing.JCheckBox pasar_dia;
    private javax.swing.JCheckBox pasar_dia1;
    private javax.swing.JCheckBox pasar_dia2;
    private javax.swing.JCheckBox pasar_dia3;
    private javax.swing.JTextField patente;
    private javax.swing.JTable tabla_egreso;
    private javax.swing.JTable tabla_vehiculo;
    private javax.swing.JTextField tarifa;
    private javax.swing.JTextField tarifa1;
    private javax.swing.JTextField tarifa2;
    private javax.swing.JTextField tarifa3;
    private javax.swing.JPanel vahiculos;
    // End of variables declaration//GEN-END:variables

}
