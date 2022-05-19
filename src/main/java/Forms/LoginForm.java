package Forms;

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
    JTextField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton goToRegisterButton = new JButton("Create New Account");

    //La siguiente variable guarda el usuario de esta sesion en una variable que
    // nos servirá para identificarlo en la pantalla Panel de Usuario.
    private static String userSession;
    //La variable siguiente sirve para guardar el id_usuario de la tabla user
    //correspondiente al usuario que se ha logeado.
    private static String thisSessionUserId;

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
                new RegisterForm();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        //Boton para realizar el login
        if (e.getSource() == loginButton) {
            try {

                //Objeto conexion
                Class.forName("com.mysql.jdbc.Driver");
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/despensify", "root", "union")) {
                    PreparedStatement Pstatement = connection.prepareStatement("SELECT * FROM user WHERE username=? and password=SHA2(?, 256)");
                    
                    Pstatement.setString(1, userTextField.getText());
                    Pstatement.setString(2, passwordField.getText());
                    
                    Pstatement.executeQuery();
                    //Vaciamos la variable por seguridad
                    passwordField.setText("");
                    
                    //Relleno la variable usuario que declaré arriba que será la que
                    //guarde el usuario durante la sesion
                    userSession = userTextField.getText();
                    
                    ResultSet rs;
                    rs = Pstatement.executeQuery();
                    if (rs.next()) {
                        
                        
                        //Estoy trabajando en esto************
                        
                        java.sql.PreparedStatement preparedStatement = null;
                        String query = "select user_id from user where username=?";
                        
                        preparedStatement = connection.prepareStatement(query);
                        
                        preparedStatement.setString(1, userSession);
                        ResultSet rs2 = preparedStatement.executeQuery();
                        thisSessionUserId = null;
                        if(rs2.next())
                            thisSessionUserId = rs2.getString(1);
                        
                        //************************************
                        
                        
                        //if username and password are true then go to mainpage
                        frame.dispose();
                        
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new DespensifyForm().setVisible(true);
                            }
                        });
                        
                    } else {
                        //Infobox que se muestra al usuario si el login falla
                        infoBox("Login failed", "Incorrect password or username");
                    }
                }

            } catch (SQLException e1) {
                System.out.println("Fallo de tipo SQL");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }
}
