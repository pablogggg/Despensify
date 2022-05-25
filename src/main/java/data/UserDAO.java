package data;

import static data.DBConnection.*;
import domain.User;
import java.sql.*;
import java.util.*;


//La clase DAO será la que implemente la funcionalidad de la tabla user.
public class UserDAO {
    
    //Generamos 4 variables para las operaciones SQL
    //Los 4 ? son los 4 campos a partir de user. Es importante
    //que sus indices son 1, 2, 3 y 4 (y 5) en orden. 
   
    private static final String SQL_SELECT = "SELECT user_id, username, password FROM user";
    private static final String SQL_INSERT = "INSERT INTO user (username, password) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET username = ?, password = ? WHERE user_id = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE user_id = ?";

    //Definimos un metodo SELECCIONAR que devolvera una lista de 
    //datos de tipo user
    public List<User> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            //ejecutamos el query
            rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                //linea clave para convertir objetos a BD
                user = new User(userId, username, password);

                //el objeto tipo persona se agrega a la lista
                users.add(user);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return users;
    }
    
    //Metodo para hacer INSERT en la tabla
    public int insert(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            registros = stmt.executeUpdate();
            //la linea anterrior sirve para actualizar el estado de la BD

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserId());
            registros = stmt.executeUpdate();
            //lo de arriba para que actualice el estado de la BD
            System.out.println("Registros actualizados: " + registros);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int delete(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, user.getUserId());
            registros = stmt.executeUpdate();
            //la linea anteriro sirve para actualizar el estado de la BD

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
