
package Forms;

import Forms.LoginForm;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class UserPanelForm implements ActionListener {
	JFrame frame;
	JLabel userLabel=new JLabel("User: ");
	JLabel passwordLabel=new JLabel("Password: ");
	JLabel currentUserTextField=new JLabel(LoginForm.userSession );
	JLabel currentPasswordField=new JLabel(LoginForm.getPasswordSession());
	JButton goToUpdatePasswordButton=new JButton("Update Password Menu");
	JButton backToAppButton=new JButton("Back to the Application");

	public UserPanelForm(){
            createWindow();
            setLocationAndSize();
            addComponentsToFrame();
            actionEvent();
	}
        
	public void createWindow(){
            frame=new JFrame();
            frame.setTitle("User Panel");
            frame.setBounds(40,40,340,280);
            frame.getContentPane().setBackground(Color.pink);
            frame.getContentPane().setLayout(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
	}
        
	public void setLocationAndSize(){
            userLabel.setBounds(30,20,40,70);
            passwordLabel.setBounds(30,70,80,70);
            currentUserTextField.setBounds(100,43,165,23);
            currentPasswordField.setBounds(100,93,165,23);
            goToUpdatePasswordButton.setBounds(60,140,190,25);
            backToAppButton.setBounds(60,180,190,25);
	}
        
	public void addComponentsToFrame(){
            frame.add(userLabel);
            frame.add(passwordLabel);
            frame.add(currentUserTextField);
            frame.add(currentPasswordField);
            frame.add(backToAppButton);
            frame.add(goToUpdatePasswordButton);
	}
        
	public void actionEvent(){
            backToAppButton.addActionListener(this);
            goToUpdatePasswordButton.addActionListener(this);
	}
    
    
	//El siguiente boton debe servirnos pa volver a la MainAppForm
	@Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backToAppButton) {
                        try {
                            frame.dispose();
                            //A continuacion el nombre del MainAppForm cuando lo tenga
                            new MainAppForm();
                        } catch (Exception e1) {
                        e1.printStackTrace();
                        }
                }
                if (e.getSource() == goToUpdatePasswordButton) {
                    try{
                        frame.dispose();
                        new CreateNewPasswordForm();
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                }
            }
}
