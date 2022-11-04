/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles_rotativos;

import Main.Controlador;
import static Main.Main.controlador;
import Main.Modelo;
import Paneles_principales.Login;
import com.formdev.flatlaf.json.ParseException;
import com.toedter.calendar.JDateChooser;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lauti
 */
public class Ingre extends javax.swing.JPanel {

    int filas = 0;
    Modelo modelo = new Modelo();

    public Ingre() {
        initComponents();
        tarifa1.setEnabled(false);
    }
    Controlador controlador = new Controlador();

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

    public void RegistrarIngresoParticular() throws java.text.ParseException, java.lang.NullPointerException {

        try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
            int c;
            if (fecha_egreso_p.getDate() != null) {

                c = controlador.IngresoParticular(documento_p.getText(), nombre_p.getText(), "3", calc_fecha(fecha_ingreso_p), calc_fecha(fecha_egreso_p), Parsela_p.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Registro exitoso", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Debe de ingresar una fecha de ingreso y egreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

            

        } catch (java.lang.NullPointerException e) {

            javax.swing.JOptionPane.showMessageDialog(this, "Debe completar todos los campos de ingreso.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }
    public void RegistrarIngresoAportante() throws java.text.ParseException{
          try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
            int c;
            if (fecha_egreso_p1.getDate() != null) {
                    
                c = controlador.IngresoParticular(documento_a.getText(), Nombre_a.getText(), "2", calc_fecha(fecha_ingreso_p1), calc_fecha(fecha_egreso_p1), Parsela_a.getText(), importe);

                if (c != 1) {
                    javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(this, "Registro exitoso", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
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
            Nombre_a.setText(res.getString("nombre"));
            documento_a.setText(res.getString("doc"));
            cod_aportante.setText(res.getString("cod_aportante"));
            apellido_a.setText(res.getString("apellido"));
            
            
        } else {
            System.out.print("Error");
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

    public void RegistrarIngreso() throws java.text.ParseException {
        float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);
        int c;
        //   System.out.println("fecha:" + fecha_egreso);

        if (fecha_egreso.getDate() != null) {

            c = controlador.IngresoParticular(documento_e.getText(), nombre_e.getText(), "1", calc_fecha(fecha_ingreso), calc_fecha(fecha_egreso), Parsela.getText(), importe);

            if (c != 1) {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar.\n Error.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Registro exitoso", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        documento_p = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Documento_a = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Buscar_a = new javax.swing.JLabel();
        Nombre_a = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellido_a = new javax.swing.JTextField();
        cod_aportante = new javax.swing.JTextField();
        documento_a = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
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
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        documento_e = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombre_e = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        apellido_e = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        carrera_e = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        facultad_e = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
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
        Parsela = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Documento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Buscar = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        documento_p.setForeground(new java.awt.Color(204, 204, 204));
        documento_p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_p.setText("Documento");
        documento_e.setEnabled(false);
        documento_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_pActionPerformed(evt);
            }
        });

        jLabel17.setText("INGRESO");

        jLabel18.setText("NOMBRE");

        nombre_p.setForeground(new java.awt.Color(204, 204, 204));
        nombre_p.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_p.setText("Nombre");
        nombre_e.setEnabled(false);

        jLabel19.setText("DOCUMENTO");

        jLabel23.setText("FECHA DE EGRESO");

        jLabel24.setText("FECHA DE INGRESO");

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

        Parsela_p.setText("Parsela");
        Parsela_p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Parsela_pActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(fecha_ingreso_p, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                        .addComponent(fecha_egreso_p, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Obtener1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_egreso_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener1))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(documento_p)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 393, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(367, 367, 367))
                    .addComponent(nombre_p, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documento_p, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(444, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PARTICULAR", jPanel3);

        Documento_a.setForeground(new java.awt.Color(204, 204, 204));
        Documento_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Documento_a.setText("Documento");
        Documento_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Documento_aActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Documento_a)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(Buscar_a, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Documento_a, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar_a)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        Nombre_a.setForeground(new java.awt.Color(204, 204, 204));
        Nombre_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nombre_a.setText("Nombre");

        jLabel4.setText("Nombre:");

        apellido_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido_a.setText("Apellido");

        cod_aportante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cod_aportante.setText("Codigo de aportante");

        documento_a.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_a.setText("Documento");

        jLabel15.setText("Apellido:");

        jLabel16.setText("Cod. aportante:");

        jLabel20.setText("Documento:");

        jLabel27.setText("FECHA DE EGRESO");

        jLabel28.setText("FECHA DE INGRESO");

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

        Parsela_a.setText("Parsela");
        Parsela_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Parsela_aActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
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
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingreso_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_egreso_p1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Obtener2))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(documento_a)
                            .addComponent(cod_aportante, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(apellido_a)
                            .addComponent(Nombre_a))))
                .addContainerGap(343, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Nombre_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cod_aportante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(documento_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(42, 42, 42)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 96, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("APORTANTES", jPanel4);

        documento_e.setForeground(new java.awt.Color(204, 204, 204));
        documento_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        documento_e.setText("Documento");
        documento_e.setEnabled(false);
        documento_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documento_eActionPerformed(evt);
            }
        });

        jLabel5.setText("INGRESO");

        jLabel6.setText("NOMBRE");

        nombre_e.setForeground(new java.awt.Color(204, 204, 204));
        nombre_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre_e.setText("Nombre");
        nombre_e.setEnabled(false);

        jLabel7.setText("DOCUMENTO");

        apellido_e.setForeground(new java.awt.Color(204, 204, 204));
        apellido_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido_e.setText("Apellido");
        apellido_e.setEnabled(false);

        jLabel8.setText("APELLIDO");

        carrera_e.setForeground(new java.awt.Color(204, 204, 204));
        carrera_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        carrera_e.setText("Carrera");
        carrera_e.setEnabled(false);
        carrera_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carrera_eActionPerformed(evt);
            }
        });

        jLabel12.setText("CARRERA");

        facultad_e.setForeground(new java.awt.Color(204, 204, 204));
        facultad_e.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        facultad_e.setText("Facultad");
        facultad_e.setEnabled(false);
        facultad_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultad_eActionPerformed(evt);
            }
        });

        jLabel13.setText("FACULTAD");

        jLabel1.setText("FECHA DE EGRESO");

        jLabel2.setText("FECHA DE INGRESO");

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

        Parsela.setText("Parsela");
        Parsela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ParselaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Parsela)
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
                .addComponent(Parsela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(facultad_e, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                            .addComponent(carrera_e)
                            .addComponent(apellido_e)
                            .addComponent(documento_e)
                            .addComponent(nombre_e)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documento_e, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(apellido_e, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombre_e, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carrera_e, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(facultad_e, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jPanel1.setBackground(new java.awt.Color(255, 254, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("BUSQUEDA POR DOCUMENTO");

        Documento.setForeground(new java.awt.Color(204, 204, 204));
        Documento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Documento.setText("Documento");
        Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentoActionPerformed(evt);
            }
        });

        jLabel10.setText("DOCUMENTO");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ALUMNOS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
    public void ingre_p() {

        if (!documento_p.getText().isEmpty() || !nombre_p.getText().isEmpty()) {

       

                int parsela = 0;
                try {

                    //controlar que en parsela no haya un string
                    try {
                        parsela = Integer.parseInt(Parsela_p.getText());
                    } catch (java.lang.NumberFormatException e) {
                        System.out.println("es un string y no se puede pasar");
                    }

                    //control que parsela este entre 50 y 1
                    if (parsela <= 50 && parsela >= 1) {

                        //  Ocupacion ocupacion = new Ocupacion();
                        // RegistrarParsela();
                        //ocupacion.BuscarParsela(Parsela.getText());
                        RegistrarIngresoParticular();
                        controlador.IngresoRegistro(Login.usuario, "ha registrado un nuevo ingresante", LocalDate.now().toString(), LocalTime.now().toString());

                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (java.text.ParseException ex) {
                    Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
                } 
                

                catch (java.lang.NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(this, "Ingreso un texto donde se esperaba un numero.", "ERROR",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                


        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR, complete todos los campos antes de registrar un ingreso. \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void ingre() {
        int parsela = 0;
        try {
            
            //controlar que en parsela no haya un string
            try {
                parsela = Integer.parseInt(Parsela.getText());
            } catch (java.lang.NumberFormatException e) {
                System.out.println("es un string y no se puede pasar");
            }

            //control que parsela este entre 50 y 1
            if (parsela <= 50 && parsela >= 1) {

                //  Ocupacion ocupacion = new Ocupacion();
                // RegistrarParsela();
                //ocupacion.BuscarParsela(Parsela.getText());
                RegistrarIngreso();
                controlador.IngresoRegistro(Login.usuario, "ha registrado un nuevo ingresante", LocalDate.now().toString(), LocalTime.now().toString());

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.text.ParseException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ingre_a (){
         int parsela = 0;
                try {

                    //controlar que en parsela no haya un string
                    try {
                        parsela = Integer.parseInt(Parsela_a.getText());
                    } catch (java.lang.NumberFormatException e) {
                        System.out.println("es un string y no se puede pasar");
                    }

                    //control que parsela este entre 50 y 1
                    if (parsela <= 50 && parsela >= 1) {

                        //  Ocupacion ocupacion = new Ocupacion();
                        // RegistrarParsela();
                        //ocupacion.BuscarParsela(Parsela.getText());
                        RegistrarIngresoAportante();
                        controlador.IngresoRegistro(Login.usuario, "ha registrado un nuevo ingresante", LocalDate.now().toString(), LocalTime.now().toString());

                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "No se pudo registrar, Ingreso una parsela que no se encuentra en la base de datos.", "ERROR",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (java.text.ParseException ex) {
                    Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
                } 
    }
    private void Boton_ingresoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingresoMousePressed
        try {
            int c = controlador.Controldnirepetidoingreso(documento_e.getText());
            if(c==0)
                 ingre();
            else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
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
            System.out.println(x);
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

    private void ObtenerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ObtenerMousePressed
        try {
            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso, fecha_egreso), 1);

            tarifa.setText(String.format("%.2f", importe));
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

    private void ParselaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ParselaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ParselaActionPerformed

    private void documento_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documento_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_documento_pActionPerformed

    private void Obtener1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener1MousePressed

        try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p, fecha_egreso_p), 3);
            tarifa1.setText(String.format("%.2f", importe));
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Obtener1MousePressed

    private void tarifa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa1ActionPerformed
    
    private void Boton_ingreso_pMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingreso_pMousePressed
            try {
            int c = controlador.Controldnirepetidoingreso(documento_p.getText());
            if(c==0)
                 ingre_p();
            else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_Boton_ingreso_pMousePressed

    private void Parsela_pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Parsela_pActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Parsela_pActionPerformed

    private void jPanel12ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel12ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel12ComponentHidden

    private void Obtener2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Obtener2MousePressed
         try {

            float importe = controlador.CalcularImporte((int) cant_dias(fecha_ingreso_p1, fecha_egreso_p1), 2);
            tarifa2.setText(String.format("%.2f", importe));
        } catch (java.text.ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese una fecha de ingreso y egreso \nIntente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Obtener2MousePressed

    private void tarifa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifa2ActionPerformed

    private void Boton_ingreso_aMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Boton_ingreso_aMousePressed
       try {
            int c = controlador.Controldnirepetidoingreso(documento_a.getText());
            if(c==0)
                 ingre_a();
            else {
            javax.swing.JOptionPane.showMessageDialog(this, "Ya se encuentra un acampante con ese dni en el camping.", "ERROR",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_Boton_ingreso_aMousePressed

    private void Parsela_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Parsela_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Parsela_aActionPerformed

    private void jPanel14ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel14ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel14ComponentHidden
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Boton_ingreso;
    private javax.swing.JLabel Boton_ingreso_a;
    private javax.swing.JLabel Boton_ingreso_p;
    private javax.swing.JLabel Buscar;
    private javax.swing.JLabel Buscar_a;
    private javax.swing.JTextField Documento;
    private javax.swing.JTextField Documento_a;
    private javax.swing.JTextField Nombre_a;
    private javax.swing.JButton Obtener;
    private javax.swing.JButton Obtener1;
    private javax.swing.JButton Obtener2;
    private javax.swing.JTextField Parsela;
    private javax.swing.JTextField Parsela_a;
    private javax.swing.JTextField Parsela_p;
    private javax.swing.JTextField apellido_a;
    private javax.swing.JTextField apellido_e;
    private javax.swing.JTextField carrera_e;
    private javax.swing.JTextField cod_aportante;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nombre_e;
    private javax.swing.JTextField nombre_p;
    private javax.swing.JTextField tarifa;
    private javax.swing.JTextField tarifa1;
    private javax.swing.JTextField tarifa2;
    // End of variables declaration//GEN-END:variables

}
