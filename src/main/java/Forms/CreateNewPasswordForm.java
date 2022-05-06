
package Forms;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class CreateNewPasswordForm implements ActionListener {

	JFrame frame;
	JLabel currentPasswordLabel = new JLabel("Current Password");
	JLabel newPasswordLabel = new JLabel("New Password");
	JTextField currentPasswordTextField = new JTextField();
	JTextField newPasswordTextField = new JTextField();
	JButton createNewPasswordButton = new JButton("Create New Password");

	public CreateNewPasswordForm() {

    	createWindow();
    	setLocationAndSize();
    	addComponentsToFrame();
    	actionEvent();
	}

	public void createWindow() {
    	frame = new JFrame();
    	frame.setTitle("Create New Password");
    	frame.setBounds(40, 40, 440, 220);
    	frame.getContentPane().setBackground(Color.pink);
    	frame.getContentPane().setLayout(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
	}

	public void setLocationAndSize() {
    	currentPasswordLabel.setBounds(30, 20, 130, 70);
    	currentPasswordTextField.setBounds(180, 43, 165, 23);
    	newPasswordLabel.setBounds(30, 60, 90, 70);
    	newPasswordTextField.setBounds(180, 80, 165, 23);
    	createNewPasswordButton.setBounds(120, 120, 170, 30);

	}

	public void addComponentsToFrame() {
    	frame.add(currentPasswordLabel);
    	frame.add(newPasswordLabel);
    	frame.add(currentPasswordTextField);
    	frame.add(newPasswordTextField);
    	frame.add(createNewPasswordButton);
	}

	public void actionEvent() {
    	createNewPasswordButton.addActionListener(this);
	}

	//Hay que editar este metodo para que un botón te registre y el otro
	@Override
	public void actionPerformed(ActionEvent e) {
    	throw new UnsupportedOperationException("Not supported yet.");
	}

}
