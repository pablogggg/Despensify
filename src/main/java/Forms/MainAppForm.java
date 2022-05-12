
package Forms;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class MainAppForm implements ActionListener {
	JFrame frame;
	JLabel topLabel=new JLabel("Please choose your username and password");
	JLabel userLabel=new JLabel("User");
	JLabel passwordLabel=new JLabel("Password");
	JTextField userTextField=new JTextField();
	JPasswordField passwordField=new JPasswordField();
	JPasswordField confirmPasswordField=new JPasswordField();
	JButton goToUserPanelButton=new JButton("Go to User Panel");
	JButton logoutButton=new JButton("Logout");


	public MainAppForm()
	{
    	createWindow();
    	setLocationAndSize();
    	addComponentsToFrame();
    	actionEvent();
	}
	public void createWindow()
	{
    	frame=new JFrame();
    	frame.setTitle("Despensify");
    	frame.setBounds(40,40,800,600);
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
    	goToUserPanelButton.setBounds(60,140,190,25);
    	logoutButton.setBounds(60,180,190,25);

	}
	public void addComponentsToFrame()
	{
    	frame.add(topLabel);
    	frame.add(userLabel);
    	frame.add(passwordLabel);
    	frame.add(userTextField);
    	frame.add(passwordField);
    	frame.add(logoutButton);
    	frame.add(goToUserPanelButton);
	}
	public void actionEvent()
	{
    	goToUserPanelButton.addActionListener(this);
    	logoutButton.addActionListener(this);
	}
    
    
	//El siguiente boton sirve para pasar al Panel de Usuario
	@Override
	public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == goToUserPanelButton) {
        	try {
            	frame.dispose();
            	new UserPanelForm();

        	} catch (Exception e1) {
            	e1.printStackTrace();
        	}
    	}
   	 
    	//En cambio este te deslogea y lleva al login
    	//Por el momento solo te lleva al
    	if(e.getSource() == logoutButton){
        	try {
            	frame.dispose();
            	new LoginForm();

        	} catch (Exception e2) {
            	e2.printStackTrace();
        	}
   	 
               	 

    	}

	}
    

}
