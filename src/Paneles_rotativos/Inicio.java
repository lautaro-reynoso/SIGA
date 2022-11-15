/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles_rotativos;

import Main.Modelo;
import Main.Controlador;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateo
 */
public class Inicio extends javax.swing.JPanel {

    int filas = 0, filas_dia = 0, filas_vehiculos = 0, filas_parcelas = 0, filas_egresantes = 0;
    Controlador controlador = new Controlador();
    Modelo modelo = new Modelo();

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();

    }

    public void buscarDocumento() throws SQLException {

        ResultSet res_1;

        res_1 = controlador.MostarDocumento(busca_documento2.getText());
        Busqueda(res_1);

    }

    public void buscarDocumentoDia() throws SQLException {
        ResultSet res_2;

        res_2 = controlador.MostarDocumentoDia(busca_documento1.getText());
        BusquedaDia(res_2);

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
            DefaultTableModel tablamodelo = (DefaultTableModel) jTable1.getModel();

            tablamodelo.addRow(tab);
            filas = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }
    }

    public void BusquedaDia(ResultSet res) throws SQLException {
        int f = 0;
        while (res.next()) {

            f++;
            String documento = res.getString("dni");
            String nombre = res.getString("nombre");
            String hora_ingreso = res.getString("hora_ingreso");
            String categoria = res.getString("categoria");
            String tab[] = {documento, nombre, hora_ingreso, categoria};
            DefaultTableModel tablamodelo = (DefaultTableModel) jTable2.getModel();

            tablamodelo.addRow(tab);
            filas_dia = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas_dia + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }
    }

    public void BusquedaVehiculos(ResultSet res) throws SQLException {
        int f = 0;
        while (res.next()) {

            f++;
            String marca = res.getString("marca");
            String patente = res.getString("patente");
            String tab[] = {marca, patente};
            DefaultTableModel tablamodelo = (DefaultTableModel) jTable3.getModel();

            tablamodelo.addRow(tab);
            filas_vehiculos = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas_vehiculos + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }
    }

    public void BusquedaParcelas(ResultSet res) throws SQLException {

        int f = 0;
        while (res.next()) {
            f++;
            String parcela = res.getString("parsela");
            String nombre = res.getString("nombre");
            String documento = res.getString("documento");

            String tab[] = {parcela, nombre, documento};
            DefaultTableModel tablamodelo = (DefaultTableModel) tabla_ocupacion.getModel();

            tablamodelo.addRow(tab);

            filas_parcelas = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas_parcelas + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }

    }

    public void BusquedaParcelasPorNro() throws SQLException {

        ResultSet res_2;

        res_2 = controlador.BuscarParsela(busca_parcela.getText());
        Busqueda(res_2);
    }

    public void Tabla() throws SQLException {
        ResultSet res_1, res_2, res_3;
        res_1 = controlador.MostarOcupacionActual();

        Busqueda(res_1);

        res_2 = controlador.MostarOcupacionActualDia();
        BusquedaDia(res_2);
        ;
        //  String id = String.valueOf(s.getString("id"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cant_p = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        busca_parcela = new javax.swing.JTextField();
        parsela_btn = new javax.swing.JLabel();
        consultar_btn = new javax.swing.JLabel();
        busca_documento1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        busca_documento2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_ocupacion = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        parcela_e = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        parcela_s = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_egresantes = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Nombre", "Hora de ingreso", "Categoría"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        cant_p.setText("Personas en el camping: Actualizar Tablas");

        jLabel1.setText("Tabla de acampantes");

        jLabel3.setText("Tabla de personas para pasar el día");

        busca_parcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busca_parcelaActionPerformed(evt);
            }
        });
        busca_parcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_parcelaKeyPressed(evt);
            }
        });

        parsela_btn.setText("BUSCAR");
        parsela_btn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        parsela_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        parsela_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                parsela_btnMousePressed(evt);
            }
        });

        consultar_btn.setText("Actualizar Tablas");
        consultar_btn.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        consultar_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        consultar_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                consultar_btnMousePressed(evt);
            }
        });

        busca_documento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busca_documento1ActionPerformed(evt);
            }
        });
        busca_documento1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_documento1KeyPressed(evt);
            }
        });

        jLabel6.setText("BUSCAR");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        busca_documento2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busca_documento2ActionPerformed(evt);
            }
        });
        busca_documento2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                busca_documento2KeyPressed(evt);
            }
        });

        jLabel4.setText("BUSCAR");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        jLabel5.setText("Buscar por numero de parcela");

        jLabel7.setText("Buscar por numero de documento");

        jLabel8.setText("Buscar por numero de documento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cant_p)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(consultar_btn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(busca_documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)))
                                .addGap(117, 117, 117)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(busca_parcela)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parsela_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(busca_documento2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(jLabel8))
                        .addGap(140, 140, 140))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(busca_parcela, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parsela_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(busca_documento2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(busca_documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consultar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cant_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        jTabbedPane1.addTab("OCUPACION ACTUAL", jPanel1);

        jLabel2.setText("Vehiculos registrados en el camping");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jLabel9.setText("REUBICACION DE ACAMPANTES");

        tabla_ocupacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Parcela (editar)", "Nombre", "Documento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_ocupacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabla_ocupacionMousePressed(evt);
            }
        });
        tabla_ocupacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_ocupacionKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_ocupacion);

        jButton2.setText("Confirmar rehubicación");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jLabel10.setText("EDITAR PARCELA:");

        jLabel11.setText("PARCELA SELECCIONADA:");

        parcela_s.setText("ELEGIR");

        tabla_egresantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Documento", "Fecha de ingreso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabla_egresantes);

        jLabel12.setText("Tabla de egresantes de hoy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parcela_s)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(parcela_e, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(parcela_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jButton2)
                    .addComponent(jLabel11)
                    .addComponent(parcela_s))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        jTabbedPane1.addTab("EGRESANTES/REUBICACION", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void consultar_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultar_btnMousePressed
        try {
            Tabla();
            int cantidad_personas = jTable1.getRowCount() + jTable2.getRowCount();
            cant_p.setText("Cantidad de personas en el camping: " + cantidad_personas);

        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_consultar_btnMousePressed

    private void busca_parcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busca_parcelaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busca_parcelaActionPerformed

    public void TablaVehiculos() throws SQLException {
        ResultSet res_1;
        res_1 = controlador.MostarVehiculos();

        BusquedaVehiculos(res_1);

    }

    public void TablaParcelas() throws SQLException {
        ResultSet res_1;
        res_1 = controlador.MostarOcupacionActual();

        BusquedaParcelas(res_1);

    }


    private void busca_documento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busca_documento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busca_documento1ActionPerformed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        try {
            buscarDocumentoDia();
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jLabel6MousePressed

    private void busca_documento2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busca_documento2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busca_documento2ActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

        try {
            buscarDocumento();
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jLabel4MousePressed

    private void parsela_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_parsela_btnMousePressed

        try {
            BusquedaParcelasPorNro();
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_parsela_btnMousePressed

    private void busca_parcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_parcelaKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                BusquedaParcelasPorNro();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         char validador = evt.getKeyChar();
        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");
            busca_parcela.setText(null);
        }
    }//GEN-LAST:event_busca_parcelaKeyPressed

    private void busca_documento2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_documento2KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                buscarDocumento();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_busca_documento2KeyPressed

    private void busca_documento1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busca_documento1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            try {
                buscarDocumentoDia();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
         char validador = evt.getKeyChar();
        if (Character.isLetter(validador)) {
            getToolkit().beep();
            evt.consume();
            Component rootPane = null;

            JOptionPane.showMessageDialog(rootPane, "Ingrese solo números!  ");
            busca_documento1.setText(null);
        }
    }//GEN-LAST:event_busca_documento1KeyPressed

    public void RehubicacionParcelas() {
        if (!parcela_e.getText().isEmpty()) {

            if (Integer.parseInt(parcela_e.getText()) <= 0 || Integer.parseInt(parcela_e.getText()) > 128) {
                javax.swing.JOptionPane.showMessageDialog(this, "No existe tal parcela.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } else {

                //  System.out.println(parcela);
                try {

                    controlador.ModificarParcela(Integer.parseInt(parcela_s.getText()), Integer.parseInt(parcela_e.getText()));
                     parcela_s.setText("ELEGIR");
                    javax.swing.JOptionPane.showMessageDialog(this, "La parcela se actualizó con éxito.", "ACTUALIZADO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                } catch (java.lang.NumberFormatException e) {
                    javax.swing.JOptionPane.showMessageDialog(this, "hubo un error en el ingreso de datos, porfavor eliga una parcela a modificar.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                }

            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Antes de modificar, complete el campo de la parcela a modificar.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        try {
            RehubicacionParcelas();

        } catch (java.lang.NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Hubo un error, intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            parcela_e.setText(null);

        }
        try {
            TablaParcelas();
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton2MousePressed

    public void TablaEgresantes() throws SQLException {

        ResultSet res_1;
        res_1 = controlador.MostarEgreso();

        BusquedaEgresantes(res_1);

    }

    public void BusquedaEgresantes(ResultSet res) throws SQLException {

        int f = 0;
        while (res.next()) {
            f++;
            String egreso = res.getString("fecha_egreso");
            String nombre = res.getString("nombre");
            String documento = res.getString("documento");

            String tab[] = {nombre, documento, egreso};
            DefaultTableModel tablamodelo = (DefaultTableModel) tabla_egresantes.getModel();

            tablamodelo.addRow(tab);

            filas_egresantes = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas_egresantes + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }

    }


    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed

        try {
            TablaVehiculos();
            TablaParcelas();
            TablaEgresantes();
        } catch (SQLException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTabbedPane1MousePressed

    private void tabla_ocupacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ocupacionMousePressed

        String parcela = tabla_ocupacion.getModel().getValueAt(tabla_ocupacion.getSelectedRow(), 0).toString();

        parcela_s.setText(parcela);
    }//GEN-LAST:event_tabla_ocupacionMousePressed

    private void tabla_ocupacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_ocupacionKeyPressed
         if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String parcela = tabla_ocupacion.getModel().getValueAt(tabla_ocupacion.getSelectedRow(), 0).toString();

        parcela_s.setText(parcela);
        }
        
    }//GEN-LAST:event_tabla_ocupacionKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField busca_documento1;
    private javax.swing.JTextField busca_documento2;
    private javax.swing.JTextField busca_parcela;
    private javax.swing.JLabel cant_p;
    private javax.swing.JLabel consultar_btn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField parcela_e;
    private javax.swing.JLabel parcela_s;
    private javax.swing.JLabel parsela_btn;
    private javax.swing.JTable tabla_egresantes;
    private javax.swing.JTable tabla_ocupacion;
    // End of variables declaration//GEN-END:variables
}
