package org.example.views;

import org.example.models.User;

import javax.swing.*;
import java.awt.*;

public class DataViewer {
    JPanel bodyPanel;
    private JLabel welcomeLabel;
    private JButton viewDataButton;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel panelOfPanels;

    static User user;

    public DataViewer() {
        welcomeLabel.setText(welcomeLabel.getText() + " " + user.getUsername());
        viewDataButton.addActionListener(e -> {
            String[][] data = {{user.getName(), user.getSurname(), user.getUsername(), user.getEmail(), user.getPassword()}};
            String[] column = {"Nombre", "Apellidos", "Nombre de usuario", "Email", "Contraseña"};
            JTable jt = new JTable(data, column);
            JScrollPane sp = new JScrollPane(jt);
            sp.setPreferredSize(new Dimension(800, 39));
            jt.getTableHeader().setBackground(Color.black);
            jt.getTableHeader().setForeground(Color.white);
            if (viewDataButton.getText().equals("Ver datos")) {
                viewDataButton.setText("Ocultar datos");
                tablePanel.add(sp);
            } else {
                viewDataButton.setText("Ver datos");
                tablePanel.removeAll();
                tablePanel.updateUI();
            }
        });
    }
}
