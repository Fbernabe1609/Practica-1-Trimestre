package org.example.views;

import org.example.controllers.ValidationData;
import org.example.models.DbHelper;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel bodyPanel;
    private JPanel buttonsPanel;
    private JPanel contentButtonPanel;

    DbHelper db = new DbHelper();

    public Login() {
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

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if ( ValidationData.checkFields(usernameField.getText(), Arrays.toString(passwordField.getPassword()))){
            if (db.searchUser(usernameField.getText(), Arrays.toString(passwordField.getPassword()))) {
                DataViewer.user = db.viewDocument(usernameField.getText(), Arrays.toString(passwordField.getPassword()));
                dispose();
                StartViews.startDataViewer();
            } else {
                JOptionPane.showMessageDialog(this,"No se ha encontrado algún usuario con los datos introducidos","Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,"Completa todos los campos","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void start() {
        Login dialog = new Login();
        dialog.pack();
        dialog.setLocation(StartViews.appFrame.getLocation());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
