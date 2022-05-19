package data;

import java.sql.*;

//Esta ya es la clase de Conexion a BD para todo el proyecto Despensify
public class DBConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/despensify?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "union";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
            
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
           
    public static void close(PreparedStatement smtm) throws SQLException{
        smtm.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}