package org.example.views;

import org.example.controllers.UserController;
import org.example.models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataViewer {
    JPanel bodyPanel;
    private JLabel welcomeLabel;
    private JButton viewDataButton;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JPanel panelOfPanels;
    private JPanel welcomeLabelPanel;
    private JButton exitButton;
    private JButton goBackButton;

    public DataViewer() {
        welcomeLabel.setText(welcomeLabel.getText() + " " + UserController.getUser().getUsername());
        viewDataButton.addActionListener(e -> {
            String[][] data = {{UserController.getUser().getName(), UserController.getUser().getSurname(), UserController.getUser().getUsername(), UserController.getUser().getEmail(), UserController.getUser().getPassword()}};
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
        exitButton.addActionListener(e -> System.exit(0));
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartViews.startViews();
                StartViews.dataViewerFrame.dispose();
            }
        });
    }

}
