package org.example.views;

import org.example.models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataViewer {
    JPanel bodyPanel;
    private JLabel welcomeLabel;
    private JButton button1;

    static User user;

    public DataViewer() {
        welcomeLabel.setText(welcomeLabel.getText() + " " + user.getUsername());
    }
}
