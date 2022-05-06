package Forms;


import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class LoginForm implements ActionListener {

	JFrame frame;
	JLabel userLabel = new JLabel("User");
	JLabel passwordLabel = new JLabel("Password");
	JTextField userTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField confirmPasswordField = new JPasswordField();
	JButton loginButton = new JButton("Login");
	JButton goToRegisterButton = new JButton("Create New Account");

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

	}

}
