package org.example.views;

import org.example.controllers.DbController;
import org.example.controllers.UserController;
import org.example.controllers.ValidationData;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Register extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField surnameField;
    private JTextField nameField;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    URL url = classloader.getResource("error.gif");
    ImageIcon icono = new ImageIcon(url);
    public Register() {

        UIManager.put("OptionPane.messageForeground", Color.red);
        UIManager.put("Button.background", buttonOK.getBackground());
        UIManager.put("Button.foreground", Color.white);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (ValidationData.checkFields(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), String.valueOf(passwordField.getPassword()), String.valueOf(confirmPasswordField.getPassword()))) {
            if (ValidationData.checkPassword(String.valueOf(passwordField.getPassword()), String.valueOf(confirmPasswordField.getPassword()))) {

                if (ValidationData.checkEmail(emailField.getText())) {
                    switch (DbController.checkRegister(usernameField.getText(), emailField.getText())) {
                        case 0 -> {
                            String password = String.valueOf(passwordField.getPassword()).replaceAll(" ,]", "");
                            password = password.replace("[", "");
                            DbController.addDataDocument(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), password);
                            UserController.createUser(usernameField.getText(), password);
                            dispose();
                            StartViews.startDataViewer();
                        }
                        case 1 ->
                                JOptionPane.showMessageDialog(this, "Ya hay una cuenta con ese nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                        case 2 ->
                                JOptionPane.showMessageDialog(this, "Ya hay una cuenta con ese email", "Error", JOptionPane.ERROR_MESSAGE);
                        default ->
                                JOptionPane.showMessageDialog(this, "Ya hay una cuenta con los datos introducidos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Debe introducir un email válido", "Error", JOptionPane.ERROR_MESSAGE, icono);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La contraseña y su confirmación no coinciden", "Error", JOptionPane.ERROR_MESSAGE, icono);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE, icono);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        Register dialog = new Register();
        dialog.pack();
        dialog.setLocation(StartViews.appFrame.getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
