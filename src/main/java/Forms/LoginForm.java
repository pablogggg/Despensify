package Forms;

import data.DBConnection;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginForm implements ActionListener {

    JFrame frame;
    JLabel userLabel = new JLabel("User");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JTextField passwordField = new JTextField();
    JButton loginButton = new JButton("Login");
    JButton goToRegisterButton = new JButton("Create New Account");

    public static String userSession;
    //Guarda la contrasenna de esta sesion en una variable
    private static String passwordSession;
    
    

    //Hago un setter para cambiar passwordSession si actualizamos
    public static void setPasswordSession(String passwordSession) {
        LoginForm.passwordSession = passwordSession;
    }

    public static String getPasswordSession() {
        return passwordSession;
    }
    
    
  
    
    
    public LoginForm() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("Login");
        frame.setBounds(40, 40, 340, 280);
        frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(30, 20, 40, 70);
        passwordLabel.setBounds(30, 70, 80, 70);
        userTextField.setBounds(100, 43, 165, 23);
        passwordField.setBounds(100, 93, 165, 23);
        loginButton.setBounds(60, 140, 190, 25);
        goToRegisterButton.setBounds(60, 180, 190, 25);
    }

    public void addComponentsToFrame() {
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(userTextField);
        frame.add(passwordField);
        frame.add(goToRegisterButton);
        frame.add(loginButton);
    }

    public void actionEvent() {
        goToRegisterButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    //El siguiente boton sirve para pasar al formulario de REGISTRO
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goToRegisterButton) {
            try {
                frame.dispose();
                new RegisterForm();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        //A partir de aqui el boton de login
        if (e.getSource() == loginButton) {
            try {
                //Objeto conexion
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/despensify", "root", "union");
                String u = userTextField.getText();
                String c = passwordField.getText();
                
                //Relleno las 2 variables que declaré arriba que serán las que
                //guarden durante la sesion el usuario y la contrasenna
                userSession = userTextField.getText();
                passwordSession = passwordField.getText();

                Statement stm = con.createStatement();
                //mysql query to run
                String sql = "SELECT * FROM user WHERE username = '" + u + "' and password = '" + c + "' ";
                ResultSet rs = stm.executeQuery(sql);

                if (rs.next()) {
                    //if username and password are true then go to mainpage
                    frame.dispose();

                    MainAppForm mainAppForm = new Forms.MainAppForm();

                } else {
                    //if username and password is wrong show message
                    System.out.println("Error al logearse");
                }

                con.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
