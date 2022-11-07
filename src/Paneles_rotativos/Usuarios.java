/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Paneles_rotativos;

import Main.Controlador;
import Main.Main;
import static Main.Main.controlador;
import Paneles_principales.Login;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lauti
 */
public class Usuarios extends javax.swing.JPanel {

    int filas_usuarios = 0;
    Controlador controlador = new Controlador();

    public Usuarios() {

        initComponents();

    }

    public void RegistrarTarifa() throws java.text.ParseException {

        try {
            if (alumno_t.getText().isEmpty() && alumno_dia.getText().isEmpty() && particular_dia.getText().isEmpty() && particular_t.getText().isEmpty() && aportante_t.getText().isEmpty() && aportante_dia.getText().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, "Porfavor complete almenos un campo", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            } else {

                if (!alumno_t.getText().isEmpty()) {
                    if (Float.parseFloat(alumno_t.getText()) > 0.0) {
                        controlador.IngresoTarifaAlumno(Float.parseFloat(alumno_t.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                if (!alumno_dia.getText().isEmpty()) {
                    if (Float.parseFloat(alumno_dia.getText()) > 0.0) {
                        controlador.IngresoTarifaAlumno_dia(Float.parseFloat(alumno_dia.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                if (!particular_t.getText().isEmpty()) {
                    if (Float.parseFloat(particular_t.getText()) > 0.0) {
                        controlador.IngresoTarifaParticular(Float.parseFloat(particular_t.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                if (!particular_dia.getText().isEmpty()) {
                    if (Float.parseFloat(particular_dia.getText()) > 0.0) {
                        controlador.IngresoTarifaParticular_dia(Float.parseFloat(particular_dia.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                if (!aportante_t.getText().isEmpty()) {
                    if (Float.parseFloat(aportante_t.getText()) > 0.0) {
                        controlador.IngresoTarifaAportante(Float.parseFloat(aportante_t.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                if (!aportante_dia.getText().isEmpty()) {
                    if (Float.parseFloat(aportante_dia.getText()) > 0.0) {
                        controlador.IngresoTarifaAportante_dia(Float.parseFloat(aportante_dia.getText()));
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(this, "Error con el ingreso de datos, Intente nuevamente.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            }
        } catch (java.lang.NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Hubo un ingreso de letras donde corresponden numeros.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }
        particular_dia.setText(null);
        alumno_dia.setText(null);
        aportante_dia.setText(null);
        particular_t.setText(null);
        alumno_t.setText(null);
        aportante_t.setText(null);
    }

    public void actualizarUsuario() {
        jLabel2.setText("NOMBRE DE USUARIO: " + Login.usuario);
        jLabel3.setText("PRIVILEGIO: " + Main.privilegio);

        if (Main.privilegio != 1) {
            jButton1.setEnabled(false);
            particular_dia.setEnabled(false);
            alumno_dia.setEnabled(false);
            aportante_dia.setEnabled(false);
            particular_t.setEnabled(false);
            alumno_t.setEnabled(false);
            aportante_t.setEnabled(false);
            nombre.setEnabled(false);
            contra.setEnabled(false);
            jComboBox1.setEnabled(false);
            confcontra.setEnabled(false);
            jButton2.setEnabled(false);
            tabla.setEnabled(false);
            boton_e.setEnabled(false);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        alumno_dia = new javax.swing.JTextField();
        particular_dia = new javax.swing.JTextField();
        aportante_dia = new javax.swing.JTextField();
        alumno_t = new javax.swing.JTextField();
        aportante_t = new javax.swing.JTextField();
        particular_t = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        contra = new javax.swing.JPasswordField();
        confcontra = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tabla = new javax.swing.JButton();
        boton_e = new javax.swing.JButton();

        jLabel2.setText("NOMBRE DE USUARIO: "+Login.usuario);

        jLabel3.setText("PRIVILEGIO:");

        jLabel4.setText("TARIFAS POR DIA:");

        jLabel5.setText("Alumno:");

        jLabel6.setText("Particular:");

        jLabel7.setText("Aportante:");

        alumno_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumno_diaActionPerformed(evt);
            }
        });

        particular_dia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                particular_diaActionPerformed(evt);
            }
        });

        jLabel12.setText("Aportante:");

        jLabel13.setText("Particular:");

        jLabel14.setText("Alumno:");

        jLabel15.setText("TARIFAS PARA ACAMPAR:");

        jButton1.setText("ENVIAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jLabel1.setText("MODIFICAR TARIFAS:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(aportante_dia)
                                    .addComponent(particular_dia)
                                    .addComponent(alumno_dia))))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(alumno_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(aportante_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(particular_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel1))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(alumno_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(particular_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(aportante_t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(alumno_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(particular_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(aportante_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("AGREGAR NUEVO USUARIO:");

        jLabel9.setText("Nombre:");

        jLabel10.setText("Contraseña:");

        jLabel11.setText("Confirmar Contraseña:");

        jButton2.setText("ENVIAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", " " }));

        jLabel16.setText("Privilegio:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, 161, Short.MAX_VALUE)
                            .addComponent(confcontra)
                            .addComponent(contra)
                            .addComponent(nombre)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(8, 8, 8)))
                .addGap(104, 104, 104))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(contra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(confcontra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(25, 25, 25)
                .addComponent(jButton2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(356, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AGREGAR NUEVO USUARIO", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Privilegio"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tabla.setText("Consultar tabla");
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaMousePressed(evt);
            }
        });

        boton_e.setText("ELIMINAR");
        boton_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boton_eMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(boton_e)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tabla)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tabla)
                    .addComponent(boton_e))
                .addContainerGap(307, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ELIMINAR USUARIO", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        try {
            RegistrarTarifa();
            controlador.setear_tarifas();
        } catch (ParseException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MousePressed

    private void alumno_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumno_diaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alumno_diaActionPerformed

    private void particular_diaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_particular_diaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_particular_diaActionPerformed
    public void TablaUsuario() throws SQLException {

        ResultSet res_1;
        res_1 = controlador.MostrarUsuarios();
        BusquedaUsuario(res_1);
    }

    public void BusquedaUsuario(ResultSet res) throws SQLException {

        int f = 0;
        while (res.next()) {

            f++;
            String usuario = res.getString("usuario");
            String privilegios = res.getString("privilegios");
            String tab[] = {usuario, privilegios};
            DefaultTableModel tablamodelo = (DefaultTableModel) jTable1.getModel();

            tablamodelo.addRow(tab);
            filas_usuarios = f;
            int rows = tablamodelo.getRowCount();
            for (int i = rows - (filas_usuarios + 1); i >= 0; i--) {
                tablamodelo.removeRow(i);
            }

        }

    }

    public void RegistrarUsuario() {

        if (!nombre.getText().isEmpty() && !contra.getPassword().toString().isEmpty() && !jComboBox1.getSelectedItem().toString().isEmpty()) {

            if ((Arrays.equals(contra.getPassword(), confcontra.getPassword()))) {

                controlador.NuevoUsuario(nombre.getText(), String.valueOf(contra.getPassword()), jComboBox1.getSelectedItem().toString());

                nombre.setText(null);
                contra.setText(null);
                confcontra.setText(null);
            } else {

                javax.swing.JOptionPane.showMessageDialog(this, "Error, Las contraseñas no coinciden.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Porfavor, complete todos los campos.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }
    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed

        RegistrarUsuario();


    }//GEN-LAST:event_jButton2MousePressed

    private void tablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMousePressed
        if (Main.privilegio == 1) {

            try {
                TablaUsuario();
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR, no puede acceder a la tabla de usuarios.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_tablaMousePressed

    public void BorrarUsuario() throws SQLException {

        String usuario = jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString();

        if (!Login.usuario.equals(usuario)) {
            controlador.EliminarUsuario(usuario);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR, no se puede eliminar un usuario que este usando el sistema.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

    }


    private void boton_eMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_eMousePressed

        try {

            BorrarUsuario();

            TablaUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "ERROR, No tiene permitido eliminar.", "ERROR", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        }


    }//GEN-LAST:event_boton_eMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alumno_dia;
    private javax.swing.JTextField alumno_t;
    private javax.swing.JTextField aportante_dia;
    private javax.swing.JTextField aportante_t;
    private javax.swing.JButton boton_e;
    private javax.swing.JPasswordField confcontra;
    private javax.swing.JPasswordField contra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField particular_dia;
    private javax.swing.JTextField particular_t;
    private javax.swing.JButton tabla;
    // End of variables declaration//GEN-END:variables

}
