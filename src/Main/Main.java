/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;


import com.formdev.flatlaf.FlatLightLaf;
import Paneles_principales.Login;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author lauti
 */
public class Main {
    public static Controlador controlador = new Controlador();
    public static Conexion conexion = new Conexion();
    public static String privilegio;
    public static String DiaActual = LocalDate.now().toString();
    public static String HoraActual = LocalTime.now().toString();
    public static float tarfia_acampar_alumnos, tarfia_acampar_aportantes, tarfia_acampar_particular,
            tarifa_dia_alumnos,tarifa_dia_aportantes,tarifa_dia_particular;
    public static Calendar calendario = Calendar.getInstance();
    

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        
        
        conexion.Conectar();
        controlador.setear_tarifas();
        System.out.println(calendario.get(Calendar.HOUR_OF_DAY));
        String hora = null;
    
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        FlatLightLaf.setup();

        /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         */
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(
                new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        }
        );

    }

}
