package org.example.views;

import org.example.controllers.DBController;
import org.example.controllers.UserController;
import org.example.controllers.ValidationData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog implements Commons {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel askDataPanel;
    private JPanel buttonsPanel;
    private JPanel contentButtonPanel;

    Border border = BorderFactory.createLineBorder(buttonOK.getBackground());
    Border borderDefault = usernameField.getBorder();

    public Login() {

        buttonCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonOK.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        focusEvent(usernameField, border, borderDefault);
        focusEvent(passwordField, border, borderDefault);
    }

    private void onOK() {
        if (ValidationData.checkFields(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
            String password = String.valueOf(passwordField.getPassword()).replaceAll(" ,]", "");
            password = password.replace("[", "");
            if (DBController.searchUser(usernameField.getText(), password)) {
                UserController.createUser(usernameField.getText(), password);
                dispose();
                StartViews.startDataViewer();
            } else {
                JOptionPane.showMessageDialog(this, "No se ha encontrado algún usuario con los datos introducidos.", "Error", JOptionPane.ERROR_MESSAGE, icono);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE, icono);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        Login dialog = new Login();
        dialog.pack();
        dialog.setTitle("Login");
        dialog.setResizable(false);
        dialog.setLocation(StartViews.appFrame.getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
