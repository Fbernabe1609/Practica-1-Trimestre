package org.example.views;

import org.example.controllers.DBController;
import org.example.controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
    private JButton saveButton;

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
        String[] column = {name, surname, username, email, password};
        JTable jt = new JTable(data, column) {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        jt.setRowSelectionAllowed(false);
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
                    makeLabels();
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
        tableRadioButton.addActionListener(e -> {
            dataPanel.removeAll();
            dataPanel.updateUI();
            dataPanel.add(sp);
        });
        listRadioButton.addActionListener(e -> {

            dataPanel.removeAll();
            dataPanel.updateUI();
            makeLabels();
        });
        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setSelectedFile(new File("fichero.txt"));
            fileChooser.setDialogTitle("Guardando fichero con sus datos");
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = fileChooser.getSelectedFile().getAbsolutePath();

                try {
                    FileWriter writer = new FileWriter(path);
                    writer.write(DBController.exportUser(UserController.getUser().getEmail()).toString());
                    writer.close();
                } catch (IOException ex) {

                    ex.printStackTrace();
                }
            }
        });
    }

    public void makeLabels() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(5, 2));
        GridBagConstraints gbc = new GridBagConstraints();
        ArrayList<JLabel> labels = new ArrayList<>() {{
            add(new JLabel(name + ":"));
            add(new JLabel(UserController.getUser().getName()));
            add(new JLabel(surname + ":"));
            add(new JLabel(UserController.getUser().getSurname()));
            add(new JLabel(username + ":"));
            add(new JLabel(UserController.getUser().getUsername()));
            add(new JLabel(email + ":"));
            add(new JLabel(UserController.getUser().getEmail()));
            add(new JLabel(password + ":"));
            add(new JLabel(UserController.getUser().getPassword()));
        }};
        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            jPanel.add(label, gbc);
        }
        dataPanel.add(jPanel);
    }
}
