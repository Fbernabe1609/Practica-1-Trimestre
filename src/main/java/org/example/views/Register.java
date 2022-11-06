package org.example.views;

import org.example.controllers.ValidationData;
import org.example.models.DbHelper;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

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

    DbHelper db = new DbHelper();

    public Register() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (ValidationData.checkFields(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), Arrays.toString(passwordField.getPassword()), Arrays.toString(confirmPasswordField.getPassword()))) {
            if (Arrays.toString(passwordField.getPassword()).equals(Arrays.toString(confirmPasswordField.getPassword()))) {

                switch (db.checkRegister(usernameField.getText(), emailField.getText())) {
                    case 0 -> {
                        db.addDataDocument(nameField.getText(), surnameField.getText(), usernameField.getText(), emailField.getText(), Arrays.toString(passwordField.getPassword()));
                        try {
                            Thread.sleep(1000);
                            DataViewer.user = db.viewDocument(usernameField.getText(), Arrays.toString(passwordField.getPassword()));
                            dispose();
                            StartViews.startDataViewer();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case 1 ->
                            JOptionPane.showMessageDialog(this, "Ya hay una cuenta con ese nombre de usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    case 2 ->
                            JOptionPane.showMessageDialog(this, "Ya hay una cuenta con ese email", "Error", JOptionPane.ERROR_MESSAGE);
                    default ->
                            JOptionPane.showMessageDialog(this, "Ya hay una cuenta con los datos introducidos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "La contraseña y su confirmación no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
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
