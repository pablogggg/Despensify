package data;

import Forms.DespensifyForm;
import Forms.LoginForm;
import static Forms.LoginForm.*;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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

            if (newPassword.isEmpty()) {
                infoBox("New Password field is empty", "New Password field is empty");
            } else if (username.isEmpty()) {
                infoBox("Username field is empty", "Username field is empty");
            } else if (oldPassword.isEmpty()) {
                infoBox("Current Password field is empty", "Current Password field is empty");
            } else {
                Pstatement.executeUpdate();
                infoBox("Password succesfully updated", "Password succesfully updated");
            }
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

            if (isUsernameEmpty(user) || isUsernameEmpty(password)) {
                System.out.println("Empty or null ");
                infoBox("Incorrect username or password entered", "Registration failed");
            } else {
                Pstatement.executeUpdate();

                //Llamamos al metodo que musetra la etiqueta de registro correcto
                infoBox("New User Registered", "User registration completed");
            }
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

    //Metodos para ir refactorizando la clase Despensify
    //*******************************************
    //Metodo para actualizar la BD con la info de la tabla. El metodo recibe
    //un parametro para evitar una dependencia circular entre las clases
    public static void tableUpdater(DefaultTableModel Df) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con1;
            con1 = DBConnection.getConnection();
            PreparedStatement insert;
            insert = con1.prepareStatement("SELECT * FROM item WHERE user_id=?");
            insert.setString(1, LoginForm.getThisSessionUserId());

            ResultSet rs = insert.executeQuery();
            ResultSetMetaData Rss = rs.getMetaData();
            int c = Rss.getColumnCount();

            Df.setRowCount(0);

            while (rs.next()) {
                Vector v2 = new Vector();

                for (int a = 1; a <= c; a++) {
                    v2.add(rs.getString("item_id"));
                    v2.add(rs.getString("productname"));
                    v2.add(rs.getString("quantity"));
                    v2.add(rs.getString("measurement"));
                }

                Df.addRow(v2);

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para agregar a la tabla al clicar el boton ADD
    public static void tableAdder(String productname, String quantity, String measurement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DBConnection.getConnection();
            PreparedStatement insert = con1.prepareStatement("INSERT into item(productname, quantity, measurement, user_id)values(?,?,?,?)");
            insert.setString(1, productname);
            insert.setString(2, quantity);
            insert.setString(3, measurement);
            insert.setString(4, LoginForm.getThisSessionUserId());
            insert.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para editar la tabla al clicar el boton EDITAR
    public static void tableEditter(String productname, String quantity, String measurement, int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DBConnection.getConnection();

            PreparedStatement insert = con1.prepareStatement("UPDATE item SET productname =?, quantity =?, measurement =? WHERE item_id =? ");
            insert.setString(1, productname);
            insert.setString(2, quantity);
            insert.setString(3, measurement);
            insert.setInt(4, id);
            insert.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para eliminar en la tabla al clicar el boton DELETE
    public static void tableDeletter(int id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1 = DBConnection.getConnection();
            PreparedStatement insert = con1.prepareStatement("DELETE FROM item WHERE item_id =? ");
            insert.setInt(1, id);
            insert.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DespensifyForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
