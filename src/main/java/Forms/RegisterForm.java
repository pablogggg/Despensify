
package Forms;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class RegisterForm implements ActionListener {
	JFrame frame;
	JLabel topLabel=new JLabel("Please choose your username and password");
	JLabel userLabel=new JLabel("User");
	JLabel passwordLabel=new JLabel("Password");
	JTextField userTextField=new JTextField();
	JPasswordField passwordField=new JPasswordField();
	JPasswordField confirmPasswordField=new JPasswordField();
	JButton registerButton=new JButton("Register");
	JButton goToLoginButton=new JButton("Already have an account?");

	public RegisterForm()
	{
    	createWindow();
    	setLocationAndSize();
    	addComponentsToFrame();
    	actionEvent();
	}
	public void createWindow()
	{
    	frame=new JFrame();
    	frame.setTitle("Register");
    	frame.setBounds(40,40,340,280);
    	frame.getContentPane().setBackground(Color.pink);
    	frame.getContentPane().setLayout(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
	}
	public void setLocationAndSize()
	{
    	topLabel.setBounds(20, 0, 290, 40);
    	userLabel.setBounds(30,20,40,70);
    	passwordLabel.setBounds(30,70,80,70);
    	userTextField.setBounds(100,43,165,23);
    	passwordField.setBounds(100,93,165,23);
    	goToLoginButton.setBounds(60,140,190,25);
    	registerButton.setBounds(60,180,190,25);

	}
	public void addComponentsToFrame()
	{
    	frame.add(topLabel);
    	frame.add(userLabel);
    	frame.add(passwordLabel);
    	frame.add(userTextField);
    	frame.add(passwordField);
    	frame.add(registerButton);
    	frame.add(goToLoginButton);
	}
	public void actionEvent()
	{
    	registerButton.addActionListener(this);
    	goToLoginButton.addActionListener(this);
	}
    
    
	//El siguiente boton sirve para pasar al formulario de LOGIN
	@Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == goToLoginButton) {
        	try {
            	frame.dispose();
            	new LoginForm();

        	} catch (Exception e1) {
            	e1.printStackTrace();
        	}

    	}

	}

}
