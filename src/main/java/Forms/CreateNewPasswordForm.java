package Forms;

import static Forms.LoginForm.*;
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
        currentPasswordLabel.setBounds(30, 20, 130, 70);
        currentPasswordTextField.setBounds(180, 43, 165, 23);
        newPasswordLabel.setBounds(30, 60, 90, 70);
        newPasswordTextField.setBounds(180, 80, 165, 23);
        createNewPasswordButton.setBounds(120, 120, 170, 30);
        backToUserPanelButton.setBounds(120, 160, 170, 30);
    }

    public void addComponentsToFrame() {
        frame.add(currentPasswordLabel);
        frame.add(newPasswordLabel);
        frame.add(currentPasswordTextField);
        frame.add(newPasswordTextField);
        frame.add(createNewPasswordButton);
        frame.add(backToUserPanelButton);
    }

    public void actionEvent() {
        createNewPasswordButton.addActionListener(this);
        backToUserPanelButton.addActionListener(this);
    }

    //Metodo de prueba para mostrar un mensaje al actualizar la contrasenna
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToUserPanelButton) {
            try {
                frame.dispose();
                new UserPanelForm();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (e.getSource() == createNewPasswordButton) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/despensify";
            String username = "root";
            String password = "union";
            String newPassword = newPasswordTextField.getText();
            String currentPassword = currentPasswordTextField.getText();
            String sql = "update user set password = '" + newPassword + "' where password = '" + currentPassword + "' ";

            try ( Connection conn = DriverManager.getConnection(jdbcUrl, username, password);  Statement stmt = conn.createStatement();) {

                stmt.executeUpdate(sql);
                LoginForm.setPasswordSession(newPassword);

                //Los dos siguientes setText vacian los dos text field
                currentPasswordTextField.setText("");
                newPasswordTextField.setText("");
                
                //Infobox que se muestra al usuario si el cambio es correcto
                infoBox("Password succesfully updated", "Password succesfully updated");

            } catch (Exception e1) {
                //Rellenar
            }
        }
    }
}
