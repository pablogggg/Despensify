package Forms;

import data.DBOperations;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public final class LoginForm implements ActionListener {

    JFrame frame;
    JLabel userLabel = new JLabel("User");
    JLabel passwordLabel = new JLabel("Password");
    JTextField userTextField = new JTextField();
    JTextField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton goToRegisterButton = new JButton("Create New Account");

    //La siguiente variable guarda el usuario de esta sesion en una variable que
    // nos servirá para identificarlo en la pantalla Panel de Usuario.
    public static String userSession;
    //La variable siguiente sirve para guardar el id_usuario de la tabla user
    //correspondiente al usuario que se ha logeado.
    public static String thisSessionUserId;

    //Getters y setters para las dos variables anteriorse
    public static String getUserSession() {
        return userSession;
    }

    public static void setUserSession(String userSession) {
        LoginForm.userSession = userSession;
    }

    public static String getThisSessionUserId() {
        return thisSessionUserId;
    }

    public static void setThisSessionUserId(String thisSessionUserId) {
        LoginForm.thisSessionUserId = thisSessionUserId;
    }

    //Constructor de la clase. Usa 4 metodos para generar el formulario
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

    //Metodo infobox para mostrar un mensaje cuando el login no sea correcto
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public void actionEvent() {
        goToRegisterButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    //El siguiente boton sirve para pasar al formulario de REGISTRO si aun no
    //disponemos de cuenta
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goToRegisterButton) {
            try {
                frame.dispose();
                RegisterForm registerForm = new RegisterForm();

            } catch (Exception e1) {
                System.out.println("Fallo al abrir el formulario Register");
            }
        }
        
        //Boton para realizar el login
        if (e.getSource() == loginButton) {

            Boolean loginOk = DBOperations.loginMaker(userTextField.getText(), passwordField.getText());

            if (loginOk) {
                frame.dispose();
                passwordField.setText("");
            } else {
                infoBox("Incorrect username or password entered", "Login failed");
            }
        }
    }
}