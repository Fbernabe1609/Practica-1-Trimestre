package org.example.views;

import javax.swing.*;

public class Home {
    JPanel bodyPanel;
    private JLabel imageLabel;
    private JButton loginButton;
    private JButton registerButton;

    public Home() {
        loginButton.addActionListener(e -> Login.start());
        registerButton.addActionListener(e -> Register.start());
    }
}
