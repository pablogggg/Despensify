package data;

import Forms.DespensifyForm;
import Forms.LoginForm;
import static Forms.LoginForm.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBOperations {

    //funcion para realizar el login
    public static Boolean loginMaker(String a, String b) {
        try {
            //Class.forname registra el Driver. En teoria se hace 1 sola vez
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
        }
        try ( Connection connection = DBConnection.getConnection()) {
            PreparedStatement Pstatement = connection.prepareStatement("SELECT * FROM user WHERE username=? and password=SHA2(?, 256)");

            Pstatement.setString(1, a);
            Pstatement.setString(2, b);

            Pstatement.executeQuery();
            //Vaciamos la variable por seguridad (esto habria que pasarlo ahora a la clase desde la que se llama a este metodo, después de finalizarlo)
            //passwordField.setText("");

            //Guardo el usuario introducido en la variable 
            //userSession que declaré arriba que será la que guarde el 
            //usuario durante toda la sesion
            LoginForm.userSession = a;

            ResultSet rs;
            rs = Pstatement.executeQuery();

            //El metodo comprueba si el usuario introducido esta vacio o es 
            //nulo, en cuyo caso impide el login
            if (isUsernameEmpty(a)) {
                System.out.println("Empty or null ");
            } else if (rs.next()) {

                java.sql.PreparedStatement preparedStatement;
                String query = "select user_id from user where username=?";

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, userSession);
                ResultSet rs2 = preparedStatement.executeQuery();
                LoginForm.thisSessionUserId = null;
                if (rs2.next()) {
                    thisSessionUserId = rs2.getString(1);
                }

                //if username and password are true then go to mainpage
                //frame.dispose();
                java.awt.EventQueue.invokeLater(() -> {
                    new DespensifyForm().setVisible(true);
                });
                return true;
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //funcion para actualizar la contrasenna
    public static boolean passwordUpdater(String newPassword, String username, String oldPassword) {
        try {
            Connection conn;
            conn = DBConnection.getConnection();
            PreparedStatement Pstatement = conn.prepareStatement("update user set password=SHA2(?, 256) where username=? AND password=SHA2(?, 256)");

            Pstatement.setString(1, newPassword);
            Pstatement.setString(2, username);
            Pstatement.setString(3, oldPassword);
            Pstatement.executeUpdate();

            //Los dos setText vacian los dos text field por seguridad despues 
            //de que se introduzcan el usuario y nueva contrasena 
//                currentPasswordTextField.setText("");
//                newPasswordTextField.setText("");
            //Infobox que se muestra al usuario si el cambio sale bien
            infoBox("Password succesfully updated", "Password succesfully updated");

        } catch (SQLException e1) {
            //Rellenar
            infoBox("Error updating password", "Error updating");
        }
        return false;
    }

    //funcion para realizar registros
    public static boolean Registerer(String user, String password) {
        try {
            Connection conn;
            conn = DBConnection.getConnection();
            PreparedStatement Pstatement = conn.prepareStatement("INSERT into user(username, password) values(?, SHA2(?, 256))");

            Pstatement.setString(1, user);
            Pstatement.setString(2, password);
            Pstatement.executeUpdate();

            //Los dos siguientes setText vacian los dos text field
//                userTextField.setText("");
//                passwordField.setText("");
            //Llamamos al metodo que musetra la etiqueta de registro correcto
            infoBox("Nuevo usuario registrado", "Registrado correctamente");

        } catch (SQLException e1) {
            System.out.println("SQL Exception in Register");
        }
        return false;
    }

    //Metodo para saber si el usuario introducido está vacío o es nulo.
    public static boolean isUsernameEmpty(String username) {
        String empty = "";

        if (username == (null) || username.equals(empty)) {
            return true;
        } else {
            return false;
        }
    }

}
