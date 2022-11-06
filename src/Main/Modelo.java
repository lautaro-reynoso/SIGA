package Main;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {

    public ResultSet BuscarPrivilegio(String usuario) {

        String sql = "SELECT * FROM usuarios where usuario = '" + usuario + "'";

        return Main.conexion.EjecutarConsultaSQL(sql);

    }

    public int InsertarIngreso(String documento, String nombre, String categoria, String dia_ingreso, String dia_egreso, String parsela, float importe) {
        String sql = "INSERT INTO ingreso (documento, nombre, categoria ,fecha_ingreso ,fecha_egreso , parsela ,importe )"
                + "VALUES('" + documento + "','" + nombre + "','" + categoria + "','" + dia_ingreso + "','" + dia_egreso + "','" + parsela + "','" + importe + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }
     public int InsertarIngresoDiario(String documento, String nombre, String hora_ingreso, String categoria) {
        String sql = "INSERT INTO ingreso_diario (nombre, dni, hora_ingreso ,categoria)"
                + "VALUES('" + nombre + "','" + documento + "','" + hora_ingreso + "','" + categoria + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public int InsertarRegistro(String usuario, String comentario, String fecha, String hora) {
        String sql = "INSERT INTO registros (usuario, comentario, fecha ,hora)"
                + "VALUES('" + usuario + "','" + comentario + "','" + fecha + "','" + hora + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public int InsertarParsela(String documento, String parsela, int quincho, String fecha_egreso) {
        String sql = "INSERT INTO parselas (documento, parsela, quincho, fecha_egreso)"
                + "VALUES('" + documento + "','" + parsela + "','" + quincho + "','" + fecha_egreso + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public int InsertarSalida(String documento, String nombre, String fecha_hora_salida, String tipo) {
        String sql = "INSERT INTO salida (doc,nombre, Hora_salida, tipo) "
                + "VALUES('" + documento + "','" + nombre + "','" + fecha_hora_salida + "','" + tipo + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public int InsertarRegreso(String cod_salida, String documento, String cod_pulsera, java.sql.Timestamp fecha_hora_salida, java.sql.Timestamp fecha_hora_regreso, String tipo) {
        String sql = "INSERT INTO regreso ( cod_salida, doc, cod_pulsera, fecha_hora_salida, fecha_hora_regreso, tipo) "
                + "VALUES('" + cod_salida + "','" + documento + "','" + cod_pulsera + "','" + fecha_hora_salida + "','" + fecha_hora_regreso + "','" + tipo + "')";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public int EliminarSalida(String documento) {
        String sql = "DELETE FROM salida WHERE doc='" + documento + "'";
        return Main.conexion.EjecutarOperacion(sql);
    }

    public ResultSet BuscarEstudiante(String documento) {
        String sql;
        sql = "SELECT * FROM alumnos WHERE documento = '" + documento + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet BuscarAportante(String documento) {
        String sql;
        sql = "SELECT * FROM aportantes WHERE doc = '" + documento + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet BuscarDocumento(String documento) {
        String sql;
        sql = "SELECT * FROM ingreso WHERE documento = '" + documento + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }
    public ResultSet BuscarDocumentoDiario(String documento) {
        String sql;
        sql = "SELECT * FROM ingreso_diario WHERE dni = '" + documento + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }
    public ResultSet BuscarParsela(String parsela) {

        String sql;
        sql = "SELECT * FROM ingreso WHERE parsela = '" + parsela + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet EliminarParsela(String parsela) {

        String sql;
        sql = "DELETE FROM parselas WHERE parsela = '" + parsela + "'";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public void ConsultarSalida() throws SQLException {

        String sql;
        ResultSet res;

        sql = "SELECT * FROM ingreso where fecha_egreso <= '" + Main.DiaActual + "'";

        res = Main.conexion.EjecutarConsultaSQL(sql);

        ArrayList<String> nombre = new ArrayList<>();
        ArrayList<String> documento = new ArrayList<>();
        ArrayList<String> categoria = new ArrayList<>();
        ArrayList<String> dia_ingreso = new ArrayList<>();
        ArrayList<String> dia_egreso = new ArrayList<>();
        ArrayList<Float> importe = new ArrayList<>();

        while (res.next()) {

            documento.add(res.getString("documento"));
            nombre.add(res.getString("nombre"));
            categoria.add(res.getString("categoria"));
            dia_ingreso.add(res.getString("fecha_ingreso"));
            dia_egreso.add(res.getString("fecha_egreso"));
            importe.add(res.getFloat("importe"));

        }

        for (int i = 0; i < documento.size(); i++) {

            String sql1 = "INSERT INTO egreso (documento, nombre, categoria ,fecha_ingreso ,fecha_egreso , importe )"
                    + "VALUES('" + documento.get(i) + "','" + nombre.get(i) + "','" + categoria.get(i) + "','" + dia_ingreso.get(i) + "','" + dia_egreso.get(i) + "','" + importe.get(i) + "')";
            int c = Main.conexion.EjecutarOperacion(sql1);

        }

        String sql2 = "DELETE FROM ingreso where fecha_egreso <= '" + Main.DiaActual + "'";
        String sql3 = "DELETE FROM parselas where fecha_egreso <= '" + Main.DiaActual + "'";
        int v = Main.conexion.EjecutarOperacion(sql2);
        int x = Main.conexion.EjecutarOperacion(sql3);

    }

 public int NuevoUsuario(String nombre, String contrasenia, String privilegios) {

        String sql;
        sql = " INSERT INTO usuarios (usuario,password, privilegios)"
                + "VALUES ('" + nombre + "','" + contrasenia + "','" + privilegios + "')";
        return Main.conexion.EjecutarOperacion(sql);

    }

    public ResultSet ValidarUsuario(String usuario, String pass) {
        String sql = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "' AND password = '" + pass + "'";

        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public int EliminarUsuario(String usuario) {
        String sql = "DELETE FROM usuarios WHERE usuario = '" + usuario + "'";

        return Main.conexion.EjecutarOperacion(sql);

    }

    public ResultSet MostarTablaAlumnos() {
        String sql;

        sql = "SELECT * FROM alumnos";

        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet MostrarParselas() {
        String sql;
        sql = "SELECT * FROM parselas";
        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet MostarOcupacionActual() {

        String sql;
        sql = "SELECT * FROM ingreso";

        return Main.conexion.EjecutarConsultaSQL(sql);

    }

    public ResultSet ConsultarSalidaTemporal(String dni) {

        String sql = "SELECT * FROM salida where doc = '" + dni + "'";

        return Main.conexion.EjecutarConsultaSQL(sql);

    }

    public ResultSet ControlarAcampante(String dni) {

        String sql = "SELECT * FROM ingreso where documento = '" + dni + "'";

        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    public ResultSet traerinfo(int parsela) {

        String sql = "SELECT * FROM ingreso WHERE parsela = '" + parsela + "'";

        return Main.conexion.EjecutarConsultaSQL(sql);
    }

    
    
    
    //float particular_d, float alumno_d, float aportante_d, float particular, float alumno,
         public int InsertarTarifaAportante( float aportante) {
        String sql = "UPDATE tarifas SET tarifa_aportante = ' "+aportante+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }
         public int InsertarTarifaParticular( float particular) {
        String sql = "UPDATE tarifas SET tarifa_particular = ' "+particular+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }
         public int InsertarTarifaAlumno( float alumno) {
        String sql = "UPDATE tarifas SET tarifa_alumno = ' "+alumno+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }
         public int InsertarTarifaAlumno_dia( float alumno) {
        String sql = "UPDATE tarifas SET alumno_d = ' "+alumno+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }      
         public int InsertarTarifaAportante_dia( float aportante) {
        String sql = "UPDATE tarifas SET aportante_d = ' "+aportante+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }
         public int InsertarTarifaParticular_dia( float particular) {
        String sql = "UPDATE tarifas SET particular_d = ' "+particular+" ' WHERE id = 8";
        return Main.conexion.EjecutarOperacion(sql);
    }
         
    public int insertarvehiculo (String patente, String marca, String importe){
        String sql = "INSERT INTO vehiculos (marca,patente,importe)"
                + "VALUES ('" + marca + "','" + patente + "','" + importe + "')";
        
       return Main.conexion.EjecutarOperacion(sql);
    }  
         
     public ResultSet tarifas (){
         String sql = "SELECT * FROM tarifas";
         
         return Main.conexion.EjecutarConsultaSQL(sql);
         
     }
     
     
}
