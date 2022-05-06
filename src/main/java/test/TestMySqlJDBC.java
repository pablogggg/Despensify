//package test;
//
//import java.sql.*;
//
//public class TestMySqlJDBC {
//    public static void main(String[] args) {
//        //Cadena de conexion para conectarnos a MySQL a través de una variable:
//        //en el futuro habrá que pasarla al metodo main de la app
//        var url = "jdbc:mysql://localhost:3306/despensify?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
//        try{
//            Connection conexion = DriverManager.getConnection(url, "root", "root");
//            //Ahora abajo al lado izq una interface, dcha una clase que la implementa
//            Statement instruccion = conexion.createStatement();
//            var sql = "SELECT id_usuario, nombre, password FROM usuario";
//            //8usamos el inter4face resultset
//            ResultSet resultado = instruccion.executeQuery(sql);
//            while(resultado.next()){
//                System.out.println("Id usuario: " +resultado.getInt("id_usuario"));
//                System.out.println("Nombre login: " + resultado.getString("nombre"));
//                System.out.println("Password : " + resultado.getString("password"));
//                
//            }
//            
//            //cerramos todo
//            resultado.close();
//            instruccion.close();
//            conexion.close();
//        }catch (SQLException ex){
//            ex.printStackTrace(System.out);
//        }
//    }
//}
