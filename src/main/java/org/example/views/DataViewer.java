package org.example.views;

import org.example.controllers.UserController;

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

    private String hideData = "Ocultar datos";
    private String viewData = "Ver datos";


    public DataViewer() {
        welcomeLabel.setText(welcomeLabel.getText() + " " + UserController.getUser().getUsername());
        viewDataButton.addActionListener(e -> {
            String[][] data = {{UserController.getUser().getName(), UserController.getUser().getSurname(), UserController.getUser().getUsername(), UserController.getUser().getEmail(), UserController.getUser().getPassword()}};
            String[] column = {"Nombre", "Apellidos", "Nombre de usuario", "Email", "Contraseña"};
            JTable jt = new JTable(data, column);
            JScrollPane sp = new JScrollPane(jt);
            sp.setPreferredSize(new Dimension(700, 39));
            jt.getTableHeader().setBackground(Color.black);
            jt.getTableHeader().setForeground(Color.white);
            if (viewDataButton.getText().equals(viewData)) {
                viewDataButton.setText(hideData);
                viewDataButton.setToolTipText(hideData);
                tablePanel.add(sp);
            } else {
                viewDataButton.setText(viewData);
                viewDataButton.setToolTipText(viewData);
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
