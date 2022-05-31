package Forms;

import data.DBConnection;
import data.DBOperations;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;


public final class CreateNewPasswordForm implements ActionListener {

    JFrame frame;
    JLabel currentUserLabel = new JLabel("Current user");
    JLabel currentPasswordLabel = new JLabel("Current Password");
    JLabel newPasswordLabel = new JLabel("New Password");
    JTextField currentUserTextField = new JTextField();
    //En este textfield se escribe la contrasena actual
    JTextField currentPasswordTextField = new JPasswordField();
    //En este textfield se escribe la contrasena nueva que se quiere
    JTextField newPasswordTextField = new JPasswordField();
    JButton createNewPasswordButton = new JButton("Create New Password");
    JButton backToUserPanelButton = new JButton("Back to User Panel");

    public CreateNewPasswordForm() {
        createWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }

    public void createWindow() {
        frame = new JFrame();
        frame.setTitle("Create New Password");
        frame.setBounds(40, 40, 440, 260);
        frame.getContentPane().setBackground(Color.pink);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void setLocationAndSize() {
        currentUserLabel.setBounds(30, -10, 130, 70);
        currentUserTextField.setBounds(180, 13, 165, 23);
        currentPasswordLabel.setBounds(30, 20, 130, 70);
        currentPasswordTextField.setBounds(180, 43, 165, 23);
        newPasswordLabel.setBounds(30, 50, 90, 70);
        newPasswordTextField.setBounds(180, 73, 165, 23);
        createNewPasswordButton.setBounds(120, 120, 170, 30);
        backToUserPanelButton.setBounds(120, 160, 170, 30);
    }

    public void addComponentsToFrame() {
        frame.add(currentUserLabel);
        frame.add(currentPasswordLabel);
        frame.add(newPasswordLabel);
        frame.add(currentUserTextField);
        frame.add(currentPasswordTextField);
        frame.add(newPasswordTextField);
        frame.add(createNewPasswordButton);
        frame.add(backToUserPanelButton);
    }

    public void actionEvent() {
        createNewPasswordButton.addActionListener(this);
        backToUserPanelButton.addActionListener(this);
    }

    //Metodo utilizado para mostrar un mensaje al actualizar la contrasena
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToUserPanelButton) {
            try {
                frame.dispose();
                UserPanelForm userPanelForm = new UserPanelForm();

            } catch (Exception e1) {
                System.out.println("Error al volver a Panel de Usuario");
            }
        }

        if (e.getSource() == createNewPasswordButton) {
            String newUser = currentUserTextField.getText();
            String newPassword = newPasswordTextField.getText();
            String currentPassword = currentPasswordTextField.getText();
            
             Boolean passwordOk = DBOperations.passwordUpdater(newPasswordTextField.getText(), currentUserTextField.getText(), currentPasswordTextField.getText());

            if (passwordOk) {
                frame.dispose();
                currentPasswordTextField.setText("");
            } else {
                
            }
        }
    }
}
