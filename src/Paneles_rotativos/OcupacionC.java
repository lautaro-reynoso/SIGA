/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles_rotativos;

import Main.Controlador;
import Main.Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author mateo
 */
public class OcupacionC extends javax.swing.JPanel {

    /**
     * Creates new form OcupacionC
     *
     */
    Controlador controlador = new Controlador();
    Modelo modelo = new Modelo();
    public void prueba() throws SQLException{
        javax.swing.JLabel[] label = {j1, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42, j43, j44, j45, j46, j47, j47, j48, j49, j50};

        ResultSet res;
        res = modelo.MostarOcupacionActual();
        
        while (res.next() == true) {

            for (int i = 0; i < label.length; i++) {

                if (String.valueOf(i+1).equals(res.getString("parsela"))) {
                    label[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                    label[i].setText((i+1) + "(ocupado)");
                    label[i].repaint();
                }

            }

            
         
            //    jLabel6.paintImmediately(jLabel6.getVisibleRect());
        }
    }
    public void BuscarParsela(String parsela) throws SQLException {

        ResultSet res;
        res = controlador.BuscarParsela(parsela);
       
        while(res.next()){
        
                
            
            if ("50".equals(parsela)) {
                j50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j50.setText(parsela + "(ocupado)");
                j50.repaint();

            }
            if ("49".equals(parsela)) {
                j49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j49.setText(parsela + "(ocupado)");
                j49.repaint();
            }
            if ("48".equals(parsela)) {
                j48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j48.setText(parsela + "(ocupado)");
                j48.repaint();
            }
            if ("47".equals(parsela)) {
                j47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j47.setText(parsela + "(ocupado)");
                j47.repaint();
            }
            if ("46".equals(parsela)) {
                j46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j46.setText(parsela + "(ocupado)");
                j46.repaint();
            }
            if ("45".equals(parsela)) {
                j45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j45.setText(parsela + "(ocupado)");
                j45.repaint();
            }

            if ("44".equals(parsela)) {
                j44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j44.setText(parsela + "(ocupado)");
                j44.repaint();
            }
            if ("43".equals(parsela)) {
                j43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j43.setText(parsela + "(ocupado)");
                j43.repaint();
            }
            if ("42".equals(parsela)) {
                j42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j42.setText(parsela + "(ocupado)");
                j42.repaint();
            }
            if ("41".equals(parsela)) {
                j41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j41.setText(parsela + "(ocupado)");
                j41.repaint();
            }
            if ("40".equals(parsela)) {
                j40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j40.setText(parsela + "(ocupado)");
                j40.repaint();
            }
            if ("39".equals(parsela)) {
                j39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j39.setText(parsela + "(ocupado)");
                j39.repaint();
            }

            if ("38".equals(parsela)) {
                j38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j38.setText(parsela + "(ocupado)");
                j38.repaint();
            }
            if ("37".equals(parsela)) {
                j37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j37.setText(parsela + "(ocupado)");
                j37.repaint();
            }
            if ("36".equals(parsela)) {
                j36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j36.setText(parsela + "(ocupado)");
                j36.repaint();
            }
            if ("35".equals(parsela)) {
                j35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j35.setText(parsela + "(ocupado)");
                j35.repaint();
            }
            if ("34".equals(parsela)) {
                j34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j34.setText(parsela + "(ocupado)");
                j34.repaint();
            }

            if ("33".equals(parsela)) {
                j33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j33.setText(parsela + "(ocupado)");
                j33.repaint();
            }
            if ("32".equals(parsela)) {
                j32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j32.setText(parsela + "(ocupado)");
                j32.repaint();
            }
            if ("31".equals(parsela)) {
                j31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j31.setText(parsela + "(ocupado)");
                j31.repaint();
            }
            if ("30".equals(parsela)) {
                j30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j30.setText(parsela + "(ocupado)");
                j30.repaint();
            }
            if ("29".equals(parsela)) {
                j29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j29.setText(parsela + "(ocupado)");
                j29.repaint();
            }
            if ("28".equals(parsela)) {
                j28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j28.setText(parsela + "(ocupado)");
                j28.repaint();
            }

            if ("27".equals(parsela)) {
                j27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j27.setText(parsela + "(ocupado)");
                j27.repaint();
            }
            if ("26".equals(parsela)) {
                j26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j26.setText(parsela + "(ocupado)");
                j26.repaint();
            }
            if ("25".equals(parsela)) {
                j25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j25.setText(parsela + "(ocupado)");
                j25.repaint();
            }
            if ("24".equals(parsela)) {
                j24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j24.setText(parsela + "(ocupado)");
                j24.repaint();
            }
            if ("23".equals(parsela)) {
                j23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j23.setText(parsela + "(ocupado)");
                j23.repaint();
            }
            if ("22".equals(parsela)) {
                j22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j22.setText(parsela + "(ocupado)");
                j22.repaint();
            }
            if ("21".equals(parsela)) {
                j21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j21.setText(parsela + "(ocupado)");
                j21.repaint();
            }
            if ("20".equals(parsela)) {
                j20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j20.setText(parsela + "(ocupado)");
                j20.repaint();
            }
            if ("19".equals(parsela)) {
                j19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j19.setText(parsela + "(ocupado)");
                j19.repaint();
            }
            if ("18".equals(parsela)) {
                j18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j18.setText(parsela + "(ocupado)");
                j18.repaint();
            }

            if ("17".equals(parsela)) {
                j17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j17.setText(parsela + "(ocupado)");
                j17.repaint();
            }
            if ("16".equals(parsela)) {
                j16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j16.setText(parsela + "(ocupado)");
                j16.repaint();
            }
            if ("15".equals(parsela)) {
                j15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j15.setText(parsela + "(ocupado)");
                j15.repaint();
            }
            if ("14".equals(parsela)) {
                j14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j14.setText(parsela + "(ocupado)");
                j14.repaint();
            }
            if ("13".equals(parsela)) {
                j13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j13.setText(parsela + "(ocupado)");
                j13.repaint();
            }
            if ("12".equals(parsela)) {
                j12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j12.setText(parsela + "(ocupado)");
                j12.repaint();
            }
            if ("11".equals(parsela)) {
                j11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j11.setText(parsela + "(ocupado)");
                j11.repaint();
            }
            if ("1".equals(parsela)) {
                j1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j1.setText(parsela + "(ocupado)");
                j1.repaint();
            }
            if ("10".equals(parsela)) {
                j10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j10.setText(parsela + "(ocupado)");
                j10.repaint();
            }
            if ("9".equals(parsela)) {
                j9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j9.setText(parsela + "(ocupado)");
                j9.repaint();
            }
            if ("8".equals(parsela)) {
                j8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j8.setText(parsela + "(ocupado)");
                j8.repaint();
            }
            if ("7".equals(parsela)) {
                j7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j7.setText(parsela + "(ocupado)");
                j7.repaint();
            }
            if ("6".equals(parsela)) {
                j6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j6.setText(parsela + "(ocupado)");
                j6.repaint();
            }
            if ("5".equals(parsela)) {
                j5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j5.setText(parsela + "(ocupado)");
                j5.repaint();
            }
            if ("4".equals(parsela)) {
                j4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j4.setText(parsela + "(ocupado)");
                j4.repaint();
            }
            if ("3".equals(parsela)) {
                j3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j3.setText(parsela + "(ocupado)");
                j3.repaint();
            }
            if ("2".equals(parsela)) {
                j2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/false1x.png"))); // NOI18N
                j2.setText(parsela + "(ocupado)");
                j2.repaint();
            }
            //    jLabel6.paintImmediately(jLabel6.getVisibleRect());
        }
    }

    public OcupacionC() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
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
        jLabel51 = new javax.swing.JLabel();

        jMenuItem1.setText("mostrar ocupantes");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("mostrar ocupantes");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        j10.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j10.setText("10");
        j10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j10MousePressed(evt);
            }
        });
        jPanel1.add(j10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, -1, -1));

        j12.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j12.setText("12");
        j12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j12MousePressed(evt);
            }
        });
        jPanel1.add(j12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 410, -1, -1));

        j5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j5.setText("5");
        j5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j5MousePressed(evt);
            }
        });
        jPanel1.add(j5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 580, -1, -1));

        j27.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j27.setText("27");
        j27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j27MousePressed(evt);
            }
        });
        jPanel1.add(j27, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, -1));

        j7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j7.setText("7");
        j7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j7MousePressed(evt);
            }
        });
        jPanel1.add(j7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, -1, -1));

        j29.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j29.setText("29");
        j29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j29MousePressed(evt);
            }
        });
        jPanel1.add(j29, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        j20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j20.setText("20");
        j20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j20MousePressed(evt);
            }
        });
        jPanel1.add(j20, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 540, -1, -1));

        j3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j3.setText("3");
        j3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j3MousePressed(evt);
            }
        });
        jPanel1.add(j3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, -1, -1));

        j31.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j31.setText("31");
        j31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j31MousePressed(evt);
            }
        });
        jPanel1.add(j31, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));

        j40.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j40.setText("40");
        j40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j40MousePressed(evt);
            }
        });
        jPanel1.add(j40, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        j49.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j49.setText("49");
        j49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j49MousePressed(evt);
            }
        });
        jPanel1.add(j49, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 630, -1, -1));

        j45.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j45.setText("45");
        j45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j45MousePressed(evt);
            }
        });
        jPanel1.add(j45, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, -1, -1));

        j50.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j50.setText("50");
        j50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j50MousePressed(evt);
            }
        });
        jPanel1.add(j50, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        j6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j6.setText("6");
        j6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j6MousePressed(evt);
            }
        });
        jPanel1.add(j6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, -1, -1));

        j15.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j15.setText("15");
        j15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j15MousePressed(evt);
            }
        });
        jPanel1.add(j15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 490, -1, -1));

        j2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j2.setText("2");
        j2.setComponentPopupMenu(jPopupMenu2);
        j2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        j2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j2MousePressed(evt);
            }
        });
        jPanel1.add(j2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

        j25.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j25.setText("25");
        j25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j25MousePressed(evt);
            }
        });
        jPanel1.add(j25, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, -1, -1));

        j33.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j33.setText("33");
        j33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j33MousePressed(evt);
            }
        });
        jPanel1.add(j33, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));

        j37.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j37.setText("37");
        j37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j37MousePressed(evt);
            }
        });
        jPanel1.add(j37, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, -1, -1));

        j36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j36.setText("36");
        j36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j36MousePressed(evt);
            }
        });
        jPanel1.add(j36, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 340, -1, -1));

        j48.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j48.setText("48");
        j48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j48MousePressed(evt);
            }
        });
        jPanel1.add(j48, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 380, -1, -1));

        j39.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j39.setText("39");
        j39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j39MousePressed(evt);
            }
        });
        jPanel1.add(j39, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, -1, -1));

        j38.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j38.setText("38");
        j38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j38MousePressed(evt);
            }
        });
        jPanel1.add(j38, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        j34.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j34.setText("34");
        j34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j34MousePressed(evt);
            }
        });
        jPanel1.add(j34, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, -1));

        j43.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j43.setText("43");
        j43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j43MousePressed(evt);
            }
        });
        jPanel1.add(j43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, -1, -1));

        j30.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j30.setText("30");
        j30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j30MousePressed(evt);
            }
        });
        jPanel1.add(j30, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        j42.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j42.setText("42");
        j42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j42MousePressed(evt);
            }
        });
        jPanel1.add(j42, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        j24.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j24.setText("24");
        j24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j24MousePressed(evt);
            }
        });
        jPanel1.add(j24, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        j23.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j23.setText("23");
        j23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j23MousePressed(evt);
            }
        });
        jPanel1.add(j23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        j21.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j21.setText("21");
        j21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j21MousePressed(evt);
            }
        });
        jPanel1.add(j21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, -1, -1));

        j19.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j19.setText("19");
        j19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j19MousePressed(evt);
            }
        });
        jPanel1.add(j19, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, -1, -1));

        j18.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j18.setText("18");
        j18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j18MousePressed(evt);
            }
        });
        jPanel1.add(j18, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 520, -1, -1));

        j4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j4.setText("4");
        j4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j4MousePressed(evt);
            }
        });
        jPanel1.add(j4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 530, -1, -1));

        j16.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j16.setText("16");
        j16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j16MousePressed(evt);
            }
        });
        jPanel1.add(j16, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 600, -1, -1));

        j17.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j17.setText("17");
        j17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j17MousePressed(evt);
            }
        });
        jPanel1.add(j17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, -1));

        j14.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j14.setText("14");
        j14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j14MousePressed(evt);
            }
        });
        jPanel1.add(j14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, -1, -1));

        j13.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j13.setText("13");
        j13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j13MousePressed(evt);
            }
        });
        jPanel1.add(j13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        j8.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j8.setText("8");
        j8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j8MousePressed(evt);
            }
        });
        jPanel1.add(j8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 430, -1, -1));

        j9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j9.setText("9");
        j9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j9MousePressed(evt);
            }
        });
        jPanel1.add(j9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, -1));

        j11.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j11.setText("11");
        j11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j11MousePressed(evt);
            }
        });
        jPanel1.add(j11, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 560, -1, -1));

        j46.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j46.setText("46");
        j46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j46MousePressed(evt);
            }
        });
        jPanel1.add(j46, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 560, -1, -1));

        j35.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j35.setText("35");
        j35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j35MousePressed(evt);
            }
        });
        jPanel1.add(j35, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        j41.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j41.setText("41");
        j41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j41MousePressed(evt);
            }
        });
        jPanel1.add(j41, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, -1));

        j32.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j32.setText("32");
        j32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j32MousePressed(evt);
            }
        });
        jPanel1.add(j32, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, -1, -1));

        j28.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j28.setText("28");
        j28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j28MousePressed(evt);
            }
        });
        jPanel1.add(j28, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, -1, -1));

        j26.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j26.setText("26");
        j26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j26MousePressed(evt);
            }
        });
        jPanel1.add(j26, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, -1, -1));

        j1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j1.setText("1");
        j1.setComponentPopupMenu(jPopupMenu1);
        j1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j1MousePressed(evt);
            }
        });
        jPanel1.add(j1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, -1, -1));

        j22.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j22.setText("22");
        j22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j22MousePressed(evt);
            }
        });
        jPanel1.add(j22, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, -1));

        j44.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j44.setText("44");
        j44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j44MousePressed(evt);
            }
        });
        jPanel1.add(j44, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 530, -1, -1));

        j47.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        j47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/true1x.png"))); // NOI18N
        j47.setText("47");
        j47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                j47MousePressed(evt);
            }
        });
        jPanel1.add(j47, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/mapaxl.jpg"))); // NOI18N
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 1070, 690));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
public void ActualizarOcupacion() throws SQLException {
        int i = 1;
       /* while (i <= 50) {
            BuscarParsela(Integer.toString(i));
            i++;
        }
        */
        prueba();
    }
    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
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

    }//GEN-LAST:event_jPanel1MouseEntered
    public void MostrarParsela(String i) throws SQLException {
        ResultSet res;
        res = controlador.BuscarParsela(i);
        if (res.next()) {
            javax.swing.JOptionPane.showMessageDialog(this, "fecha de egreso: " + res.getString("fecha_egreso") + "\n" + "quincho: " + res.getString("quincho"), "DNI: " + res.getString("documento"), javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed

        try {
            MostrarParsela("1");
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jMenuItem1MousePressed

    private void j2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j2MousePressed


    }//GEN-LAST:event_j2MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        try {
            MostrarParsela("2");
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2MousePressed
    public void traerinfo(int parsela) throws SQLException {
        Main.Modelo modelo = new Main.Modelo();
        ResultSet res;

        res = modelo.traerinfo(parsela);

        int  i = 0;
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
    private void j3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j3MousePressed
        try {
            traerinfo(3);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j3MousePressed

    private void j14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j14MousePressed
        try {
            traerinfo(14);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_j14MousePressed

    private void j1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j1MousePressed

        try {
            traerinfo(1);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j1MousePressed

    private void j12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j12MousePressed

        try {
            traerinfo(12);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j12MousePressed

    private void j4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j4MousePressed

        try {
            traerinfo(4);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j4MousePressed

    private void j5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j5MousePressed

        try {
            traerinfo(5);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j5MousePressed

    private void j6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j6MousePressed

        try {
            traerinfo(6);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j6MousePressed

    private void j7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j7MousePressed

        try {
            traerinfo(7);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j7MousePressed

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

    private void j10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j10MousePressed

        try {
            traerinfo(10);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j10MousePressed

    private void j11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j11MousePressed

        try {
            traerinfo(11);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j11MousePressed

    private void j13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j13MousePressed

        try {
            traerinfo(13);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j13MousePressed

    private void j15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j15MousePressed

        try {
            traerinfo(15);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j15MousePressed

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

    private void j18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j18MousePressed

        try {
            traerinfo(18);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j18MousePressed

    private void j19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j19MousePressed

        try {
            traerinfo(19);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j19MousePressed

    private void j20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j20MousePressed

        try {
            traerinfo(20);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j20MousePressed

    private void j21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j21MousePressed

        try {
            traerinfo(21);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j21MousePressed

    private void j22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j22MousePressed

        try {
            traerinfo(22);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j22MousePressed

    private void j23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j23MousePressed

        try {
            traerinfo(23);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j23MousePressed

    private void j24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j24MousePressed

        try {
            traerinfo(24);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j24MousePressed

    private void j25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j25MousePressed

        try {
            traerinfo(25);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j25MousePressed

    private void j26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j26MousePressed

        try {
            traerinfo(26);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j26MousePressed

    private void j27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j27MousePressed

        try {
            traerinfo(27);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j27MousePressed

    private void j28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j28MousePressed

        try {
            traerinfo(28);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j28MousePressed

    private void j29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j29MousePressed

        try {
            traerinfo(29);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j29MousePressed

    private void j30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j30MousePressed

        try {
            traerinfo(30);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j30MousePressed

    private void j31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j31MousePressed

        try {
            traerinfo(31);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j31MousePressed

    private void j32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j32MousePressed

        try {
            traerinfo(32);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j32MousePressed

    private void j33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j33MousePressed

        try {
            traerinfo(33);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j33MousePressed

    private void j34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j34MousePressed

        try {
            traerinfo(34);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j34MousePressed

    private void j35MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j35MousePressed

        try {
            traerinfo(35);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j35MousePressed

    private void j36MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j36MousePressed

        try {
            traerinfo(36);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j36MousePressed

    private void j37MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j37MousePressed

        try {
            traerinfo(37);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j37MousePressed

    private void j38MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j38MousePressed

        try {
            traerinfo(38);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j38MousePressed

    private void j39MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j39MousePressed

        try {
            traerinfo(39);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j39MousePressed

    private void j40MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j40MousePressed

        try {
            traerinfo(40);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j40MousePressed

    private void j41MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j41MousePressed

        try {
            traerinfo(41);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j41MousePressed

    private void j42MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j42MousePressed

        try {
            traerinfo(42);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j42MousePressed

    private void j43MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j43MousePressed

        try {
            traerinfo(43);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j43MousePressed

    private void j44MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j44MousePressed

        try {
            traerinfo(44);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j44MousePressed

    private void j45MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j45MousePressed

        try {
            traerinfo(45);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j45MousePressed

    private void j46MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j46MousePressed

        try {
            traerinfo(46);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j46MousePressed

    private void j47MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j47MousePressed

        try {
            traerinfo(47);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j47MousePressed

    private void j48MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j48MousePressed

        try {
            traerinfo(48);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j48MousePressed

    private void j49MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j49MousePressed

        try {
            traerinfo(49);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j49MousePressed

    private void j50MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_j50MousePressed

        try {
            traerinfo(50);
        } catch (SQLException ex) {
            Logger.getLogger(OcupacionC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_j50MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j10;
    private javax.swing.JLabel j11;
    private javax.swing.JLabel j12;
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
    private javax.swing.JLabel j6;
    private javax.swing.JLabel j7;
    private javax.swing.JLabel j8;
    private javax.swing.JLabel j9;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    // End of variables declaration//GEN-END:variables
}
