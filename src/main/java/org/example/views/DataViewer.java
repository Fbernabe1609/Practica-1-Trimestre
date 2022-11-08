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
    private JPanel dataPanel;
    private JPanel buttonsRadioPanel;
    private JPanel panelOfPanels;
    private JPanel welcomeLabelPanel;
    private JButton exitButton;
    private JButton goBackButton;
    private JPanel buttonsPanel;
    private JRadioButton tableRadioButton;
    private JRadioButton listRadioButton;
    private JPanel radioButtonsPanel;

    private String hideData = "Ocultar datos";
    private String viewData = "Ver datos";
    private String name = "Nombre";
    private String surname = "Apellidos";
    private String username = "Nombre de usuario";
    private String email = "Email";
    private String password = "Contraseña";


    public DataViewer() {

        ButtonGroup group = new ButtonGroup();
        group.add(tableRadioButton);
        group.add(listRadioButton);

        welcomeLabel.setText(welcomeLabel.getText() + " " + UserController.getUser().getUsername());

        String[][] data = {{UserController.getUser().getName(), UserController.getUser().getSurname(), UserController.getUser().getUsername(), UserController.getUser().getEmail(), UserController.getUser().getPassword()}};
        String[] column = {name,surname,username,email,password};
        JTable jt = new JTable(data, column);
        JScrollPane sp = new JScrollPane(jt);
        sp.setPreferredSize(new Dimension(700, 39));
        jt.getTableHeader().setBackground(exitButton.getBackground());
        jt.getTableHeader().setForeground(Color.white);

        viewDataButton.addActionListener(e -> {
            if (viewDataButton.getText().equals(viewData)) {
                viewDataButton.setText(hideData);
                viewDataButton.setToolTipText(hideData);
                listRadioButton.setVisible(true);
                tableRadioButton.setVisible(true);
                if (tableRadioButton.isSelected()) {
                    dataPanel.add(sp);
                } else {
                    JPanel jPanel = new JPanel();
                    jPanel.setLayout(new GridLayout(5,2));
                    GridBagConstraints gbc = new GridBagConstraints();
                    JLabel label1 = new JLabel(name + ":");
                    label1.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label2 = new JLabel(UserController.getUser().getName());
                    label2.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label3 = new JLabel(surname + ":");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label4 = new JLabel(UserController.getUser().getSurname());
                    label4.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label5 = new JLabel(username + ":");
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label6 = new JLabel(UserController.getUser().getUsername());
                    label6.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label7 = new JLabel(email + ":");
                    label7.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label8 = new JLabel(UserController.getUser().getEmail());
                    label8.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label9 = new JLabel(password + ":");
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel label10 = new JLabel(UserController.getUser().getPassword());
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    jPanel.add(label1, gbc);
                    jPanel.add(label2, gbc);
                    jPanel.add(label3, gbc);
                    jPanel.add(label4, gbc);
                    jPanel.add(label5, gbc);
                    jPanel.add(label6, gbc);
                    jPanel.add(label7, gbc);
                    jPanel.add(label8, gbc);
                    jPanel.add(label9, gbc);
                    jPanel.add(label10, gbc);
                }
            } else {
                listRadioButton.setVisible(false);
                tableRadioButton.setVisible(false);
                viewDataButton.setText(viewData);
                viewDataButton.setToolTipText(viewData);
                dataPanel.removeAll();
                dataPanel.updateUI();
            }
        });
        exitButton.addActionListener(e -> System.exit(0));
        goBackButton.addActionListener(e -> {
            StartViews.startViews();
            StartViews.dataViewerFrame.dispose();
        });
        tableRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPanel.removeAll();
                dataPanel.updateUI();
                dataPanel.add(sp);
            }
        });
        listRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dataPanel.removeAll();
                dataPanel.updateUI();
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new GridLayout(5,2));
                GridBagConstraints gbc = new GridBagConstraints();
                JLabel label1 = new JLabel(name + ":");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label2 = new JLabel(UserController.getUser().getName());
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label3 = new JLabel(surname + ":");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label4 = new JLabel(UserController.getUser().getSurname());
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label5 = new JLabel(username + ":");
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label6 = new JLabel(UserController.getUser().getUsername());
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label7 = new JLabel(email + ":");
                label7.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label8 = new JLabel(UserController.getUser().getEmail());
                label8.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label9 = new JLabel(password + ":");
                label9.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel label10 = new JLabel(UserController.getUser().getPassword());
                label10.setHorizontalAlignment(SwingConstants.CENTER);
                jPanel.add(label1, gbc);
                jPanel.add(label2, gbc);
                jPanel.add(label3, gbc);
                jPanel.add(label4, gbc);
                jPanel.add(label5, gbc);
                jPanel.add(label6, gbc);
                jPanel.add(label7, gbc);
                jPanel.add(label8, gbc);
                jPanel.add(label9, gbc);
                jPanel.add(label10, gbc);
                dataPanel.add(jPanel);
            }
        });
    }

}
