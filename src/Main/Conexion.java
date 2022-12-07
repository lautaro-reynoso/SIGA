package Main;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    public String url = "jdbc:sqlserver://192.168.0.15:1433;database=db_florida;encrypt=true;trustServerCertificate=true";
    public String user = "sa";
    public String pass = "florida22";
    public java.sql.Statement s;
    public ResultSet resultado;
    public Connection conexion = null;

    Modelo modelo = new Modelo();

    public void Conectar() throws SQLException, ClassNotFoundException{
        try{
            if (conexion != null)
                return;

            conexion = DriverManager.getConnection(url,user,pass);
            if (conexion !=null)
                System.out.println("Conexion a base de datos: "+url+" ... Ok");
        }catch (SQLException e) {
            System.out.println("Problema al establecer la Conexión a la base de datos");
              
        }
        this.s = conexion.createStatement();


    }

    public void Desconectar(){
        try{
            conexion.close();
            conexion= null;
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }

    public  java.sql.ResultSet EjecutarConsultaSQL(String sql){

        try {
            resultado=s.executeQuery(sql);

        } catch (SQLException ex) {
            return null;
        }
        return resultado;
    }



    public int EjecutarOperacion(String sql){
        int respuesta = 0;
        try {
            respuesta=this.s.executeUpdate(sql);
            if(respuesta==1){
                System.out.println("Registro Guardado");
            }
            else{
                System.out.println("Ocurrio un problema al agregar el registro");

            }
        } catch(SQLException ex){
            // Mostramos toda la informacion sobre el error disponible
            System.out.println( "Error: SQLException" );
            while (ex != null) {
                System.out.println ("SQLState: " + ex.getSQLState ());
                System.out.println ("Mensaje:  " + ex.getMessage ());
                System.out.println ("ErrorCode:   " + ex.getErrorCode ());
                ex = ex.getNextException();
            }
        } catch(Exception e) {
            System.out.println("Se produjo un error inesperado:    "+e.getMessage());
        }
        return respuesta;
    }

/*
    public int InsertarIngreso(String documento, String nombre, String cod_pulsera, String categoria, String dia_ingreso, String importe){
        String sql="INSERT INTO ingreso (documento, nombre, cod_pulsera,categoria, dia_ingreso, importe) "
                + "VALUES('"+documento+"','"+nombre+"','"+cod_pulsera+"','"+categoria+"','"+dia_ingreso+"','"+importe+"')";
        return EjecutarOperacion(sql);
    }

    public int InsertarSalida(String documento, String cod_pulsera,String fecha_hora_salida, String tipo){
        String sql="INSERT INTO salida (doc, cod_pulsera, fecha_hora_salida, tipo) "
                + "VALUES('"+documento+"','"+cod_pulsera+"','"+fecha_hora_salida+"','"+tipo+"')";
        return EjecutarOperacion(sql);
    }

    public int InsertarRegreso(String cod_salida, String documento, String cod_pulsera, java.sql.Timestamp fecha_hora_salida, java.sql.Timestamp fecha_hora_regreso,String tipo){
        String sql="INSERT INTO regreso ( cod_salida, doc, cod_pulsera, fecha_hora_salida, fecha_hora_regreso, tipo) "
                + "VALUES('"+cod_salida+"','"+documento+"','"+cod_pulsera+"','"+fecha_hora_salida+"','"+fecha_hora_regreso+"','"+tipo+"')";
        return EjecutarOperacion(sql);
    }

    public int EliminarSalida(String cod_salida){
        String sql= "DELETE FROM salida WHERE id_salida='"+cod_salida+"'";
        return EjecutarOperacion(sql);
    }

    public  ResultSet BuscarEstudiante(String documento){
        String sql;
        sql="SELECT * FROM alumnos WHERE apellido = '"+documento+"'";
        return EjecutarConsultaSQL(sql);
    }

    public  ResultSet BuscarAportante(String documento){
        String sql;
        sql="SELECT * FROM aportantes WHERE doc = '"+documento+"'";
        return EjecutarConsultaSQL(sql);
    }
*/
}
